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

    public static void countTotalSum(Purchase purchase) {
        Optional<Purchase> purchaseOptional = Optional.ofNullable(purchase);
        if (purchaseOptional.isPresent()) {
            Purchase purchaseWithOutNull = purchaseOptional.get();
            if (purchaseWithOutNull.numberOfGoods <= 0 || purchaseWithOutNull.price <= 0 ||
                    purchaseWithOutNull.discount < 0 || purchaseWithOutNull.discount > 100) {
                System.out.println("Значения 'numberOfGoods' и 'Price' должны быть больше нуля, 'Discount' может принимать значения от 0 до 100 включительно");
                return;
            }
            double sumWithoutDiscount = purchaseWithOutNull.numberOfGoods * purchaseWithOutNull.price;
            double sumWithDiscount = sumWithoutDiscount * (1 - purchaseWithOutNull.discount / 100);
            String resultWithoutDiscount = String.format("%.2f", sumWithoutDiscount);
            String resultWithDiscount = String.format("%.2f", sumWithDiscount);
            System.out.println("Стоимость покупки без скидки - " + resultWithoutDiscount + ", стоимость покупки со скидкой - "
                    + resultWithDiscount);
        } else {
            System.out.println("Нет данных для вычисления суммы покупки");
        }
    }
}