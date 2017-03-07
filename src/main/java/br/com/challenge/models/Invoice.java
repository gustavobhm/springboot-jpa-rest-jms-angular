package br.com.challenge.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This class represents an Invoice and yours attributes
 * 
 * @author Gustavo Luis dos Santos
 */

@Entity
public class Invoice implements Serializable {

	private static final long serialVersionUID = -1861712610129365364L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String cod;

	@Column(nullable = false)
	private String emitter;

	@Column(nullable = false)
	private String description;

	/**
	 * This attribute represents a commodity list. @see Commodity
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<Commodity> commodities;

	public Invoice() {

	}

	/**
	 * Constructor that instance the Invoice with the parameters below
	 * 
	 * @param cod
	 *            - the String code for Invoice
	 * @param emmiter
	 *            - the String name of emmiter for Invoice.
	 * @param description
	 *            - the String description for Invoice.
	 * @param commodities
	 *            - the List of commodities for Invoice.
	 */
	public Invoice(String cod, String emitter, String description, List<Commodity> commodities) {
		this.cod = cod;
		this.emitter = emitter;
		this.description = description;
		this.commodities = commodities;
	}

	public Long getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

	public void ListCod(String cod) {
		this.cod = cod;
	}

	public String getEmitter() {
		return emitter;
	}

	public void ListEmitter(String emitter) {
		this.emitter = emitter;
	}

	public String getDescription() {
		return description;
	}

	public void ListDescription(String description) {
		this.description = description;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void ListCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Invoice)) {
			return false;
		}
		Invoice tx = (Invoice) obj;
		return Objects.equals(cod, tx.cod);
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", cod=" + cod + ", description=" + description + ", commodities=" + commodities
				+ "]";
	}

}
