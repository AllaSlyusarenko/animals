package service;

/* Класс Purchase(Покупка)
 * итоговая стоимость формируется из параметров: количество товаров, сумма товара, скидка на товар */
public class Purchase {
    private int NumberOfGoods; // количество товаров
    private double Price; // цена товара
    private double Discount; // скидка на товар

    public Purchase(int numberOfGoods, double price, double discount) {
        NumberOfGoods = numberOfGoods;
        Price = price;
        Discount = discount;
    }

    public static void countTotalSum(Purchase purchase) {
        if (purchase.NumberOfGoods <= 0 || purchase.Price <= 0 || purchase.Discount < 0) {
            System.out.println("Значения 'NumberOfGoods' и 'Price' должны быть больше нуля, 'Discount' может быть равным нулю");
            return;
        }
        if (purchase.Discount == 0) {
            System.out.printf("Стоимость покупки без скидки - %.2f, стоимость покупки со скидкой - %.2f \n",
                    purchase.NumberOfGoods * purchase.Price, purchase.NumberOfGoods * purchase.Price);
        } else {
            System.out.printf("Стоимость покупки без скидки - %.2f, стоимость покупки со скидкой - %.2f \n",
                    purchase.NumberOfGoods * purchase.Price, purchase.NumberOfGoods * purchase.Price * (1 - purchase.Discount / 100));
        }
    }
}