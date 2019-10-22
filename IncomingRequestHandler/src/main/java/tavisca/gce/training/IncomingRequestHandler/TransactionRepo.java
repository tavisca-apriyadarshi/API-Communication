package tavisca.gce.training.IncomingRequestHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import tavisca.gce.training.IncomingRequestHandler.model.TransactionDetails;

public interface TransactionRepo extends JpaRepository<TransactionDetails, Integer> {
}
