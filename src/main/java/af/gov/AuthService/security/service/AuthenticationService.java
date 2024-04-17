package af.gov.AuthService.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import af.gov.AuthService.domain.auth.User;
import af.gov.AuthService.repository.UserRepository;
import af.gov.AuthService.security.DTO.AuthRequestDTO;
import af.gov.AuthService.security.DTO.AuthResponseDTO;
import af.gov.AuthService.security.DTO.RegisterRequestDTO;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationService(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtService jwtService){
      this.userRepository=userRepository;
      this.passwordEncoder=passwordEncoder;
      this.authenticationManager=authenticationManager;
      this.jwtService=jwtService;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
		var user = User.builder()
				.name(request.getName())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
				.activated(false)
				.build();
		userRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		return AuthResponseDTO.builder()
				.token(jwtToken)
				.build();
	}

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword())
				);
		var user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    
		
		var jwtToken = jwtService.generateToken(user);
		return AuthResponseDTO.builder()
				.token(jwtToken)
				.build();
	}
}
