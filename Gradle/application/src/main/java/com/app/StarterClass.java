package com.app;

import java.util.stream.*;
import java.util.*;
import com.srv.HorseRace;
import com.srv.HorseService;
import com.horse.Horse;
import org.apache.commons.lang3.RandomUtils;


public class StarterClass{
    static InputValidation validated = new InputValidation();

    public static void start() {
        HorseService horseService = new HorseService();
        HorseRace horseRace;
		List<Horse> horses;
		int horseAmount;
        System.out.println("Enter length of field");
        int fieldLength = validated.getIntegerInput(1, 500);

		do{
            System.out.println("Input amount of horses ");
			horseAmount = validated.getIntegerInput(2,50);
			horses = new ArrayList<Horse>();

			for (int i = 0; i < horseAmount; i++){
                horseService.setHorse(new Horse());
                RandomUtils.nextInt(1, 10 + 1);
                horseService.setHorseHealth((RandomUtils.nextInt(0,1 + 1) == 1) ? true : false);
                System.out.println("Input name of horse " + (i+1) );
                horseService.giveHorseName(Optional.ofNullable(validated.stringInput()), i+1);
                horseService.giveWarCry(RandomUtils.nextInt(0, horseService.getWarCrySize()));
                horses.add(horseService.getHorse());
			}
		    horseRace = new HorseRace(horses, fieldLength);

        } while (!horseRace.canStart());
		horseRace.start();
	}

}
