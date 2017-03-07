package br.com.challenge.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents a Commodity and yours attributes
 * 
 * @author Gustavo Luis dos Santos
 */

@Entity
public class Commodity implements Serializable {

	private static final long serialVersionUID = -441135021112054035L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String cod;

	@Column(nullable = false)
	private BigDecimal value;

	@Column
	private String description;

	public Commodity() {

	}

	/**
	 * Constructor that instance the Commodity with the parameters below
	 * 
	 * @param cod
	 *            - the String code for Commodity
	 * @param value
	 *            - the BigDecimal price for Commodity.
	 * @param description
	 *            - the String description for Commodity.
	 */
	public Commodity(String cod, BigDecimal value, String description) {
		this.cod = cod;
		this.value = value;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Commodity)) {
			return false;
		}
		Commodity commodity = (Commodity) obj;
		return Objects.equals(cod, commodity.cod);
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", cod=" + cod + ", value=" + value + ", description=" + description + "]";
	}

}
