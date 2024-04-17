package af.gov.AuthService.security.service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtService {
	// these veriable is for Nimbus library
	// private final Long tokenValidityInSeconds= 86400L;
	// private final JwtEncoder jwtEncoder;
    private static final String secret_key = "442A472D4B6150645267556B58703273357638792F423F4528482B4D62516554";

        public String extractUsername(String token) {
            return extractClaim(token,Claims::getSubject);
        }
        
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims=extractAllClaims(token);
            return claimsResolver.apply(claims);
        }
        public Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder() 
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
                    
        }
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(
			Map<String,Object> extractClaims,
			UserDetails userDetails
			) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				// .claim("auth", userDetails.getAuthorities())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact(); 
				
				//generate token acording nimbus library
			// 	String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
			// 	Instant now = Instant.now();
			// 	Instant  validity = now.plus(this.tokenValidityInSeconds, ChronoUnit.SECONDS);
			// 	JwtClaimsSet claims = JwtClaimsSet.builder()
			// 	.issuedAt(now)
			// 	.expiresAt(validity)
			// 	.subject(userDetails.getUsername())
			// 	.claim("auth", authorities)
			// 	.build();
			// JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
			// return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
	}


	private Key getSignInKey() {
		byte[] keyBytes= Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
 
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
    
    
}
