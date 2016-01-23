/**
 * Class to represent a currency
 */
public class Money implements Expression {

    protected int amount;
    protected String currency;

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    Money times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    Expression plus(Money toAdd) {
        return new Sum(this, toAdd);
    }

    String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @Override
    public Money reduce(String currency) {
        return this;
    }
}
