package af.gov.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.AuthService.domain.auth.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    
}
