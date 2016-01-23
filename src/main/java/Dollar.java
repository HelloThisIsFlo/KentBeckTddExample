/**
 * Class to represent the Dollar currency
 */
public class Dollar extends Money{

    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    Money times(int multiplier) {
        return Money.dollar(amount * multiplier);
    }

}
