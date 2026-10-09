package edu.course.lab03;

public class PercentDiscount implements DiscountPolicy {

    private final double percent;

    public PercentDiscount(double percent){
        if (percent < 0 || percent > 100){
            throw new IllegalArgumentException();
        }
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        return price * (1 - percent / 100);
    }
}
