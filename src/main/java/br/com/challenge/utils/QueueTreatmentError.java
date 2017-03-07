package br.com.challenge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for listen the errors from the ActiveMQ.DLQ
 * 
 * @author Gustavo Luis dos Santos
 */
@Component
public class QueueTreatmentError {

	private static final Logger logger = LoggerFactory.getLogger(QueueTreatmentError.class);

	/**
	 * Method responsible for listen the errors from the ActiveMQ.DLQ
	 * 
	 * @param e
	 *            - Generic Error Message.
	 */
	@JmsListener(destination = "ActiveMQ.DLQ")
	public void error(GenericMessage<?> e) {
		logger.error("ERROR --- Processing queue error", e);
	}

}
