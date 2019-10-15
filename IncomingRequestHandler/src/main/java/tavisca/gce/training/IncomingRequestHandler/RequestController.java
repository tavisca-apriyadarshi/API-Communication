package tavisca.gce.training.IncomingRequestHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tavisca.gce.training.IncomingRequestHandler.model.UsersData;

@RestController
public class RequestController {
    @PostMapping("/send")
    public ResponseEntity sendToValidatorApi(@RequestBody UsersData userData) {
        final String uri = "http://localhost:8083/validate";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UsersData> responseEntity = restTemplate.postForEntity(uri, userData, UsersData.class);
        if(responseEntity.getStatusCode().isError())
            return responseEntity;
        return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
    }
}
