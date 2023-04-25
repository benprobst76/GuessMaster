package com.example.guessmaster;

public class Singer extends Person{
	//includes person plus string debut album and date release date 
	private String debutAlbum;
	private Date debutAlbumReleaseDate;
	
	public Singer() {
		super();
		debutAlbum = "none";
		debutAlbumReleaseDate = new Date(1,1,1000);
	}
	
	public Singer(Singer singer) {
		super(singer);
		this.debutAlbum = singer.debutAlbum;
		this.debutAlbumReleaseDate = new Date(singer.debutAlbumReleaseDate);
	}
	
	public Singer(String name, Date born, double difficulty, String gender, String debutAlbum, Date debutAlbumReleaseDate) {
		super(name, born, difficulty, gender);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate =  new Date(debutAlbumReleaseDate);
	}
	
	public String getDebutAlbum() {
		return debutAlbum;
	}
	
	public Date getDebutAlbumRealeaseDate() {
		return new Date(debutAlbumReleaseDate);
	}
	
	public void setDebutAlbum(String debutAlbum) {
		this.debutAlbum = debutAlbum;
	}
	
	public void setDebutAlbumReleaseDate(Date debutAlbumReleaseDate) {
		this.debutAlbumReleaseDate = debutAlbumReleaseDate;
	}
	
	@Override
	//adds to entity opening message
	public String entityType() {
		return ("This entity is a singer!");
	}

	@Override
	public Singer clone() {
		Singer clone  = new Singer(super.getName(), super.getBirth(),super.getDifficulty(), super.getGender(), debutAlbum, debutAlbumReleaseDate);
		return clone;
	}
	
	//adds to entity closing message
	public String toString() {
		return (super.toString() + "\nAlbum: " + debutAlbum + "\nReleased on " + debutAlbumReleaseDate);
	}
}
