package org.causwengteam13.issuetrackerserver.presentation.restapi.auth;

import org.causwengteam13.issuetrackerserver.domain.user.command.SignInCommand;
import org.causwengteam13.issuetrackerserver.domain.user.command.SignUpCommand;
import org.causwengteam13.issuetrackerserver.domain.user.result.SignInResult;
import org.causwengteam13.issuetrackerserver.domain.user.result.SignUpResult;
import org.causwengteam13.issuetrackerserver.domain.user.usecase.SignIn;
import org.causwengteam13.issuetrackerserver.domain.user.usecase.SignUp;
import org.causwengteam13.issuetrackerserver.presentation.restapi.CommonResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.auth.request.SignInRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.auth.request.SignUpRequest;
import org.causwengteam13.issuetrackerserver.presentation.restapi.auth.response.SignInResponse;
import org.causwengteam13.issuetrackerserver.presentation.restapi.auth.response.SignUpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUp signUp;
    private final SignIn signIn;

    @PostMapping("/sign-up")
    public CommonResponse<SignUpResponse> signUp(
        @RequestBody @Valid SignUpRequest request
    ) {
        SignUpCommand command = SignUpCommand.builder()
            .loginId(request.getLoginId())
            .password(request.getPassword())
            .name(request.getName())
            .build();

        SignUpResult result = signUp.execute(command);

        SignUpResponse response = new SignUpResponse(result.token());

        return CommonResponse.success("Sign up success", response);
    }

    @PostMapping("/sign-in")
    public CommonResponse<SignInResponse> signIn(
        @RequestBody @Valid SignInRequest request
    ) {
        SignInCommand command = SignInCommand.builder()
            .loginId(request.getLoginId())
            .password(request.getPassword())
            .build();

        SignInResult result = signIn.execute(command);

        SignInResponse response = new SignInResponse(result.token());

        return CommonResponse.success("Sign in success", response);
    }
}
