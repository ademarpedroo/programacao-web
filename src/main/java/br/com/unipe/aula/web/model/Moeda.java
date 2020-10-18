package br.com.unipe.aula.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Moeda {

	private BigDecimal valorDolares;

	public Moeda() {
	}

	public Moeda(BigDecimal valorDolares) {
		super();
		this.valorDolares = valorDolares;
	}

	public BigDecimal getValorDolares() {
		return valorDolares.setScale(2, RoundingMode.HALF_EVEN);
	}

	public void setValorDolares(BigDecimal valorDolares) {
		this.valorDolares = valorDolares;
	}

	public BigDecimal getValorReais() {
		return valorDolares.multiply(new BigDecimal(5.40)).setScale(2, RoundingMode.HALF_EVEN);
	}

}
