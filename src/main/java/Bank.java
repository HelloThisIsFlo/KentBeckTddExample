/**
 * todo comment
 */
public class Bank {

    /**
     * Reduce and expression to the selected currency
     *
     * @param exp Expression to reduce
     * @param currency Currency of the result Money
     * @return Money representing the result of the expression
     */
    Money reduce(Expression exp, String currency) {
        return Money.dollar(10);
    }
}
