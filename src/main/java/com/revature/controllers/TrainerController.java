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

import com.revature.models.Trainer;
import com.revature.repositories.ITrainerDAO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value="/trainer")
@ResponseBody
public class TrainerController {
	
	private ITrainerDAO dao;
	
	@Autowired
	public TrainerController(ITrainerDAO dao) {
		super();
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
//  @ResponseBody
	public List<Trainer> getAll() {
		return dao.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Trainer> findById(@PathVariable("id") int id) {
		Trainer a = dao.findById(id);
		if (a == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
	}
	
	@PostMapping
	public List<Trainer> add(@RequestBody Trainer p) {
		p = dao.insert(p);
		return dao.findAll();
	}

}
