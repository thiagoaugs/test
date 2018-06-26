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
@Table(name = "CIDADE")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ibge_id")
	private Long ibgeId;

	@Column(name = "uf_id")
	private Estado estado;

	@Column(name = "uf_id")
	private String nome;

	@Column(name = "uf_id")
	private String capital;

	@Column(name = "lat")
	private Float latitude;
	
	@Column(name = "lon")
	private Float longitude;

	@Column(name = "no_accents")
	private String noAccents;
	
	@Column(name = "alternative_names")
	private String alternativeNames;

	@Column(name = "microregion")
	private String microregion;
	
	@Column(name = "macroregion")
	private String macroregion;

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMacroregion() {
		return macroregion;
	}

	public void setMacroregion(String macroregion) {
		this.macroregion = macroregion;
	}

}
