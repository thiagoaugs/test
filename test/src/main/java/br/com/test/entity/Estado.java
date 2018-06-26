package br.com.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Entity
@Table(name = "ESTADO")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private long id;
	@Column
	private String nome;
	@Column
	private Integer quant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

}
