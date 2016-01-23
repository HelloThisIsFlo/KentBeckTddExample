/**
 * Expression that represent a Sum
 */
public class Sum implements Expression {

    Expression first;
    Expression second;

    public Sum(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {

        // Reduce both Money first
        Money reducedFirst = first.reduce(bank, toCurrency);
        Money reducedSecond = second.reduce(bank, toCurrency);

        int amount = reducedFirst.amount + reducedSecond.amount;
        return new Money(amount, toCurrency);
    }

    @Override
    public Expression plus(Expression toAdd) {
        return new Sum(this, toAdd);
    }

    @Override
    public Expression times(int multiplier) {
        Expression multipliedFirst = first.times(multiplier);
        Expression multipliedSecond = second.times(multiplier);
        return new Sum(multipliedFirst, multipliedSecond);
    }
}
