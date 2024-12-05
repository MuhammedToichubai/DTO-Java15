package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthRequest {
    private String email;
    private String name;
    private String password;
}
