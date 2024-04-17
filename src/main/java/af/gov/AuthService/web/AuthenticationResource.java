package af.gov.AuthService.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.gov.AuthService.security.DTO.AuthRequestDTO;
import af.gov.AuthService.security.DTO.AuthResponseDTO;
import af.gov.AuthService.security.DTO.RegisterRequestDTO;
import af.gov.AuthService.security.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/")
public class AuthenticationResource {
	private final AuthenticationService authenticationService;

    public AuthenticationResource(AuthenticationService authenticationService){
     this.authenticationService=authenticationService;
    }

	@PostMapping("register")
	public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}
	 
	@PostMapping("authenticate")
	public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthRequestDTO request) {
		
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}
