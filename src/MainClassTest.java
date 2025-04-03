import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest
{
    @Before
    public void testBefore()
    {
        System.out.println("Test Start");
    }

    @After
    public void testAfter()
    {
        System.out.println("Test End");
    }

    @Test
    public void testGetLocalNumber()
    {
        MainClass main = new MainClass();
        assertEquals("Local number is not 14", 14, main.getLocalNumber());
        System.out.println("Test passed successfully");
    }
}