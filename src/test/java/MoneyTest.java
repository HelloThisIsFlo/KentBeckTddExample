import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for the Money classes
 */
public class MoneyTest {
    
    private static final Expression FIVE_BUCKS = Money.dollar(5);
    private static final Expression TEN_FRANCS= Money.franc(10);
    private Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = new Bank();

        // Set up the bank
        bank.addRate("CHF", "USD", 2);
        bank.addRate("CHF", null, 2);
    }

    @Test
    public void testMultiplication() throws Exception {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() throws Exception {
        // Test equality for Dollar
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));

        // Test equality Dollar & Francs
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testEqualityNull() throws Exception {
        assertFalse(Money.dollar(4).equals(null));
    }

    @Test
    public void testEqualityObject() throws Exception {
        assertFalse(Money.dollar(43).equals(new ArrayList<String>()));

    }

    @Test
    public void testCurrency() throws Exception {
        // For now represent currency with String
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    public void testSimpleAddition() throws Exception {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);

        // Apply the exchange rates and reduce to a USD Money
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() throws Exception {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.first);
        assertEquals(five, sum.second);
    }

    @Test
    public void testReduceSum() throws Exception {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));

        // Reduce the sum
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    public void testReduceMoney() throws Exception {
        Money reduced = bank.reduce(Money.dollar(3), "USD");
        assertEquals(Money.dollar(3), reduced);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() throws Exception {
        // Reduce 2CHF to $1
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(result, Money.dollar(1));
    }

    @Test
    public void testIdentityRate() throws Exception {
        // This was a surprise when refactoring, so create a new test before implementing the functionality
        assertEquals(1, bank.rate("USD", "USD"));
    }

    @Test
    public void testMixedAddition() throws Exception {
        // Reduce sum of 2 Money with different rates
        Money result = bank.reduce(FIVE_BUCKS.plus(TEN_FRANCS), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    public void testSumPlusMoney() throws Exception {
        // Make sum of sum + FIVE_BUCKS : ($5 + 10CHF) + $5 = $10 + $5 = $15
        Expression sum = new Sum(FIVE_BUCKS, TEN_FRANCS).plus(FIVE_BUCKS);
        Money result = sum.reduce(bank, "USD");
        assertEquals(result, Money.dollar(15));
    }

    @Test
    public void testSumTimes() throws Exception {
        Sum sum = new Sum(FIVE_BUCKS, TEN_FRANCS); //$10
        Money result = bank.reduce(sum.times(3), "USD"); //$30
        assertEquals(result, Money.dollar(30));
    }

}