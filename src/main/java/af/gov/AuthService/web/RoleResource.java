package af.gov.AuthService.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.gov.AuthService.domain.auth.Role;
import af.gov.AuthService.security.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/role/")
@AllArgsConstructor
public class RoleResource {
    private final RoleService roleService;
    @PostMapping
    public ResponseEntity<Role> save(@Valid @RequestBody Role role){
      return new ResponseEntity<Role>(roleService.save(role), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role>> view(){
      return new ResponseEntity<>(roleService.view(), HttpStatus.OK);
    }
}
