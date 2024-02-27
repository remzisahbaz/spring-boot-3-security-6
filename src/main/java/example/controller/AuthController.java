package example.controller;

import example.dto.request.AuthenticateionRequest;
import example.dto.request.RegisterRequest;
import example.dto.response.AuthenticationResponse;
import example.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestScope
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody RegisterRequest requestBody
    ){
return ResponseEntity.ok(authService.register(requestBody));
    }

    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody AuthenticateionRequest authRequest
    ){
return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute("user");
        redirectAttributes.addFlashAttribute("success", "You have successfully logged out.");
        return "redirect:login";
    }

}
