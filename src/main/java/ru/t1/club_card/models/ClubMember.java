package ru.t1.club_card.models;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClubMember {

    private int id;

    @NotBlank(message = "Поле \"Email\" не должно быть пустым")
    @Size(min = 1, max = 255, message = "Поле \"Email\" должно содержать минимум 1 символ и максимум 255 символов")
    @Email(message = "Адрес электронной почты должен быть корректным, например: user@example.com")
    private String email;

    @NotBlank(message = "Поле \"Пароль\" не должно быть пустым")
    @Size(min = 8, max = 255, message = "Поле \"Пароль\" должно содержать минимум 8 и максимум 255 символов")
    private String password;

    @NotBlank(message = "Поле \"Имя\" не должно быть пустым")
    @Size(min = 1, max = 255, message = "Поле \"Имя\" должно содержать минимум 1 символ и максимум 255 символов")
    private String firstName;

    @NotBlank(message = "Поле \"Фамилия\" не должно быть пустым")
    @Size(min = 1, max = 255, message = "Поле \"Фамилия\" должно содержать минимум 1 символ и максимум 255 символов")
    private String lastName;

    private LocalDate birthday;

    @Max(value = 20, message = "Телефон должен содержать максимум 20 символов")
    private String phone;

    private String privilege;
    private boolean isLocked;
    private String role;
    private String template;

}
