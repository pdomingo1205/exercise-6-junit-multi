package com.srv;

import java.util.stream.*;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalTime;
import com.horse.Horse;
import org.apache.commons.lang3.RandomUtils;




public class HorseService{
	public static String[] warcries = { "Let's get down to business!", "¿Que habla español?"
		, "Allahu Ackbar!", "Deus Vult!", "Punitin ang cedula!","To defeat the huns!"
		, "Grab a brush and put a little make up!"
		, "Hide the scars to fade away the shake up!"
		, "Why did you leave the keys upon the table!"
		, "Here you go creating another fable!"
		, "Mabuhay!"
		, "Alin lamang ba sa mga iyan ang ating nakamtan..."
		, "Consummatum Est."
		, null};

	Horse currHorse;

	public HorseService(){

	}

	public Horse getHorse(){
		return currHorse;
	}

	public void setHorse(Horse newHorse){
		currHorse = newHorse;
	}

	public String getHorseName(){
		return currHorse.getName();
	}

	public boolean getHealth(){
		return currHorse.isHealthy();
	}

	public int getWarCrySize(){
		return warcries.length;
	}

	public Optional<String> getWarCry(){
		return currHorse.getWarCry();
	}

	public void giveHorseName(Optional<String> horseName, int horseIndex){


		if(horseName.isPresent()){
			currHorse.setName(horseName.get());
		}
		else{
			currHorse.setName("Horse " + String.valueOf(horseIndex));
		}

		if(currHorse.isHealthy()){
			currHorse.setName(currHorse.getName().toUpperCase());
		}
	}

	public void setHorseHealth(boolean isHealthy){
		currHorse.setHealth(isHealthy);
	}

	public void giveWarCry(int index){
		currHorse.setWarCry(Optional.ofNullable(warcries[index]));
	}

	public void giveWarCry(Optional<String> newCry){
		currHorse.setWarCry(newCry);
	}

	public void setHorseDistRemain(int newDistance){
		currHorse.setDistRemain(newDistance);
	}

	public void setHorseDistanceTravelled(int newDistance){
		currHorse.setDistanceTravelled(newDistance);
	}

	public void cry(Horse horse){
		Optional<String> warCry = horse.getWarCry();
		String horseName = horse.getName();

		if (warCry.isPresent()){
			System.out.println(String.format("\n %s shouts \"%s\" \n", horseName, warCry.get()));
		}else {
			System.out.println(String.format("\n %s has no warcry. \n", horseName));
		}

	}

	public Horse run(Horse horse, int tempSpeed){

		if(tempSpeed > horse.getDistRemain()){
			tempSpeed = horse.getDistRemain();
		}

		horse.setSpeed(tempSpeed);
		horse.setDistanceTravelled(horse.getDistanceTravelled() + tempSpeed);
		horse.addDistBreakDown(tempSpeed);
		return horse;
	}

	public String runMessage(Horse horse, String dest){
		String outputText;
		if (dest.equals("Barn")) {
			outputText = (String.format(" %s Distance Left to barn: %d meters ", horse.getName(), horse.getDistRemain()));
		}
		else{
			outputText = (String.format(" %s has galloped: %d meters	|	Distance Traversed: %d meters 	|	Distance Left: %d meters",
											horse.getName(), horse.getSpeed(), horse.getDistanceTravelled(), horse.getDistRemain()));
		}
		return outputText;
	}

	public void runToGoal(Horse horse, int distance, String dest){
		int tempSpeed;
		horse.setDistRemain(distance);

		do {

			tempSpeed = RandomUtils.nextInt(1, 10 + 1);
			horse = run(horse, tempSpeed);
			horse.setDistRemain(distance - horse.getDistanceTravelled());

			System.out.println(runMessage(horse,dest));

		} while (horse.getDistanceTravelled() < distance);

		if (dest.equals("Barn")) {
			horse.setDistanceTravelled(0);
			System.out.println("\n" + horse.getName() + " has reached the Barn! \n");
		} else {
			cry(horse);
		}

	}

	public void printBreakDown(Horse horse){
		System.out.println(String.format(" %s travelled Distances: %s = %d ", horse.getName(), horse.getDistBreakDown(), horse.getDistanceTravelled()));
	}


}
