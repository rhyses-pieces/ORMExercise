package com.revature.repositories;

import java.util.List;

import com.revature.models.Trainer;

public interface ITrainerDAO {
	
	public Trainer findById(int id);
	public List<Trainer> findAll();
	public Trainer insert(Trainer t);
	public Trainer update(Trainer t);
	public boolean delete(Trainer t);
	
	
}
