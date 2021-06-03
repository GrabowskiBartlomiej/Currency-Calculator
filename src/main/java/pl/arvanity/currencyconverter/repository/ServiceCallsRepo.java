package pl.arvanity.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arvanity.currencyconverter.entity.ServiceCalls;

public interface ServiceCallsRepo extends JpaRepository<ServiceCalls, Long> {

}
