package tavisca.gce.training.IncomingRequestValidator;

import org.springframework.data.jpa.repository.JpaRepository;
import tavisca.gce.training.IncomingRequestValidator.model.TransactionDetails;

public interface TransactionRepo extends JpaRepository<TransactionDetails, Integer> {
}
