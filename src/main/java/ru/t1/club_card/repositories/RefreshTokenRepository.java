package ru.t1.club_card.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.t1.club_card.models.RefreshToken;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(RefreshToken refreshToken) {
        jdbcTemplate.update("INSERT INTO refresh_tokens(token, expiry_date, club_member_id) VALUES(?, ?, ?);",
                refreshToken.getToken(),
                refreshToken.getExpiryDate(),
                refreshToken.getClubMemberId());
    }

}
