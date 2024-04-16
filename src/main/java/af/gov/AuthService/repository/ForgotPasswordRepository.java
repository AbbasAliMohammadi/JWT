package af.gov.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.AuthService.domain.auth.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword,Long> {

    
}
