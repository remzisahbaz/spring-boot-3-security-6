package example.service;


import example.config.JwtService;
import example.dto.request.AuthenticateionRequest;
import example.dto.request.RegisterRequest;
import example.dto.response.AuthenticationResponse;
import example.model.Role;
import example.model.User;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest requestBody) {
        var user = User.builder()
                .firstname(requestBody.getFirstname())
                .lastname(requestBody.getLastname())
                .email(requestBody.getEmail())
                .username(requestBody.getUsername())
                .password(passwordEncode.encode(requestBody.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateionRequest authRequest) {
        var user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow();
       /* authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user,
                        authRequest

                )
        );
*/
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
