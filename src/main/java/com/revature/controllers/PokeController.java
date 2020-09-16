package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.repositories.IPokemonDAO;
import com.revature.repositories.ITrainerDAO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value = "/pokemon")
@ResponseBody
public class PokeController {

	private IPokemonDAO dao;
	private ITrainerDAO tdao;

	@Autowired
	public PokeController(IPokemonDAO dao, ITrainerDAO tdao) {
		super();
		this.dao = dao;
		this.tdao = tdao;
	}

	@RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
	public List<Pokemon> getAll() {
		return dao.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pokemon> findById(@PathVariable("id") int id) {
		Pokemon a = dao.findById(id);
		if (a == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
	}
	
	@PostMapping
	public List<Trainer> add(@RequestBody Pokemon p) {
		p = dao.insert(p);
		return tdao.findAll();
	}
	
}