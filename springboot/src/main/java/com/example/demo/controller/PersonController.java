package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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

import com.example.demo.controller.assembler.PersonModelAssembler;
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

	private final PersonModelAssembler personAssembler;

	PersonController(PersonRepository personRepository, PersonModelAssembler personAssembler) {
		this.personRepository = personRepository;
		this.personAssembler = personAssembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Person>> getAll() {

		List<EntityModel<Person>> persons = personRepository.findAll().stream().map(personAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<Person> getById(@PathVariable Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		return personAssembler.toModel(person);
	}

	/**
	 * Spring MVC’s ResponseEntity is used to create an HTTP 201 Created status
	 * message. This type of response typically includes a Location response header,
	 * and we use the URI derived from the model’s self-related link.
	 * 
	 * @param person
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> createPerson(@RequestBody @NonNull Person person) {
		EntityModel<Person> entityModel = personAssembler.toModel(personRepository.save(person));
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable @NonNull Long id, @RequestBody @NonNull Person person) {
		Person personToUpdate = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		personToUpdate = PersonMapper.mapPerson(person, personToUpdate);

		EntityModel<Person> entityModel = personAssembler.toModel(personRepository.save(personToUpdate));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	/**
	 * This returns an HTTP 204 No Content response.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable @NonNull Long id) {
		Person personToDelete = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist with this id " + id));
		personRepository.delete(personToDelete);
		return ResponseEntity.noContent().build();
	}
}
