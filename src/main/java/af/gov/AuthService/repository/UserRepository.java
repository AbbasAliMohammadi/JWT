package af.gov.AuthService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.AuthService.domain.auth.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    
} 
