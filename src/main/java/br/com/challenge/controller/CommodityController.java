package br.com.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.models.Commodity;
import br.com.challenge.repository.CommodityRepository;
import br.com.challenge.utils.ProducerException;

/**
 * This class is responsible for mapping and controller the Rest requests of
 * Commodities
 * 
 * @author Gustavo Luis dos Santos
 */
@Component
@RestController
@RequestMapping("/commodities")
public class CommodityController {

	@Autowired
	private CommodityRepository commodityRepository;

	/**
	 * Rest Endpoint Method responsible for find all commodities in the DataBase
	 * 
	 * @return a List of commodities
	 * 
	 */
	@GetMapping(value = "/all")
	public List<Commodity> findAll() {
		return commodityRepository.findAll();
	}

	/**
	 * Rest Endpoint Method responsible for search a commodity by the code
	 * 
	 * @param cod
	 *            - the String code
	 * @return the Commodity
	 * 
	 */
	@GetMapping(value = "/cod/{cod}")
	public Commodity findByCod(@PathVariable final String cod) {
		return commodityRepository.findByCod(cod);
	}

	/**
	 * Rest Endpoint Method responsible for save the Commodity to the
	 * Repository.
	 * 
	 * @param commodity
	 *            - the Commodity that will be saved
	 * @return the saved Commodity
	 * 
	 */
	@PostMapping(value = "/save")
	public Commodity save(@RequestBody final Commodity commodity) {
		try {
			commodityRepository.saveAndFlush(commodity);
		} catch (Exception e) {
			throw new ProducerException("Error including commodity=" + commodity, e);
		}
		return commodityRepository.findByCod(commodity.getCod());
	}

	/**
	 * Method responsible for listen and send the Commodities to the queue.
	 * 
	 * @param invoice
	 *            - the Commodity that will be send to the JMS queue
	 */
	@JmsListener(destination = "queue_commodity")
	public void send(Commodity commodity) {
		try {
			commodityRepository.saveAndFlush(commodity);
		} catch (Exception e) {
			throw new ProducerException("Error including commodity=" + commodity, e);
		}
	}

}
