package com.example.guessmaster;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Scanner;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.*;
import android.content.DialogInterface;

public class GuessMaster extends AppCompatActivity {
	private int numberOfCandidateEntities;
	private int tickets = 0;
	private Entity[] entities;
	private	TextView entityName;
	private	TextView ticketSum;
	private Button guessButton;
	private EditText userIn;
	private	Button btnclearContent;
	private String user_input;
	private ImageView entityImage;
	private int index;
	String answer;
	//initiate entities to guess from
	Politician trudeau =  new Politician("Justin Trudeau", new Date("December", 25, 1971), 0.25, "Male", "Liberal");
	Singer dion  = new Singer("Celine Dion", new Date("March", 30, 1968), 0.5, "Female", "La voix du bon Dieu", new Date("November", 6, 1981));
	Country usa = new Country("United States", new Date("July", 4, 1776), 0.1, "Washington D.C");
	Person myCreator =  new Person("Ben Probst", new Date("June", 16, 2003), 1, "Male");


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess_activity);
		guessButton = (Button) findViewById(R.id.btnGuess);
		userIn = (EditText) findViewById(R.id.guessinput);
		ticketSum = (TextView) findViewById(R.id.ticket);
		entityName = (TextView) findViewById(R.id.entityName);
		btnclearContent = (Button) findViewById(R.id.btnClear);
		user_input = userIn.toString();
		entityImage = (ImageView) findViewById(R.id.entityImage);

		this.addEntity(this.trudeau);
		this.addEntity(this.usa);
		this.addEntity(this.dion);
		this.addEntity(this.myCreator);
		AlertDialog.Builder alert = new AlertDialog.Builder(GuessMaster.this);
		this.playGame(alert);

		//OnClick Listener action for clear button
		btnclearContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				changeEntity();
			}
		});

		//OnClick Listener action for submit button
		guessButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				playGame(index);
			}
		});
	}
	private void changeEntity() {
		userIn.getText().clear();
		index = genRandomEntityInd();
		playGame(index);
	}

	private void ImageSetter(){
		if (entityName.getText().equals("Justin Trudeau")){
			entityImage.setImageResource(R.drawable.justint);
		} else if (entityName.getText().equals(("Celine Dion"))){
			entityImage.setImageResource(R.drawable.celidion);
		} else if (entityName.getText().equals("United States")){
			entityImage.setImageResource(R.drawable.usaflag);
		} else {
			entityImage.setImageResource(R.drawable.creator);
		}
	}
	public GuessMaster() {
		numberOfCandidateEntities = 0;
		entities = new Entity[100];
	}
	
	public GuessMaster(int numOfEnt) {
		numberOfCandidateEntities = numOfEnt;
		entities = new Entity[numOfEnt];
	}
	
	public GuessMaster(int numOfEnt, Entity[]  entities) {
		numberOfCandidateEntities = numOfEnt;
		this.entities = entities;
	}
	
	public void setNumCandidates(int num) {
		numberOfCandidateEntities = num;
	}
	
	public void setEntities(Entity[] entities) {
		this.entities = entities;
	}
	
	public int getNumCandidates() {
		return numberOfCandidateEntities;
	}
	
	public Entity[] getEntities() {
		return entities;
	}
	
	//add entity to entities list using clone corresponding to the appropriate subclass 
	public void addEntity(Entity entity) {
		Entity copy = entity.clone();
		entities[numberOfCandidateEntities++] = copy;
	}
	
	public void playGame(Entity entity) {

		// welcome message corresponding to the entity type
		//Name of the entity to be guessed in the entiyName textview
		entityName.setText(entity.getName());
		ImageSetter();
		//Get Input from the EditText
		answer = userIn.getText().toString();
		answer = answer.replace("\n", "").replace("\r", "");
		if (!answer.equals("")) {
			Date date = new Date(answer);
			AlertDialog.Builder answeralert = new AlertDialog.Builder(GuessMaster.this);
			//player guessed correctly
			if (date.equals(entity.getBirth())) {
				answeralert.setTitle(("You won"));
				answeralert.setMessage("BINGO! " + entity.closingMessage());
				answeralert.setNegativeButton("Continue ", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						Toast.makeText(getBaseContext(),"You got "+entity.getAwardedTicketNumber()+" tickets!", Toast.LENGTH_SHORT).show();
						tickets += entity.getAwardedTicketNumber();
						ticketSum.setText("Tickets " + tickets);
						//play game again with new entity
						ContinueGame();
					}
				});
			} else if (date.precedes(entity.getBirth())) {
				answeralert.setTitle(("Incorrect"));
				answeralert.setMessage("Try a later date");
				answeralert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						userIn.setText("");
						playGame(index);
					}
				});
			} else {
				answeralert.setTitle(("Incorrect"));
				answeralert.setMessage("Try an earlier date");
				answeralert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						userIn.setText("");
						playGame(index);
					}
				});
			}
			AlertDialog dialog = answeralert.create();
			dialog.show();
		}
	}

	public void ContinueGame() {
		index = genRandomEntityInd();
		Entity entity = entities[index];
		String entName = entity.getName();
		//Print the name of the entity to be guessed
		//in the entityName textview
		entityName.setText(entName);
		//Call the ImageSetter method
		ImageSetter();
		//Clear Previous Entry
		userIn.getText().clear();
	}

	// verify entity index is in the entities list
	public void playGame(int entityInd) {
		if (entityInd >= numberOfCandidateEntities || entityInd < 0) {
			System.out.print("Out of bounds");
			System.exit(0);
		}
		playGame(entities[entityInd]);
	}
	
	public void playGame(AlertDialog.Builder alert) {
		index = genRandomEntityInd();
		if (index >= numberOfCandidateEntities || index < 0) {
			System.out.print("Out of bounds");
			System.exit(0);
		}
		welcomeToGame(entities[index], alert);
		playGame(entities[index]);
	}
	
	//get random index to output a random entity to guess
	public int genRandomEntityInd() {
		Random randomNumber = new Random();
		return  randomNumber.nextInt(numberOfCandidateEntities);
	}

	public void welcomeToGame(Entity Entity, AlertDialog.Builder alert){

		alert.setTitle(("GuessMaster_Game_V3"));
		alert.setMessage(Entity.welcomeMessage());
		alert.setCancelable(false);

		alert.setNegativeButton("START_GAME", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Game_is_Starting...Enjoy", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = alert.create();
		dialog.show();
	}

	public static void main(String[] args) {

	}
}




