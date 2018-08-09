package com.srv;

import java.util.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import com.srv.HorseService;
import com.horse.Horse;

public class HorseRaceTest
    {

        protected static HorseService horseService = new HorseService();
        protected static List<Horse> horses = new ArrayList<Horse>();

        @Test
        public void test_RaceCanStart_IfTwoHealthy() {
            Horse horse;

            for (int i = 0; i < 2; i++){
                horse = new Horse();
                horse.setHealth(true);
                horse = (horseService.giveHorseName(horse, Optional.ofNullable(null), i+1));
                horses.add(horse);
            }
            HorseRace horseRace = new HorseRace(horses, 10);
            assertTrue(horseRace.canStart());
        }

        @After
        public void emptyList(){
            horses.clear();
        }

        @Test
        public void test_RaceCantStart_IfZeroHealthy() {
            Horse horse;

            for (int i = 0; i < 2; i++){
                horse = new Horse();
                horse.setHealth(false);
                horse = (horseService.giveHorseName(horse, Optional.ofNullable(null), i+1));
                horses.add(horse);
            }
            HorseRace horseRace = new HorseRace(horses, 10);
            assertFalse(horseRace.canStart());
        }

    }
