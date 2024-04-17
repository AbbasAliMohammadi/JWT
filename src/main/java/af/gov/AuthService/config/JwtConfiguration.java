package af.gov.AuthService.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.util.Base64;

import af.gov.AuthService.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {
    // public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;

    // private static final String secret_key = "MTU0ZTZjYzg4OWM2NmNkNTc2MGFkM2EzOTIxYzA1Mjc2YzhhNGQ4OWQ5YWY4NzFiMDVjNDk2NTI0NjkwZWJlMDUxYjRkY2E5YjkwN2Q1MjM0YzQ4MjcxM2Q4ZWRiMjY1YmYxMTBmZjNjNDQzNTMzMWI0Yzk3ZGZjNjk3ZmZlNDA=";
    // @Bean
    // public JwtDecoder jwtDecoder() {
    //     NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(getSecretKey()).macAlgorithm(JWT_ALGORITHM).build();
    //     return token -> {
    //         try {
    //             return jwtDecoder.decode(token); 
    //         } catch (Exception e) {
    //             // if (e.getMessage().contains("Invalid signature")) {
    //             //     metersService.trackTokenInvalidSignature();
    //             // } else if (e.getMessage().contains("Jwt expired at")) {
    //             //     metersService.trackTokenExpired();
    //             // } else if (e.getMessage().contains("Invalid JWT serialization")) {
    //             //     metersService.trackTokenMalformed();
    //             // } else if (e.getMessage().contains("Invalid unsecured/JWS/JWE")) {
    //             //     metersService.trackTokenMalformed();
    //             // }
    //             throw e;
    //         }
    //     };
    // }
    // @Bean
    // public JwtEncoder jwtEncoder() {
    //     return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    // }

    // public SecretKey getSecretKey() {
    //     byte[] keyBytes = Base64.from(secret_key).decode();
    //     return new SecretKeySpec(keyBytes, 0, keyBytes.length, JWT_ALGORITHM.getName());
    // }

    private final UserRepository userRepository;
 
    @Bean
    public UserDetailsService userDetailsService(){
    return username -> userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
    
}
