package com.srv;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.srv.InputValidation.*;
import org.junit.runners.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import static org.mockito.Matchers.*;





@RunWith(PowerMockRunner.class)
@PrepareForTest(InputValidation.class)

public class StaticClassTest
    {

        @Test
        public void test_StaticClass(){
            PowerMockito.mockStatic(InputValidation.Inner.class);
            when(InputValidation.Inner.mockStringInput("")).thenReturn(null);
            when(InputValidation.Inner.mockStringInput("Hey")).thenReturn("Hello!");

            //InputValidation.Inner.mockStringInput();
            assertEquals("Checking if function returns null", null, InputValidation.Inner.mockStringInput(""));
            assertNotEquals("Checking if does not return null", null, InputValidation.Inner.mockStringInput("Hey"));

        }


        @Test
        public void test_RealStaticClass(){
            //InputValidation.Inner.mockStringInput();
            assertEquals("Checking if function returns null", null, InputValidation.Inner.mockStringInput(""));
            assertNotEquals("Checking if does not return null", null, InputValidation.Inner.mockStringInput("Hey"));

        }

        }
