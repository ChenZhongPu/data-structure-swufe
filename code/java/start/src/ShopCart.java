import java.util.HashMap;
import java.util.Map;

public class ShopCart {
    private final Map<String, Integer> carts;

    public ShopCart() {
        carts = new HashMap<>();
    }

    public int size() {
        return carts.size();
    }

    public void addBook(String name) {
        int amount = carts.getOrDefault(name, 0) + 1;
        carts.put(name, amount);
    }

    public void addBook(String name, int num) {
        assert num > 0;
        int amount = carts.getOrDefault(name, 0) + num;
        carts.put(name, amount);
    }

    public int getAmount(String name) {
        return carts.getOrDefault(name, 0);
    }

    public static void main(String[] args) {
        ShopCart shopCart = new ShopCart();
        shopCart.addBook("8899");

        assert shopCart.size() == 1;
        assert shopCart.getAmount("8899") == 1;
        assert shopCart.getAmount("7788") == 0;

        shopCart.addBook("7788", 2);
        assert shopCart.getAmount("7788") == 2;

        shopCart.addBook("7788");
        assert shopCart.getAmount("7788") == 3;

        assert shopCart.size() == 2;
    }
}
