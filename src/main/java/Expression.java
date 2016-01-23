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

    /**
     * Add an Expression to the current Expression
     * @param toAdd Expression to add
     * @return Result of the sum
     */
    Expression plus(Expression toAdd);

    /**
     * Multiply this expression by a multiplier
     * @param multiplier Multiplication factor
     * @return Resulting Expression
     */
    Expression times(int multiplier);
}
