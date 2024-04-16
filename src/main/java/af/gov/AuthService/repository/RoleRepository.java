package af.gov.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.AuthService.domain.auth.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    
} 
