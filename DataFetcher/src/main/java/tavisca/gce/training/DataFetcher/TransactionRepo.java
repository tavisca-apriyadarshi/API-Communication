package tavisca.gce.training.DataFetcher;

import org.springframework.data.jpa.repository.JpaRepository;
import tavisca.gce.training.DataFetcher.model.TransactionDetails;

public interface TransactionRepo extends JpaRepository<TransactionDetails, Integer> {
}
