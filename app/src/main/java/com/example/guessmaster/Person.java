package com.example.guessmaster;

public class Person extends Entity{
	//adds to entity plus extra string gender
	private String gender;
	
	public Person() {
		super();
		gender = "none";
	}
	
	public Person(Person person) {
		super(person);
		this.gender = person.gender;
	}
	
	public Person(String name, Date born, double difficulty, String gender) {
		super(name, born, difficulty);
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	//adds to opening message of entity
	public String entityType() {
		// TODO Auto-generated method stub
		return ("This entity is a person!");
	}

	@Override
	public Person clone() {
		Person clone  = new Person(super.getName(), super.getBirth(),super.getDifficulty(), gender);
		return clone;
	}
	
	//adds to closing message of entity;
	public String toString() {
		return (super.toString() + "Gender: " + gender);
	}
	
}
