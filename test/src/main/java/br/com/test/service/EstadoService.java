/**
 * 
 */
package br.com.test.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.test.entity.Estado;
import br.com.test.repository.EstadoRepository;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Local
@Stateless
public class EstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepository repository;

	public void save(Estado entity) {
		repository.save(entity);
	}

	public void update(Estado entity) {
		repository.update(entity);
	}

	public List<Estado> loadAll() {
		return repository.loadAll();
	}

	public Estado load(Long id) {
		return repository.load(id);
	}
	
	public Estado loadByName(String nome) {
		return repository.loadByName(nome);
	}

	public List<Estado> getEstadosMaiorMenor() {
		List<Estado> lista = new ArrayList<Estado>();
		List<Estado> listaMaior = repository.getEstadoMaior();
		List<Estado> listaMenor = repository.getEstadoMenor();

		lista.add(listaMaior.get(0));
		lista.add(listaMenor.get(0));
		
		return lista;
	}
}
