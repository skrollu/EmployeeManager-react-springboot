package com.example.demo.mapper;

import com.example.demo.jpa.Person;

public class PersonMapper {

	/**
	 * Map person into a new Person entity without the id
	 * 
	 * @param person
	 * @return
	 */
	public static Person mapPerson(Person person, Person result) {
		result.setFirstName(person.getFirstName());
		result.setLastName(person.getLastName());
		result.setBirthDate(person.getBirthDate());
		result.setDeathDate(person.getDeathDate());
		result.setFatherId(person.getFatherId());
		result.setMotherId(person.getMotherId());
		result.setLegalFatherId(person.getLegalFatherId());
		result.setLegalMotherId(person.getLegalMotherId());
		result.setDescription(person.getDescription());

		return result;
	}
}
