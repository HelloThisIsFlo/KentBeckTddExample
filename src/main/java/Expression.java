/**
 * An Expression holds an expression of possibly different currency
 *
 * This allows to postpone the exchange rate conversion until necessary.
 */
public interface Expression {

    /**
     * Reduce this expression to the selected currency
     *
     * @param bank Bank to get the currency from
     * @param toCurrency Currency of the result Money
     * @return Money representing the result of the expression
     */
    Money reduce(Bank bank, String toCurrency);
}
