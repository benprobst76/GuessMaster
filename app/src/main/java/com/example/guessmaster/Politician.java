package com.example.guessmaster;

public class Politician extends Person{
	//includes person plus sting party
	private String party;
	
	public Politician() {
		super();
		party = "none";
	}
	
	public Politician(Politician politician) {
		super(politician);
		this.party = politician.party;
	}
	
	public Politician(String name, Date born, double difficulty, String gender, String party) {
		super(name, born, difficulty, gender);
		this.party = party;
	}
	
	public String getParty() {
		return party;
	}
	
	public void setParty(String party) {
		this.party = party;
	}
	
	@Override
	//adds to entity opening message
	public String entityType() {
		// TODO Auto-generated method stub
		return ("This entity is a politician!");
	}

	@Override
	public Politician clone() {
		Politician clone  = new Politician(super.getName(), super.getBirth(),super.getDifficulty(), super.getGender(), party);
		return clone;
	}
	
	//adds to entity closing message
	public String toString() {
		return (super.toString() + "\nParty: " + party);
	}
	
}
