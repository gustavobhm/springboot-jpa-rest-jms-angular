package br.com.challenge.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.models.Commodity;
import br.com.challenge.models.Invoice;
import br.com.challenge.repository.InvoiceRepository;
import br.com.challenge.utils.ProducerException;

/**
 * This class is responsible for mapping and controller the Rest requests of
 * Invoices
 * 
 * @author Gustavo Luis dos Santos
 */
@Component
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Rest Endpoint Method responsible for find all invoices in the DataBase
	 * 
	 * @return a List of Invoices
	 * 
	 */
	@GetMapping(value = "/all")
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	/**
	 * Rest Endpoint Method responsible for search a invoice by the code
	 * 
	 * @param cod
	 *            - the String code
	 * @return the Invoice
	 * 
	 */
	@GetMapping(value = "/cod/{cod}")
	public Invoice findByCod(@PathVariable final String cod) {
		return invoiceRepository.findByCod(cod);
	}

	/**
	 * Rest Endpoint Method responsible for search a invoice by the emmiter
	 * 
	 * @param emitter
	 *            - the String of Emitter
	 * @return the Invoice
	 * 
	 */
	@GetMapping(value = "/emitter/{emitter}")
	public Invoice findByEmitter(@PathVariable final String emitter) {
		return invoiceRepository.findByEmitter(emitter);
	}

	/**
	 * Rest Endpoint Method responsible for search a invoice by the commodity
	 * code
	 * 
	 * @param cod
	 *            - the commodity code
	 * @return the Invoice
	 * 
	 */
	@GetMapping(value = "/commodities/{cod}")
	public Invoice findByCommoditiesCod(@PathVariable final String cod) {
		return invoiceRepository.findByCommoditiesCod(cod);
	}

	/**
	 * Rest Endpoint Method responsible for save the Invoice to the Repository.
	 * 
	 * @param invoice
	 *            - the Invoice that will be saved
	 * @return the saved Invoice
	 * 
	 */
	@PostMapping(value = "/save")
	public Invoice save(@RequestBody final Invoice invoice) {
		try {
			invoiceRepository.saveAndFlush(invoice);
		} catch (Exception e) {
			throw new ProducerException("Error including invoice=" + invoice, e);
		}
		return invoiceRepository.findByCod(invoice.getCod());
	}

	/**
	 * Rest Endpoint Method responsible for create a data mass
	 * 
	 * @return the message if the data creation was successfull.
	 * 
	 */
	@GetMapping(value = "/create_data")
	public String createData() {

		// Creating the first invoice and your commodities
		List<Commodity> commodities1 = new ArrayList<Commodity>();
		Commodity comm1 = new Commodity("BRA-005", new BigDecimal("3900"), "Geladeira Bras. 500 litros");
		Commodity comm2 = new Commodity("BRA-002", new BigDecimal("689.50"), "Micro-ondas Bras.");
		Commodity comm3 = new Commodity("BRA-010", new BigDecimal("1855.99"), "Fogão Bras. 4 bocas");
		commodities1.add(comm1);
		commodities1.add(comm2);
		commodities1.add(comm3);
		Invoice inv1 = new Invoice("001", "Loja_1", "NF-1", commodities1);

		// Creating the second invoice and your commodities
		List<Commodity> commodities2 = new ArrayList<Commodity>();
		Commodity comm4 = new Commodity("BRA-022", new BigDecimal("139.99"), "Filtro de Agua IBL");
		Commodity comm5 = new Commodity("BRA-033", new BigDecimal("333.33"), "Umidificador de Ar GEE");
		commodities2.add(comm4);
		commodities2.add(comm5);
		Invoice inv2 = new Invoice("002", "Shopping", "NF-2", commodities2);

		// Creating two separeted commodities
		Commodity comm6 = new Commodity("BRA-102", new BigDecimal("2500"), "Sofá Preto LLGR");
		Commodity comm7 = new Commodity("BRA-777", new BigDecimal("555.30"), "Liquidificador WPAII");

		// Sending the invoices and commodities by JMS
		jmsTemplate.convertAndSend("queue_invoice", inv1);
		jmsTemplate.convertAndSend("queue_invoice", inv2);
		jmsTemplate.convertAndSend("queue_commodity", comm6);
		jmsTemplate.convertAndSend("queue_commodity", comm7);

		return "Data created successfully!";
	}

	/**
	 * Method responsible for listen and send the Invoices to the queue.
	 * 
	 * @param invoice
	 *            - the Invoice that will be send to the JMS queue
	 */
	@JmsListener(destination = "queue_invoice")
	public void send(Invoice invoice) {
		try {
			invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw new ProducerException("Error including invoice=" + invoice, e);
		}
	}

}
