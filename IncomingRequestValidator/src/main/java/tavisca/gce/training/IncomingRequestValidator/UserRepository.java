package tavisca.gce.training.IncomingRequestValidator;

import org.springframework.data.jpa.repository.JpaRepository;
import tavisca.gce.training.IncomingRequestValidator.model.UsersData;

public interface UserRepository extends JpaRepository<UsersData, Integer> {
}
