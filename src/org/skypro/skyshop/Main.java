public class Main {
    public static void main(String[] args) {
        // Создание тестовых продуктов
        Product product1 = new Product("Яблоко", 50);
        Product product2 = new Product("Банан", 30);
        Product product3 = new Product("Апельсин", 70);
        Product product4 = new Product("Груша", 40);
        Product product5 = new Product("Манго", 100);
        Product product6 = new Product("Лимон", 20);

        ProductBasket basket = new ProductBasket();

        // Тестирование добавления товаров
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5); // 5-й товар
        basket.addProduct(product6); // Переполнение корзины

        // Демонстрация работы методов
        System.out.println("=== Тест 1: Печать заполненной корзины ===");
        basket.printContents();

        System.out.println("\n=== Тест 2: Поиск товаров ===");
        System.out.println("Найден 'Банан': " + basket.containsProduct("Банан"));
        System.out.println("Найден 'Виноград': " + basket.containsProduct("Виноград"));
        System.out.println("Общая стоимость: " + basket.getTotalCost() + " руб.");

        System.out.println("\n=== Тест 3: Очистка корзины ===");
        basket.clearBasket();
        basket.printContents();
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());
        System.out.println("Поиск в пустой корзине: " + basket.containsProduct("Яблоко"));
    }
}

class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class ProductBasket {
    private final Product[] storage = new Product[5];
    private int count = 0;

    public void addProduct(Product product) {
        if (count < storage.length) {
            storage[count++] = product;
        } else {
            System.out.println("[Ошибка] Корзина переполнена!");
        }
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += storage[i].getPrice();
        }
        return total;
    }

    public void printContents() {
        if (count == 0) {
            System.out.println("Корзина пуста");
            return;
        }

        System.out.println("Содержимое корзины:");
        for (int i = 0; i < count; i++) {
            Product p = storage[i];
            System.out.printf("- %s: %d руб.\n", p.getName(), p.getPrice());
        }
        System.out.println("Всего: " + getTotalCost() + " руб.");
    }

    public boolean containsProduct(String name) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        count = 0;
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }
}