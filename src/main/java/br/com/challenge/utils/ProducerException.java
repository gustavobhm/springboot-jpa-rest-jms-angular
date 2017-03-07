package br.com.challenge.utils;

/**
 * This class is the error representation from the production of JMS
 * 
 * @author Gustavo Luis dos Santos
 */
public class ProducerException extends RuntimeException {

	private static final long serialVersionUID = 5068891848300732090L;

	/**
	 * Constructor that receive a message and the error cause
	 * 
	 * @param msg
	 *            - Error message.
	 * @param cause
	 *            - Error cause.
	 */
	public ProducerException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
