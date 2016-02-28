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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PairCurrency that = (PairCurrency) o;

            if (fromCurrency != null ? !fromCurrency.equals(that.fromCurrency) : that.fromCurrency != null)
                return false;
            return toCurrency != null ? toCurrency.equals(that.toCurrency) : that.toCurrency == null;

        }

        @Override
        public int hashCode() {
            int result = fromCurrency != null ? fromCurrency.hashCode() : 0;
            result = 31 * result + (toCurrency != null ? toCurrency.hashCode() : 0);
            return result;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return rates != null ? rates.equals(bank.rates) : bank.rates == null;

    }

    @Override
    public int hashCode() {
        return 0;
    }
}
