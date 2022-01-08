package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.jpa.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.PersonRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		return ResponseEntity.ok(person);
	}

	@PostMapping
	public Person createPerson(@RequestBody @NonNull Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable @NonNull Long id, @RequestBody @NonNull Person person) {
		Person personToUpdate = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		personToUpdate = PersonMapper.mapPerson(person, personToUpdate);
		Person personUpdated = this.personRepository.save(personToUpdate);
		return ResponseEntity.ok(personUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable @NonNull Long id) {
		Person personToDelete = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		personRepository.delete(personToDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
