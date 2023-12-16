package ru.mts.hw_1;

public class Main {
    public static void main(String[] args) {
        Purchase.countTotalSum(new Purchase(5, 10, 0.75));
        Purchase.countTotalSum(new Purchase(5, 10, 42.575));
        Purchase.countTotalSum(new Purchase(5, 10, 59.1));

        Purchase.countTotalSum(new Purchase(0, 10, 59.1));
        Purchase.countTotalSum(new Purchase(5, 0, 59.1));
        Purchase.countTotalSum(new Purchase(5, 10, 0));
        Purchase.countTotalSum(null);
    }
}