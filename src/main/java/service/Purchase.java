package service;

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
        if (purchase.discount == 0) {
            System.out.printf("Стоимость покупки без скидки - %.2f, стоимость покупки со скидкой - %.2f \n",
                    purchase.numberOfGoods * purchase.price, purchase.numberOfGoods * purchase.price);
        } else {
            System.out.printf("Стоимость покупки без скидки - %.2f, стоимость покупки со скидкой - %.2f \n",
                    purchase.numberOfGoods * purchase.price, purchase.numberOfGoods * purchase.price * (1 - purchase.discount / 100));
        }
    }
}