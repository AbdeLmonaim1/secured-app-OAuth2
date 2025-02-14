package ma.app.appcustomerfrontthymeleaf.repository;

import ma.app.appcustomerfrontthymeleaf.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
