package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.challenge.models.Commodity;

/**
 * This interface is responsable for the automatic creation of endpoints Rest
 * and the data persistance of commodities
 * 
 * @author Gustavo Luis dos Santos
 */
@Component
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

	Commodity findByCod(String cod);

}
