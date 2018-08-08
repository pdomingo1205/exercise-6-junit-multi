package com.srv;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import com.srv.HorseService;
import com.horse.Horse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HorseServiceTest
    {

        protected static HorseService hService = new HorseService();
        Horse horse;

        @Before
        public void setUp(){
            horse = new Horse();
        }

        @Test
        public void test_NameShouldBeUppercase_IfHealthy() {
            horse.setHealth(true);
            horse = (hService.giveHorseName(horse, Optional.ofNullable("Amigo"), 0));
            assertEquals("Checking if name is AMIGO", "AMIGO", horse.getName());
        }

        @Test
        public void test_NameShouldBeUppercase_IfHealthyAndNull(){
            horse.setHealth(true);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            assertEquals("Checking if name is HORSE 0", "HORSE 0",  horse.getName());
        }

        @Test
        public void test_Name_IfUnhealthy(){
            horse.setHealth(false);
            horse = (hService.giveHorseName(horse, Optional.ofNullable("Amigo"), 3));
            assertEquals("Checking if name is Amigo", "Amigo", horse.getName());
        }

        @Test
        public void test_NameShouldBeDefault_IfUnhealthyAndNull(){
            horse.setHealth(false);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 1));
            assertEquals("Checking if name is Horse 1", "Horse 1", horse.getName());
        }

        @Test
        public void test_HorseRun_IfDistanceGreaterThanTen_ShouldNotLimitSpeed(){
            hService.setDest("Finish");
            horse.setDistRemain(20);
            hService.run(horse, 5);
            hService.run(horse, 9);
            hService.run(horse, 2);
            assertEquals("Checking if Distance Travelled is 16", 16, horse.getDistanceTravelled().intValue());
        }
        @Test
        public void testHorseRun_IfDistanceBelowTen_ShouldLimitSpeed(){
            hService.setDest("Finish");
            horse.setDistanceTravelled(0);
            horse.setDistRemain(5);
            hService.run(horse, 7); //Horse ran 7 meters but distance left is 5 meters.
            assertEquals("Checking if Distance Travelled is 5", 5, horse.getDistanceTravelled().intValue()); //Travelled distance should be limited to distance left
        }

        @Test
        public void test_RunMessage_IfDestinationBarn(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Barn");
            assertEquals("Checking if message is ' Horse 0 Distance Left to barn: 20 meters '",
            " Horse 0 Distance Left to barn: 20 meters ", hService.runMessage(horse));
        }

        @Test
        public void test_RunDistance_IfBarn_BreakDownShouldBeEmpty(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Barn");
            hService.run(horse, 7);
            assertTrue(horse.getDistBreakDown().isEmpty());
        }

        @Test
        public void test_RunDistance_IfFinish_BreakDownShouldNotBeEmpty(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Finish");
            hService.run(horse, 7);
            assertFalse(horse.getDistBreakDown().isEmpty());
        }



        @Test
        public void test_RunMessage_IfDestinationFinish(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Finish");
            hService.run(horse, 0);

            assertEquals(
            "Checking if message is ' Horse 0 has galloped: 0 meters	|	Distance Traversed: 0 meters 	|	Distance Left: 20 meters'",
            " Horse 0 has galloped: 0 meters	|	Distance Traversed: 0 meters 	|	Distance Left: 20 meters",
             hService.runMessage(horse));
        }

        @Ignore
        public void test_HorseListSize_ShouldBe_1(){
            //hService.addHorseToList();
            //assertEquals("Checking if size of horse List is 1", 1, hService.getHorseList().size());
        }

        @Ignore
        public void test_HorseListSize_ShouldNotBe_0(){
            //hService.addHorseToList();
            //assertNotEquals("Checking if size of horse List is not 0", 0, hService.getHorseList().size());
        }

        @Ignore
        public void test_ClearList_ShouldClearArray(){
            //hService.addHorseToList();
            //assertFalse("Checking if size of array is not empty", hService.getHorseList().isEmpty());
            //hService.clearList();
            //assertTrue("Checking if array is empty", hService.getHorseList().isEmpty());
        }

        @Ignore
        public void test_ClearList_ShouldNotClearArray(){
            //assertTrue("Checking if size of array is empty", hService.getHorseList().isEmpty());
            //hService.clearList();
            //assertTrue("Checking if size of array is empty", hService.getHorseList().isEmpty());
        }

        @Test
        public void test_EndRun_IfBarn_ShouldSetDistanceTravelledTo_0(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Barn");
            hService.run(horse, 7);
            assertEquals(0, hService.endRun(horse).getDistanceTravelled().intValue());
        }

        @Test
        public void test_EndRun_IfFinish_DistanceShouldNotBe_0(){
            horse.setDistRemain(20);
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.setDest("Finish");
            hService.run(horse, 7);
            assertNotEquals(0, hService.endRun(horse).getDistanceTravelled().intValue());
        }

        @Test
        public void testRunToGoalIfFinish_DistanceTravelledShouldBe_20(){
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.runToGoal(horse, 20, "Finish");
            assertEquals(20, horse.getDistanceTravelled().intValue());
        }

        @Test
        public void test_RunToGoal_IfFinish_ShouldContainBreakDown(){
            horse = (hService.giveHorseName(horse, Optional.ofNullable(null), 0));
            hService.runToGoal(horse, 20, "Finish");
            assertFalse("Checking if size of Breakdown if not empty", horse.getDistBreakDown().isEmpty());
        }


        @Test
        public void test_WarCry_IfNull(){
            horse = (hService.giveHorseName(horse, Optional.ofNullable("Amigo"), 0));
            horse = hService.giveWarCry(horse, 3);
            assertEquals("\n Amigo has no warcry. \n", hService.cry(horse));
        }

        @Test
        public void test_WarCry_IfNotNull(){
            horse = (hService.giveHorseName(horse, Optional.ofNullable("Amigo"), 0));
            horse = hService.giveWarCry(horse, 0);
            assertEquals("\n Amigo shouts \"Let's get down to business!\" \n", hService.cry(horse));
        }

        @Test
        public void test_printBreakDown(){
            ArrayList<Integer> distBreakdown = new ArrayList<Integer>();
            distBreakdown.add(7);
            horse = mock(Horse.class);
            when(horse.getName()).thenReturn("John");
            when(horse.getDistBreakDown()).thenReturn(distBreakdown);
            when(horse.getDistanceTravelled().intValue()).thenReturn(7);

            assertEquals(" John travelled Distances: [7] = 7 ", hService.printBreakDown(horse));

        }




    }
