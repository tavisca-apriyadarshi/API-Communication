package tavisca.gce.training.IncomingRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tavisca.gce.training.IncomingRequestValidator.model.UsersData;

@RestController
public class RequestValidationController {
    @Autowired
    private UserRepository repo;

    @PostMapping("/validate")
    public ResponseEntity addUser(@RequestBody UsersData userData) {
        if(userData.getPassword().length() >= 8 && userData.getName().contains("@") && userData.getName().endsWith(".com"))
            return ResponseEntity.status(HttpStatus.OK).body(repo.save(userData));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check password and user name parameters");
    }
}
