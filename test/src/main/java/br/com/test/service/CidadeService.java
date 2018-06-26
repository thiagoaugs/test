/**
 * 
 */
package br.com.test.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.test.entity.Cidade;
import br.com.test.entity.Estado;
import br.com.test.repository.CidadeRepository;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Local
@Stateless
public class CidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeRepository repository;

	@EJB
	private EstadoService estadoService;

	public void save(Cidade entity) {

		Estado estado = this.estadoService.load(entity.getEstado().getId());
		estado.setQuant(estado.getQuant() + 1);
		this.estadoService.update(estado);

		repository.save(entity);
	}

	public void delete(Cidade entity) {
		repository.delete(entity);
	}

	public Long count() {
		return repository.count();
	}

	public Cidade load(Long idIbge) {
		return repository.load(idIbge);
	}

	public List<Cidade> filter(Cidade filtros) {
		return repository.filter(filtros);
	}

	public List<Cidade> getCidadesByEstado(Long id) {

		Estado estado = new Estado();
		Cidade cidade = new Cidade();
		estado.setId(id);
		cidade.setEstado(estado);

		return repository.filter(cidade);
	}

	public List<Cidade> getCapitais() {
		Cidade filtros = new Cidade();
		filtros.setCapital("true");

		return repository.filter(filtros);
	}

	public List<Cidade> findBy(String propriedade, String valor) {
		return repository.findBy(propriedade, valor);
	}

	public Long countBy(String propriedade, String valor) {
		return repository.countBy(propriedade, valor);
	}

	
	public List<Cidade> getMoreDistance() {
		return new ArrayList<Cidade>();
	}
}
