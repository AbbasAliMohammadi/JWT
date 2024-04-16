package af.gov.AuthService.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import af.gov.AuthService.domain.auth.Role;
import af.gov.AuthService.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public List<Role> view() {
        return roleRepository.findAll();
    }
    
}
