package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    private Long id;
    @NotBlank
    private String password;
}
