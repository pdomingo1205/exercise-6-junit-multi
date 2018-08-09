package com.app;

import java.util.stream.*;
import java.util.*;
import com.srv.HorseRace;
import com.srv.HorseService;
import com.srv.InputValidation;
import static com.srv.InputValidation.*;
import com.srv.IntegerAsker;
import com.horse.Horse;
import org.apache.commons.lang3.RandomUtils;
import static java.lang.Integer.parseInt;


public class StarterClassImplementation implements StarterClass{

    Scanner scan = new Scanner(System.in);
    HorseService horseService = new HorseService();
    HorseRace horseRace;
    Horse horse;
    IntegerAsker intAsker = new IntegerAsker(System.in, System.out);
    Integer horseAmount;
    Integer fieldLength;


    List<Horse> horses = new ArrayList<Horse>();


    public boolean tacTest(boolean hey){
        return hey;
    }

    public void start() {
        Horse horse;
        fieldLength = getInteger(1, 500);

		do {
            clearList();
            horseAmount = getInteger(2, 50);

			for (int i = 0; i < horseAmount; i++){
                horse = horseCreator(new Horse(), i+1, Optional.ofNullable(InputValidation.stringInput(scan.nextLine())));
                horses.add(horse);
			}

		    horseRace = new HorseRace(horses, fieldLength);

        } while (!horseRace.canStart());
		horseRace.start();
	}


    public Horse horseCreator(Horse newHorse, Integer index, Optional<String> name){
        System.out.println("Input name of horse " + (index.intValue()) );
        newHorse = (horseService.giveHorseName(newHorse, name, index));
        newHorse.setHealth(RandomUtils.nextBoolean());
		newHorse = horseService.giveWarCry(newHorse, RandomUtils.nextInt(0, horseService.warcries.size()));

        return newHorse;
    }

    private void clearList(){
        if(!horses.isEmpty()){
            horses.clear();
        }
    }

}
