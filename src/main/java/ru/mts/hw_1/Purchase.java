package ru.mts.hw_1;

import java.util.Optional;

/* Класс Purchase(Покупка)
 * итоговая стоимость формируется из параметров: количество товаров, цена товара, скидка на товар */
public class Purchase {
    private final int numberOfGoods; // количество товаров
    private final double price; // цена товара
    private final double discount; // скидка на товар

    public Purchase(int numberOfGoods, double price, double discount) {
        this.numberOfGoods = numberOfGoods;
        this.price = price;
        this.discount = discount;
    }

    public static double countTotalSum(Purchase purchase) {
        Optional<Purchase> purchaseOptional = Optional.ofNullable(purchase);
        if (purchaseOptional.isPresent()) {
            Purchase purchaseWithOutNull = purchaseOptional.get();
            if (purchaseWithOutNull.numberOfGoods <= 0 || purchaseWithOutNull.price <= 0 ||
                    purchaseWithOutNull.discount < 0 || purchaseWithOutNull.discount > 100) {
                System.out.println("'numberOfGoods' and 'Price' values must be greater than zero, 'Discount' can take values from 0 to 100 inclusive");
                throw new IllegalArgumentException("'numberOfGoods' and 'Price' values must be greater than zero, 'Discount' can take values from 0 to 100 inclusive");
            }
            double sumWithoutDiscount = purchaseWithOutNull.numberOfGoods * purchaseWithOutNull.price;
            double sumWithDiscount = sumWithoutDiscount * (1 - purchaseWithOutNull.discount / 100);
            String resultWithoutDiscount = String.format("%.2f", sumWithoutDiscount);
            String resultWithDiscount = String.format("%.2f", sumWithDiscount);
            System.out.println("Purchase price without discount - " + resultWithoutDiscount + ", Discount purchase price - "
                    + resultWithDiscount);
            return Math.round(sumWithDiscount * 100.0) / 100.0;
        } else {
            System.out.println("There is no data to calculate the purchase amount");
            throw new IllegalArgumentException("There is no data to calculate the purchase amount");
        }
    }
}