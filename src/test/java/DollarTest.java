import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the Dollar class
 */
public class DollarTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMultiplication() throws Exception {
        Dollar five = new Dollar(5);

        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));

    }

    @Test
    public void testEquality() throws Exception {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }
}