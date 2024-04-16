package af.gov.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.AuthService.domain.auth.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
    
}
