package com.example.guessmaster;

public abstract class Entity {
	private String name;
	private	Date born;
	//difficulty a number between 0 - 1
	private double difficulty;
	
	//Default entity constructor
	public Entity() {
		Date born = new Date();
		this.born = born;
		this.name = "Jon Doe";
		this.difficulty = 0;
	}
	
	
	public Entity(String name, Date born, double difficulty) {
        if (born == null)//Not a real date.
        {
             System.out.println("Fatal Error.");
             System.exit(0);
        }
		this.name = name;
		this.born = born;
		this.difficulty = difficulty;
	}
	
	//copy constructor
	public Entity(Entity entity) {
		this.name = entity.name;
		this.difficulty = entity.difficulty;
		this.born = new Date(entity.born);
	}
	
	public void setBirth(Date born) {
        if (born == null)//Not a real date.
        {
             System.out.println("Fatal Error.");
             System.exit(0);
        }
		this.born = born;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	
	public Date getBirth() {
		return born;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDifficulty() {
		return difficulty;
	}
	
	
	public String toString() {
		String date = born.toString();
		return (name + " born on " + date + "\n");
	}
	
	public boolean equals(Entity entity) {
		if (entity.born.equals(born) && entity.name.equals(name)) {
			return true;
		}
		else 
			return false;
	}
	
	//returns tickets earned based on difficulty
	public int getAwardedTicketNumber() {
		
		return (int) (difficulty*100);
	}
	
	//abstract methods
	public abstract String entityType();
	
	public abstract Entity clone();
	
	//messages
	public String welcomeMessage() {
		return ("\nWelcome! Let's start the game! " + entityType() + "\n");
	}
	
	public String closingMessage() {
		return ("Congratulations! The detailed informtation of the entity you guessed is:\n" + toString());
	}
	
}


