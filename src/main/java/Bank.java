/**
 * todo comment
 */
public class Bank {

    /**
     * Reduce an expression to the selected currency
     *
     * @param expression Expression to reduce
     * @param currency Currency of the result Money
     * @return Money representing the result of the expression
     */
    Money reduce(Expression expression, String currency) {
        return expression.reduce(currency);
    }
}
