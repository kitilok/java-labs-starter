package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountPolicyTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0));
    }

    @Test
    void noDiscountRejectsNegativePrice() {
        DiscountPolicy policy = new NoDiscount();

        assertThrows(IllegalArgumentException.class, () -> policy.apply(-10.0));
    }

    @Test
    void percentDiscountCalculatesDiscount() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0));
    }

    @Test
    void percentDiscountRejectsInvalidPercent() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
    }

    @Test
    void calculatorCanUseDifferentPolicies() {
        PriceCalculator noDiscount =
                new PriceCalculator(new NoDiscount());

        PriceCalculator percentDiscount =
                new PriceCalculator(new PercentDiscount(20));

        assertEquals(100.0, noDiscount.calculate(100.0));
        assertEquals(80.0, percentDiscount.calculate(100.0));
    }
}