package pl.arvanity.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arvanity.currencyconverter.entity.Calculation;

public interface CalculationRepo extends JpaRepository<Calculation, Long> {

    Calculation findCalculationById(long id);

}
