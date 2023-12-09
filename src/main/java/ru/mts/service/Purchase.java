package ru.mts.service;

/* Класс Purchase(Покупка)
 * итоговая стоимость формируется из параметров: количество товаров, сумма товара, скидка на товар */
public class Purchase {
    private int numberOfGoods; // количество товаров
    private double price; // цена товара
    private double discount; // скидка на товар

    public Purchase(int numberOfGoods, double price, double discount) {
        this.numberOfGoods = numberOfGoods;
        this.price = price;
        this.discount = discount;
    }

    public static void countTotalSum(Purchase purchase) {
        if (purchase.numberOfGoods <= 0 || purchase.price <= 0 || purchase.discount < 0 || purchase.discount > 100) {
            System.out.println("Значения 'numberOfGoods' и 'Price' должны быть больше нуля, 'Discount' может принимать значения от 0 до 100");
            return;
        }
        double sumWithoutDiscount = purchase.numberOfGoods * purchase.price;
        double sumWithDiscount = purchase.numberOfGoods * purchase.price * (1 - purchase.discount / 100);
        String resultWithoutDiscount = String.format("%.2f", sumWithoutDiscount);
        String resultWithDiscount = String.format("%.2f", sumWithDiscount);

        if (purchase.discount == 0) {
            System.out.println("Стоимость покупки без скидки - " + resultWithoutDiscount + ", стоимость покупки со скидкой - "
                    + resultWithoutDiscount);
        } else {
            System.out.println("Стоимость покупки без скидки - " + resultWithoutDiscount + ", стоимость покупки со скидкой - "
                    + resultWithDiscount);
        }
    }
}