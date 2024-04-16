package af.gov.AuthService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestResource {


    @GetMapping("/")
    public String index(){
        return "test message";
    }
    
}
