package com.srv;

import java.util.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import com.srv.HorseService;
import com.horse.Horse;

public class HorseRaceTest
    {

        static HorseService horseService = new HorseService();
        static List<Horse> horses = new ArrayList<Horse>();

        @Test
        public void testRaceIfTwoHealthy() {
            for (int i = 0; i < 2; i++){
                horseService.setHorse(new Horse());
                horseService.setHorseHealth(true);
                horseService.giveHorseName(Optional.ofNullable(null), i+1);
                horses.add(horseService.getHorse());
            }
            HorseRace horseRace = new HorseRace(horses, 10);
            assertTrue(horseRace.canStart());
        }
        @After

        public void emptyList(){
            horses.clear();
        }

        @Test
        public void testRaceIfZeroHealthy() {
            for (int i = 0; i < 2; i++){
                horseService.setHorse(new Horse());
                horseService.setHorseHealth(false);
                horseService.giveHorseName(Optional.ofNullable(null), i+1);
                horses.add(horseService.getHorse());
            }
            HorseRace horseRace = new HorseRace(horses, 10);
            assertFalse(horseRace.canStart());
        }


    }
