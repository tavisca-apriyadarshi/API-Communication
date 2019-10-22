package tavisca.gce.training.DataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavisca.gce.training.DataFetcher.model.TransactionDetails;
import tavisca.gce.training.DataFetcher.model.TransactionIdGenerator;
import tavisca.gce.training.DataFetcher.model.UsersData;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class DataFetchingController {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TransactionRepo tRepo;
    private TransactionDetails transactionDetails;

    @GetMapping("/users")
    public ResponseEntity getAllUsers(HttpServletRequest request) {
        transactionDetails = new TransactionDetails();
        saveTransaction("Getting all users.", transactionDetails, request);
        System.out.println("All users shown");
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer id, HttpServletRequest request) {
        transactionDetails = new TransactionDetails();
        saveTransaction("Getting users with id: "+id, transactionDetails, request);
        System.out.println("Single user shown");
        return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity addUser(@RequestBody UsersData userData, @RequestParam("txid") String transactionId, HttpServletRequest request) {
        //if(userData.getPassword().length() >= 8 && userData.getName().contains("@") && userData.getName().endsWith(".com"))
        transactionDetails = new TransactionDetails();
        saveTransaction("Saving user.", transactionDetails, request);
        System.out.println("Saving data.");
        return ResponseEntity.status(HttpStatus.OK).body(repo.save(userData));
    }

    private void saveTransaction(String user, TransactionDetails transactionDetails,HttpServletRequest request) {
        transactionDetails.setTransactionId(TransactionIdGenerator.getUniqueID());
        transactionDetails.setValidity(true);
        transactionDetails.setInputData(user);
        transactionDetails.setServiceFrom(request.getRequestURI());
        transactionDetails.setServiceName("Output");
        transactionDetails.setTimestamp(LocalDateTime.now().toString());
        tRepo.save(transactionDetails);
    }

}
