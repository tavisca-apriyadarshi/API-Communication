package tavisca.gce.training.IncomingRequestValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tavisca.gce.training.IncomingRequestValidator.model.TransactionDetails;
import tavisca.gce.training.IncomingRequestValidator.model.UsersData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RequestValidationController {
    @Autowired
    private TransactionRepo transactionRepo;
    private TransactionDetails transactionDetails;

    @PostMapping("/validate")
    public ResponseEntity addUser(@RequestBody String userData, @RequestParam("txid") String transactionId, HttpServletRequest request) throws IOException {
/*
        if(userData.getPassword().length() >= 8 && userData.getName().contains("@") && userData.getName().endsWith(".com"))
            return ResponseEntity.status(HttpStatus.OK).body(repo.save(userData));
*/
        transactionDetails = new TransactionDetails();
        boolean validity = false;
            validity = isValid(userData);
        saveTransactionDetails(userData, transactionDetails, validity, transactionId, request);
        if(validity){
            JSONObject js = new JSONObject(userData);
            ObjectMapper mapper = new ObjectMapper();
            UsersData data = mapper.readValue(userData, UsersData.class);

            final String uri = "http://localhost:8085/save?txid="+transactionId;
            System.out.println(uri);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UsersData> responseEntity = restTemplate.postForEntity(uri, data, UsersData.class);
            if(responseEntity.getStatusCode().isError())
                return responseEntity;
            return ResponseEntity.status(HttpStatus.OK).body("User allowed successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check details carefully");
    }

    private boolean isValid(String userData) {
        JSONObject data = new JSONObject(userData);

        if(data.isNull("birthDate") || data.isNull("createdBy") ||
                data.isNull("created_Date") || data.isNull("name") ||
                data.isNull("password") || data.isNull("role")){
            return false;
        }
        return true;
    }

    private void saveTransactionDetails(String userData, TransactionDetails transactionDetails, boolean validity, String transactionId, HttpServletRequest request) {
        transactionDetails.setTransactionId(transactionId);
        transactionDetails.setTimestamp(LocalDateTime.now().toString());
        transactionDetails.setServiceName("http://localhost:8085/save");
        transactionDetails.setServiceFrom(request.getRequestURI());
        transactionDetails.setInputData(userData);
        transactionDetails.setValidity(validity);
        transactionRepo.save(transactionDetails);
    }
}
