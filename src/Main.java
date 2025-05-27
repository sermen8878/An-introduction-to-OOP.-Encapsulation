import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();
    public abstract boolean isSpecial();

    @Override
    public abstract String toString();
}

class SimpleProduct extends Product {
    private final double price;

    public SimpleProduct(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %.2f", getName(), getPrice());
    }
}

class DiscountedProduct extends Product {
    private final double basePrice;
    private final int discount;

    public DiscountedProduct(String name, double basePrice, int discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return basePrice * (100 - discount) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %.2f (%d%% скидка)", getName(), getPrice(), discount);
    }
}

class FixPriceProduct extends Product {
    private static final double FIXED_PRICE = 99.99;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: Фиксированная цена %.2f", getName(), FIXED_PRICE);
    }
}

class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void printContents() {
        double total = 0;
        int specialCount = 0;

        for (Product product : products) {
            System.out.println(product);
            total += product.getPrice();
            if (product.isSpecial()) {
                specialCount++;
            }
        }

        System.out.printf(Locale.US, "Итого: %.2f%n", total);
        System.out.println("Специальных товаров: " + specialCount);
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new SimpleProduct("Хлеб", 30.0));
        cart.addProduct(new DiscountedProduct("Молоко", 50.0, 20));
        cart.addProduct(new FixPriceProduct("Книга"));
        cart.addProduct(new DiscountedProduct("Смартфон", 1000.0, 15));
        cart.addProduct(new FixPriceProduct("Блокнот"));

        cart.printContents();
    }
}