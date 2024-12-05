package peaksoft.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.LoginRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.model.User;
import peaksoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @PostConstruct
    public void savedDefaultAdmin() {
        boolean exists = userRepository.existsByEmail("admin@peaksoft.com");
        if (!exists){
            userRepository.save(
                    User.builder()
                            .name("Admin")
                            .email("admin@peaksoft.com")
                            .password("admin")
                            .role("ADMIN")
                            .build()
            );
        }
    }

    @Override
    public SimpleResponse signUp(AuthRequest authRequest) {
        boolean exists = userRepository.existsByEmail(authRequest.getEmail());
        if (exists) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.CONFLICT)
                    .message("Email already exists")
                    .build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        user.setName(authRequest.getName());
        user.setRole("CLIENT");
        userRepository.save(user);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.email()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!user.getPassword().equals(loginRequest.password())) {
            throw new IllegalArgumentException("Wrong password / Парол туура эмес");
        }
       return AuthResponse.builder()
                .userId(user.getId())
                .role(user.getRole())
                .email(user.getEmail())
                .build();

    }

    @Override
    public AuthResponse findById(Long id) {
        return userRepository.findByUser(id);

    }
}
