package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.jpa.Person;

/**
 * Spring Repository annotation is a specialization of @Component annotation, so
 * Spring Repository classes are autodetected by spring framework through
 * classpath scanning.
 * 
 * @author Mathieu
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
