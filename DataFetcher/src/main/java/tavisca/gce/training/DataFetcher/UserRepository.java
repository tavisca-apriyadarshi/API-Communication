package tavisca.gce.training.DataFetcher;

import org.springframework.data.jpa.repository.JpaRepository;
import tavisca.gce.training.DataFetcher.model.UsersData;

public interface UserRepository extends JpaRepository<UsersData, Integer> {
}
