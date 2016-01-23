import java.util.HashMap;
import java.util.Map;

/**
 * todo comment
 */
public class Bank {

    private class PairCurrency {
        private final String fromCurrency;
        private final String toCurrency;

        public PairCurrency(String fromCurrency, String toCurrency) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
        }

        @Override
        public boolean equals(Object o) {
            PairCurrency pair = (PairCurrency) o;
            return fromCurrency.equals(pair.fromCurrency) && toCurrency.equals(pair.toCurrency);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    private Map<PairCurrency, Integer> rates = new HashMap<>();

    /**
     * Reduce an expression to the selected currency
     *
     * @param expression Expression to reduce
     * @param currency Currency of the result Money
     * @return Money representing the result of the expression
     */
    Money reduce(Expression expression, String currency) {
        return expression.reduce(this, currency);
    }

    /**
     * Add a conversion rate.
     *
     * To obtain the destination currency : the origin currency is divided by the rate
     *
     * @param fromCurrency origin currency
     * @param toCurrency destination currency
     * @param rate conversion rate
     */
    void addRate(String fromCurrency, String toCurrency, int rate){
        rates.put(new PairCurrency(fromCurrency, toCurrency), rate);
    }

    int rate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1;
        } else {
            return rates.get(new PairCurrency(fromCurrency, toCurrency));
        }
    }
}
