package com.example.guessmaster;

public class Country extends Entity{
	//includes entity plus extra string capital
	private String capital;
	
	public Country() {
		super();
		capital = "none";
	}
	
	public Country(Country country) {
		super(country);
		this.capital = country.capital;
	}
	
	public Country(String name, Date born, double difficulty, String capital) {
		super(name, born, difficulty);
		this.capital = capital;
	}
	
	@Override
	//adds to opening message of entity
	public String entityType() {
		// TODO Auto-generated method stub
		return ("This entity is a country");
	}

	@Override
	public Country clone() {
		Country clone  = new Country(super.getName(), super.getBirth(),super.getDifficulty(), capital);
		return clone;
	}
	
	//adds to closing message of entity
	public String toString() {
		return (super.toString() + "Capital: " + capital);
	}
	
}
