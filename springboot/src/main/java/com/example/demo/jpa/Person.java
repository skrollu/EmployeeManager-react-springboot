package com.example.demo.jpa;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "death_date")
	private Date deathDate;

	@Column(name = "father_id")
	private long fatherId;

	@Column(name = "mother_id")
	private long motherId;

	@Column(name = "legal_father_id")
	private long legalFatherId;

	@Column(name = "legal_mother_id")
	private long legalMotherId;

	@Column(name = "description")
	private String description;

	public Person() {
		super();
	}

	public Person(long id, String firstName, String lastName, Date birthDate, Date deathDate, long fatherId,
			long motherId, long legalFatherId, long legalMotherId, String description) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.fatherId = fatherId;
		this.motherId = motherId;
		this.legalFatherId = legalFatherId;
		this.legalMotherId = legalMotherId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public long getFatherId() {
		return fatherId;
	}

	public void setFatherId(long fatherId) {
		this.fatherId = fatherId;
	}

	public long getMotherId() {
		return motherId;
	}

	public void setMotherId(long motherId) {
		this.motherId = motherId;
	}

	public long getLegalFatherId() {
		return legalFatherId;
	}

	public void setLegalFatherId(long legalFatherId) {
		this.legalFatherId = legalFatherId;
	}

	public long getLegalMotherId() {
		return legalMotherId;
	}

	public void setLegalMotherId(long legalMotherId) {
		this.legalMotherId = legalMotherId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, deathDate, description, fatherId, firstName, id, lastName, legalFatherId,
				legalMotherId, motherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(deathDate, other.deathDate)
				&& Objects.equals(description, other.description) && fatherId == other.fatherId
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && legalFatherId == other.legalFatherId
				&& legalMotherId == other.legalMotherId && motherId == other.motherId;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", deathDate=" + deathDate + ", fatherId=" + fatherId + ", motherId=" + motherId + ", legalFatherId="
				+ legalFatherId + ", legalMotherId=" + legalMotherId + ", description=" + description + "]";
	}

}