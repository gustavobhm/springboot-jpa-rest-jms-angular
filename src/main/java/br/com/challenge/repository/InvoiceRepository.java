package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.challenge.models.Invoice;

/**
 * This interface is responsable for the automatic creation of endpoints Rest
 * and the data persistance of invoices
 * 
 * @author Gustavo Luis dos Santos
 */
@Component
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	Invoice findByCod(String cod);

	Invoice findByEmitter(String emitter);

	Invoice findByCommoditiesCod(String cod);

}
