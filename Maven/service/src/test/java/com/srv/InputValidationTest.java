package com.srv;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputValidationTest
    {


        @Test
        public void test_EmptyStringInput_ShouldReturnNull(){
            assertEquals("Checking if function returns null", null, InputValidation.stringInput(""));
        }

        @Test
        public void test_StringInput_ShouldNotReturnNull(){
            assertNotEquals("Checking if does not return null", null, InputValidation.stringInput("Hey"));
        }

        @Test
        public void getsIntegerWhenWithinBoundsOfOneToTen() throws Exception {
            InputValidation.setRange(1,10);
            IntegerAsker asker = mock(IntegerAsker.class);
            when(asker.ask(anyString())).thenReturn(3);

            assertEquals(InputValidation.getBoundIntegerFromUser(asker).intValue(), 3);
        }

        @Test
        public void asksForNewIntegerWhenOutsideBoundsOfOneToTen() throws Exception {
            InputValidation.setRange(1,10);
            IntegerAsker asker = mock(IntegerAsker.class);
            when(asker.ask("Give a number between 1 and 10")).thenReturn(99);
            when(asker.ask("Wrong number, try again.")).thenReturn(3);
            InputValidation.getBoundIntegerFromUser(asker);
            verify(asker).ask("Wrong number, try again.");
        }

        @Test
        public void test_RealStaticClass(){
            //InputValidation.Inner.mockStringInput();
            assertEquals("Checking if function returns null", null, InputValidation.Inner.mockStringInput(""));
            assertNotEquals("Checking if does not return null", null, InputValidation.Inner.mockStringInput("Hey"));

        }




    }
