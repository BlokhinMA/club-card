package ru.t1.club_card.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.t1.club_card.models.ClubMember;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClubMember save(ClubMember clubMember) {
        jdbcTemplate.update("INSERT INTO club_members(email, password, first_name, last_name, birthday, phone, privilege, is_locked, role, template) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.getFirstName(),
                clubMember.getLastName(),
                clubMember.getBirthday(),
                clubMember.getPhone(),
                clubMember.getPrivilege(),
                clubMember.isLocked(),
                clubMember.getRole(),
                clubMember.getTemplate());
        return jdbcTemplate.query("SELECT * FROM club_members ORDER BY id DESC LIMIT 1;", new BeanPropertyRowMapper<>(ClubMember.class))
                .stream().findAny().orElse(null);
    }

    public ClubMember findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM club_members WHERE email = ?;", new BeanPropertyRowMapper<>(ClubMember.class),
                        email)
                .stream().findAny().orElse(null);
    }

    public ClubMember findById(int id) {
        return jdbcTemplate.query("SELECT * FROM club_members WHERE id = ?;", new BeanPropertyRowMapper<>(ClubMember.class),
                        id)
                .stream().findAny().orElse(null);
    }

    public ClubMember updateIsLockedById(boolean isLocked, int id) {
        jdbcTemplate.update("UPDATE club_members SET is_locked = ? WHERE id = ?;",
                isLocked,
                id);
        return jdbcTemplate.query("SELECT * FROM club_members WHERE id = ?;", new BeanPropertyRowMapper<>(ClubMember.class),
                        id)
                .stream().findAny().orElse(null);
    }

}
