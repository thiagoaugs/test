/**
 * 
 */
package br.com.test.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.test.entity.Estado;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Named
public class EstadoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Estado entity) {
		this.entityManager.persist(entity);
	}
	
	public void update(Estado entity) {
		this.entityManager.merge(entity);
	}

	public List<Estado> loadAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> query = builder.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);
		query.select(root);

		TypedQuery<Estado> typedQuery = this.entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	public Estado load(Long id) {
		return this.entityManager.find(Estado.class, id);
	}
	
	public Estado loadByName(String nome) {
		return this.entityManager.find(Estado.class, nome);
	}
	
	
	public List<Estado> getEstadoMaior() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> query = builder.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);
		query.select(root);

		TypedQuery<Estado> typedQuery = this.entityManager.createQuery(query);

		query.orderBy(builder.asc(root.get("quant")));
		typedQuery.setMaxResults(1);

		return typedQuery.getResultList();
	}

	public List<Estado> getEstadoMenor() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> query = builder.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);
		query.select(root);

		TypedQuery<Estado> typedQuery = this.entityManager.createQuery(query);

		query.orderBy(builder.desc(root.get("quant")));
		typedQuery.setMaxResults(1);

		return typedQuery.getResultList();
	}
	
}
