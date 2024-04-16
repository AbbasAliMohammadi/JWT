package af.gov.AuthService.security.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AuthRequestDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
    
}
 