package ru.t1.club_card.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.t1.club_card.models.ClubMember;
import ru.t1.club_card.services.MainService;

@RestController
@RequiredArgsConstructor
@Validated
public class MainController {

    private final MainService mainService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid ClubMember clubMember) {
        var tokens = mainService.register(clubMember);
        if (tokens == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        if (mainService.confirm(token) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        var accessToken = mainService.login(email, password);
        if (accessToken == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(accessToken);
    }

}
