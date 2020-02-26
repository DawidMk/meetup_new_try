package pl.dma.appka.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString(exclude = "password")
public class RegisterFormDto {

    @NotEmpty(message = "nie może być puste")
    @Email(message = "musi być email")
    private String email;

    @NotEmpty(message = "nie może być puste")
    private String login;

    @NotEmpty(message = "nie może być puste")
//    @Pattern(regexp = "[^\\s\\\\]{1,50}", message = "bez białych znaków ani \\, długość 1-50")
    private String password;
}
