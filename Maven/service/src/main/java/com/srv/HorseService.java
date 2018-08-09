package com.srv;

import java.util.stream.*;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalTime;
import com.horse.Horse;
import org.apache.commons.lang3.RandomUtils;




public class HorseService{


public ArrayList<String> warcries = new ArrayList<String>() {{
	add("Let's get down to business!");
	add("¿Que habla español?");
	add("Allahu Ackbar!");
	add(null);
}};


	/*
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
*/

	//Horse currHorse;
	String destination;

	public HorseService(){

	}

	public Horse giveHorseName(Horse currHorse, Optional<String> horseName, Integer horseIndex){

		if(horseName.isPresent()){
			currHorse.setName(horseName.get());
		}
		else{
			currHorse.setName("Horse " + (horseIndex.intValue()));
		}

		if(currHorse.isHealthy()){
			currHorse.setName(currHorse.getName().toUpperCase());
		}
		return currHorse;
	}

	public Horse giveWarCry(Horse currHorse, Integer index){
		//currHorse.setWarCry();
		Optional<String> warCry = Optional.ofNullable(warcries.get(index.intValue()));
		String horseName = currHorse.getName();

		if (warCry.isPresent()){
			currHorse.setWarCry(String.format("\n %s shouts \"%s\" \n", horseName, warCry.get()));
		}else {
			currHorse.setWarCry(String.format("\n %s has no warcry. \n", horseName));
		}

		return currHorse;
	}

	public void setDest(String newDest){
		destination = newDest;
	}

	public String cry(Horse horse){
		return horse.getWarCry();
	}

	public Horse run(Horse horse, Integer tempSpeed){

		if(tempSpeed > horse.getDistRemain().intValue()){
			tempSpeed = horse.getDistRemain().intValue();
		}

		horse.setSpeed(tempSpeed.intValue());
		horse.setDistanceTravelled(horse.getDistanceTravelled().intValue() + tempSpeed.intValue());
		if(destination.equals("Barn") == false){
			horse.addDistBreakDown(tempSpeed.intValue());
		}

		return horse;
	}

	public String runMessage(Horse horse){
		String outputText;
		if (destination.equals("Barn")) {
			outputText = (String.format(" %s Distance Left to barn: %d meters ", horse.getName(), horse.getDistRemain()));
		}
		else{
			outputText = (String.format(" %s has galloped: %d meters	|	Distance Traversed: %d meters 	|	Distance Left: %d meters",
											horse.getName(), horse.getSpeed().intValue(), horse.getDistanceTravelled(), horse.getDistRemain()));
		}
		return outputText;
	}

	public Horse endRun(Horse horse){
		if (destination.equals("Barn")) {
			horse.setDistanceTravelled(0);
			System.out.println("\n" + horse.getName() + " has reached the Barn! \n");
		} else {
			System.out.println(cry(horse));
		}
		return horse;
	}

	public void runToGoal(Horse horse, Integer distance, String dest){
		Integer tempSpeed;
		horse.setDistRemain(distance);
		destination = dest;
		do {

			tempSpeed = RandomUtils.nextInt(1, 10 + 1);
			horse = run(horse, tempSpeed);
			horse.setDistRemain(distance - horse.getDistanceTravelled().intValue());
			System.out.println(runMessage(horse));

		} while (horse.getDistanceTravelled().intValue() < distance);

		horse = endRun(horse);

	}

	public String printBreakDown(Horse horse){
		return (String.format(" %s travelled Distances: %s = %d ", horse.getName(), horse.getDistBreakDown(), horse.getDistanceTravelled().intValue()));
	}


}
