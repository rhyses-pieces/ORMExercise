package com.revature.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Trainer;

@Repository
@Transactional
public class TrainerDAO implements ITrainerDAO {

	private SessionFactory sf;
	
	public TrainerDAO() {
		super();
	}
	
	@Autowired
	public TrainerDAO(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	@Override
	public Trainer findById(int id) {
		Session ses = sf.getCurrentSession();
		return ses.get(Trainer.class, id);
	}

	@Override
	public List<Trainer> findAll() {
		Session ses = sf.getCurrentSession();
		CriteriaQuery<Trainer> cq = ses.getCriteriaBuilder().createQuery(Trainer.class);
		cq.from(Trainer.class);
		return ses.createQuery(cq).getResultList();
	}

	@Override
	public Trainer insert(Trainer t) {
		Session ses = sf.getCurrentSession();
		ses.save(t);
		return this.findById(t.gettId());
	}

	@Override
	public boolean delete(Trainer t) {
		Session ses = sf.getCurrentSession();
		if (this.findById(t.gettId()) != null) {
			ses.delete(t);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Trainer update(Trainer t) {
		Session ses = sf.getCurrentSession();
		ses.update(t);
		return this.findById(t.gettId());
	}

}
