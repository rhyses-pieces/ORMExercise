package com.revature.repositories;

import java.util.List;

import com.revature.models.Pokemon;

public interface IPokemonDAO {

	public Pokemon findById(int id);
	
	public List<Pokemon> findAll();
	
	public Pokemon insert(Pokemon p);
	
	public boolean delete(Pokemon p);
	
	public Pokemon update(Pokemon p);

}
