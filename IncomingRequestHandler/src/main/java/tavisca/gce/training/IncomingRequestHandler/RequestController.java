package tavisca.gce.training.IncomingRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tavisca.gce.training.IncomingRequestHandler.model.TransactionDetails;
import tavisca.gce.training.IncomingRequestHandler.model.TransactionIdGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class RequestController {
    @Autowired
    private TransactionRepo transactionRepo;
    private TransactionDetails transactionDetails;

    @PostMapping("/send")
    public ResponseEntity sendToValidatorApi(@RequestBody String userData) {
        System.out.println(userData);
        transactionDetails = new TransactionDetails();
        boolean validity = isValid(userData);
        saveTransactionDetails(userData, transactionDetails, validity);
        final String uri = "http://localhost:8083/validate?txid="+transactionDetails.getTransactionId();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, userData, String.class);
        if(responseEntity.getStatusCode().isError())
            return responseEntity;
        return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
    }

    private boolean isValid(String userData) {
        if(userData != null)
            return true;
        return false;
    }

    private void saveTransactionDetails(String userData, TransactionDetails transactionDetails, boolean validity) {
        transactionDetails.setTransactionId(TransactionIdGenerator.getUniqueID());
        transactionDetails.setTimestamp(LocalDateTime.now().toString());
        transactionDetails.setServiceName("http://localhost:8083/validate");
        transactionDetails.setInputData(userData);
        transactionDetails.setValidity(validity);
        transactionRepo.save(transactionDetails);
    }
}
