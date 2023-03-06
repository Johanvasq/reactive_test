package co.com.ias.reactive_test.repository;

import co.com.ias.reactive_test.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends ReactiveCrudRepository<User, Integer> {
}
