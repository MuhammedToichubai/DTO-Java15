package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peaksoft.enums.Role;

@Builder
@Getter
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String email;
    private Role role;
}
