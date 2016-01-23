import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the Dollar and Franc classes
 */
public class CurrencyTest {

    @Test
    public void testMultiplication() throws Exception {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testFrancMultiplication() throws Exception {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void testEquality() throws Exception {
        // Test equality for Dollar
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));

        // Test equality for Franc
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));

        // Test equality Dollar & Francs
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testDifferentClassEquality() throws Exception {
        // Test equality for different classes
        String currency = "CHF";
        assertTrue(new Money(5, currency).equals(new Franc(5, currency)));

        currency = "USD";
        assertTrue(new Money(5, currency).equals(new Dollar(5, currency)));
    }

    @Test
    public void testCurrency() throws Exception {
        // For now represent currency with String
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }
}