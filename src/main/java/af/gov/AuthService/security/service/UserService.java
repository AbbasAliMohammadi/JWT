package af.gov.AuthService.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import af.gov.AuthService.domain.auth.Role;
import af.gov.AuthService.domain.auth.User;
import af.gov.AuthService.repository.RoleRepository;
import af.gov.AuthService.repository.UserRepository;
import af.gov.AuthService.security.DTO.MapRoleDTO;

@Service
public class UserService {
 private final UserRepository userRepository;
 private final RoleRepository roleRepository;
        public UserService(UserRepository userRepository, RoleRepository roleRepository){
            this.userRepository=userRepository;
            this.roleRepository=roleRepository;
        }

        public ResponseEntity<String> mapRoleToUser(MapRoleDTO mapRoleDTO){
            User user =userRepository.findById(mapRoleDTO.getUserId()).orElse(null);
            if(user!=null){
              List<Role> roles = roleRepository.findAllById(mapRoleDTO.getRoleIds());
              user.setRoles(new HashSet<>(roles));
              userRepository.save(user);
              return ResponseEntity.ok("successfully added");
            }else{
                return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
            }

        }
    
}
