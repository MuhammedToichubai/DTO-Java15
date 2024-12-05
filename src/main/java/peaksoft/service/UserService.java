package peaksoft.service;

import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.LoginRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.response.SimpleResponse;

public interface UserService {
    SimpleResponse signUp(AuthRequest authRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse findById(Long id);
}
