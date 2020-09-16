package com.revature.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Pokemon;

@Repository
@Transactional
public class PokemonDAO implements IPokemonDAO {

	private SessionFactory sf;
	
	public PokemonDAO() {
		super();
	};
	
	@Autowired
	public PokemonDAO(SessionFactory sf) {
		super();
		this.sf = sf;
	};
	
	@Override
	public Pokemon findById(int id) {
		Session ses = sf.getCurrentSession();
		return ses.get(Pokemon.class, id);
	}

	@Override
	public List<Pokemon> findAll() {
		Session ses = sf.getCurrentSession();
		CriteriaQuery<Pokemon> cq = ses.getCriteriaBuilder().createQuery(Pokemon.class);
		cq.from(Pokemon.class);
		return ses.createQuery(cq).getResultList();
	}

	@Override
	public Pokemon insert(Pokemon p) {
		Session ses = sf.getCurrentSession();
		ses.save(p);
		return this.findById(p.getPokeId());
	}

	@Override
	public boolean delete(Pokemon p) {
		Session ses = sf.getCurrentSession();
		if (this.findById(p.getPokeId()) != null) {
			ses.delete(p);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Pokemon update(Pokemon p) {
		Session ses = sf.getCurrentSession();
		ses.update(p);
		return this.findById(p.getPokeId());
	}

}
