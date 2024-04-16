package af.gov.AuthService.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.gov.AuthService.security.DTO.MapRoleDTO;
import af.gov.AuthService.security.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user/")
public class UserResource {
    private final UserService userService;
    public UserResource(UserService userService){
     this.userService=userService;
    }
    @PostMapping("mapRole")
    public ResponseEntity<String> mapRoleToUser(@Valid @RequestBody MapRoleDTO mapRoleDTO){
      return userService.mapRoleToUser(mapRoleDTO);
    }
}
