package ru.t1.club_card.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RefreshToken {

    private int id;
    private String token;
    private Date expiryDate;
    private int clubMemberId;

    public RefreshToken(String token, Date expiryDate, int clubMemberId) {
        this.token = token;
        this.expiryDate = expiryDate;
        this.clubMemberId = clubMemberId;
    }

}
