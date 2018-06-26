package br.com.test.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.test.entity.Cidade;
import br.com.test.entity.Estado;

/**
 * @author Thiago Silva - AMCOM
 *
 */
@Named
public class CidadeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Cidade entity) {
		this.entityManager.persist(entity);

	}

	public void delete(Cidade entity) {
		this.entityManager.remove(entity);

	}

	public Long count() {
		return this.entityManager.createQuery("select count(c) from Cidade c", Long.class).getSingleResult();
	}

	public Cidade load(Long idIbge) {
		return this.entityManager.find(Cidade.class, idIbge);
	}

	public List<Cidade> filter(Cidade filtros) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Cidade> query = builder.createQuery(Cidade.class);
		Root<Cidade> root = query.from(Cidade.class);
		query.select(root);

		List<Predicate> predicates = setWhereClauses(filtros, builder, root);

		if (!predicates.isEmpty()) {
			query.where(predicates.toArray(new Predicate[] {}));
		}

		query.orderBy(builder.asc(root.get("nome")));
		TypedQuery<Cidade> typedQuery = this.entityManager.createQuery(query);

		return typedQuery.getResultList();
	}

	private List<Predicate> setWhereClauses(Cidade filtros, CriteriaBuilder builder, Root<Cidade> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (Long.valueOf(filtros.getEstado().getId()) != null) {
			Join<Cidade, Estado> joinEstado = root.join("estado", JoinType.INNER);
			javax.persistence.criteria.Expression<String> attribute = joinEstado.get("id");
			predicates.add(builder.and(builder.equal((attribute), filtros.getEstado().getId())));

		}

		if (filtros.getCapital() != null) {
			javax.persistence.criteria.Expression<String> attribute = root.get("capital");
			predicates.add(builder.and(builder.equal((attribute), filtros.getCapital())));

		}

		return predicates;
	}

	public List<Cidade> findBy(String propriedade, String valor) {
		StringBuilder sb = new StringBuilder();
		sb.append("select from Cidade where ");
		sb.append(propriedade);
		sb.append(" = :valor");
		TypedQuery<Cidade> query = this.entityManager.createQuery(sb.toString(), Cidade.class);
		query.setParameter("valor", valor);
		return query.getResultList();
	}

	
	public Long countBy(String propriedade, String valor) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(idIbge) from Cidade where ");
		sb.append(propriedade);
		sb.append(" = :valor");
		TypedQuery<Long> query = this.entityManager.createQuery(sb.toString(), Long.class);
		query.setParameter("valor", valor);
		return query.getSingleResult();
	}
	

}
