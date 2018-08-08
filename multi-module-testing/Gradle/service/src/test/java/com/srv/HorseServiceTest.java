package com.srv;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.srv.HorseService;
import com.horse.Horse;

public class HorseServiceTest
    {

        static HorseService hService = new HorseService();

        @Before
        public void setUp(){
            Horse horse = new Horse();
            hService.setHorse(horse);
        }

        @Test
        public void testNameIfHealthy() {
            hService.setHorseHealth(true);
            hService.giveHorseName(Optional.ofNullable("Amigo"), 0);
            assertEquals("Checking if name is AMIGO", "AMIGO", hService.getHorseName());
        }

        @Test
        public void TestNameIfHealthyAndNull(){
            hService.setHorseHealth(true);
            hService.giveHorseName(Optional.ofNullable(null), 0);
            assertEquals("Checking if name is HORSE 0", "HORSE 0", hService.getHorseName());
        }

        @Test
        public void testNameIfUnhealthy(){
            hService.setHorseHealth(false);
            hService.giveHorseName(Optional.ofNullable("Amigo"), 3);
            assertEquals("Checking if name is Amigo", "Amigo", hService.getHorseName());
        }

        @Test
        public void TestNameIfUnhealthyAndNull(){
            hService.setHorseHealth(false);
            hService.giveHorseName(Optional.ofNullable(null), 1);
            assertEquals("Checking if name is Horse 1", "Horse 1", hService.getHorseName());
        }

        @Test
        public void testWarCryIfNull(){
            hService.giveWarCry(null);
            assertNull(hService.getWarCry());
        }
        @Test
        public void testHorseRun(){
            hService.setHorseDistRemain(20);
            hService.run(hService.getHorse(), 5);
            hService.run(hService.getHorse(), 9);
            hService.run(hService.getHorse(), 2);
            assertEquals("Checking if Distance Travelled is 16", 16, hService.getHorse().getDistanceTravelled());
        }
        @Test
        public void testHorseRunIfDistanceBelowTen(){
            hService.setHorseDistanceTravelled(0);
            hService.setHorseDistRemain(5);
            hService.run(hService.getHorse(), 7);
            assertEquals("Checking if Distance Travelled is 5", 5, hService.getHorse().getDistanceTravelled());
        }

        @Test
        public void testRunMessageIfDestinationBarn(){
            hService.setHorseDistRemain(20);
            hService.giveHorseName(Optional.ofNullable(null), 0);
            assertEquals("Checking if message is ' Horse 0 Distance Left to barn: 20 meters '",  " Horse 0 Distance Left to barn: 20 meters ", hService.runMessage(hService.getHorse(),"Barn"));
        }

        @Test
        public void testRunMessageIfDestinationFinish(){
            hService.setHorseDistRemain(20);
            hService.giveHorseName(Optional.ofNullable(null), 0);
            assertEquals("Checking if message is ' Horse 0 has galloped: 0 meters	|	Distance Traversed: 0 meters 	|	Distance Left: 20 meters'",  " Horse 0 has galloped: 0 meters	|	Distance Traversed: 0 meters 	|	Distance Left: 20 meters", hService.runMessage(hService.getHorse(),"Finish"));
        }



    }
