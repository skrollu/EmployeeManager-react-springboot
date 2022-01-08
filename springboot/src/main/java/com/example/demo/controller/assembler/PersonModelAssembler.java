package com.example.demo.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.demo.controller.PersonController;
import com.example.demo.jpa.Person;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

	@Override
	public EntityModel<Person> toModel(Person person) {

		return EntityModel.of(person, //
				linkTo(methodOn(PersonController.class).getById(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).getAll()).withRel("persons"));
	}
}