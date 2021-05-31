package pl.arvanity.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arvanity.currencyconverter.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {


    Currency findCurrencyById(long id);
}
