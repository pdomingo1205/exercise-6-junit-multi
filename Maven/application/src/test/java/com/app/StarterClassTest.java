package com.srv;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.srv.InputValidation.*;
import com.horse.Horse;
import com.app.StarterClass;
import com.app.StarterClassImplementation;
import org.junit.runners.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import static org.mockito.Matchers.*;
import org.apache.commons.lang3.RandomUtils;





@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomUtils.class)

public class StarterClassTest
    {


        @Test
        public void test_StarterClass(){
            Horse horse2 = new Horse();
            PowerMockito.mockStatic(RandomUtils.class);
            when(RandomUtils.nextBoolean()).thenReturn(true);
            when(RandomUtils.nextInt()).thenReturn(1);

            StarterClassImplementation starter = new StarterClassImplementation();

            horse2 = starter.horseCreator(horse2, 1,  Optional.ofNullable("John"));

            assertTrue(horse2.isHealthy());
            assertEquals("John", horse2.getName());
            assertFalse(horse2.getWarCry().isEmpty());

        }

        @Test
        public void test_horseCreator(){
            Horse horse2 = new Horse();
            PowerMockito.mockStatic(RandomUtils.class);
            when(RandomUtils.nextBoolean()).thenReturn(false);
            when(RandomUtils.nextInt()).thenReturn(3);

            StarterClassImplementation starter = new StarterClassImplementation();

            horse2 = starter.horseCreator(horse2, 1,  Optional.ofNullable("Sonn"));

            assertFalse(horse2.isHealthy());
            assertNotEquals("John", horse2.getName());
        }



        }
