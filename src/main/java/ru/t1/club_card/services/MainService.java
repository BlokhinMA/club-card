package ru.t1.club_card.services;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.t1.club_card.models.ClubMember;
import ru.t1.club_card.models.RefreshToken;
import ru.t1.club_card.repositories.ClubMemberRepository;
import ru.t1.club_card.repositories.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class MainService {

    private final ClubMemberRepository clubMemberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    public ClubMember register(ClubMember clubMember) {
        if (clubMemberRepository.findByEmail(clubMember.getEmail()) != null)
            return null;

        clubMember.setPassword(BCrypt.hashpw(clubMember.getPassword(), BCrypt.gensalt()));
        clubMember.setLocked(true);
        clubMember.setRole("user");
        var clubMemberInDB = clubMemberRepository.save(clubMember);
        clubMemberInDB.setPassword(null);
        sendEmailConfirmation(clubMemberInDB);
        return clubMemberInDB;
    }

    private void sendEmailConfirmation(ClubMember clubMember) {
        String confirmationLink = "http://localhost:8080/confirm?token=" + jwtUtil.generate(String.valueOf(clubMember.getId()), clubMember.getRole(), "ACCESS");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(clubMember.getEmail());
        message.setSubject("Подтверждение регистрации");
        message.setText("Для подтверждения вашей регистрации, пожалуйста, перейдите по следующей ссылке: " + confirmationLink);
        mailSender.send(message);
    }

    public ClubMember confirm(String token) {
        Claims claims = jwtUtil.getClaims(token);
        int id = Integer.parseInt(claims.get("id").toString());
        if (clubMemberRepository.findById(id) == null)
            return null;
        return clubMemberRepository.updateIsLockedById(false, id);
    }

    public String login(String email, String password) {
        var clubMember = clubMemberRepository.findByEmail(email);
        if (clubMember == null || !BCrypt.checkpw(password, clubMember.getPassword()) || clubMember.isLocked())
            return null;

        var accessToken = jwtUtil.generate(String.valueOf(clubMember.getId()), clubMember.getRole(), "ACCESS");
        var refreshToken = jwtUtil.generate(String.valueOf(clubMember.getId()), clubMember.getRole(), "REFRESH");

        refreshTokenRepository.save(new RefreshToken(refreshToken, jwtUtil.getExpirationDate(refreshToken), clubMember.getId()));

        return accessToken;
    }

}
