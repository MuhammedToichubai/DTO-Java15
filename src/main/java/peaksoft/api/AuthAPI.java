package peaksoft.api;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.LoginRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final UserService userService;


    @PostMapping("/sign-up")
    public SimpleResponse signUp(@RequestBody AuthRequest authRequest) {
        return userService.signUp(authRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
       return userService.login(loginRequest);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}")
    public AuthResponse getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}
