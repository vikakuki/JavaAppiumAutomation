import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainClassTest
{
    MainClass mainClass;

    @Before
    public void testBefore()
    {
        System.out.println("Test Start");
        mainClass = new MainClass();
    }

    @After
    public void testAfter()
    {
        System.out.println("Test End");
    }

    @Test
    public void testGetLocalNumber()
    {
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        assertEquals("Expected local number to be 14, but got: " + actual, expected, actual);
    }

    @Test
    public void testGetClassNumber() {
        int actual = mainClass.getClassNumber();
        assertTrue("Class number returned by getClassNumber is not greater than 45, but got: " + actual, actual > 45);
    }
}