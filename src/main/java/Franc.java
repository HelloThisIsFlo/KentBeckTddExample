/**
 * Class to represent the Franc currency
 */
public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    @Override
    Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
