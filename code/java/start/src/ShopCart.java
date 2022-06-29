import java.util.HashMap;
import java.util.Map;

public class ShopCart {
    public static void main(String[] args) {
        Map<String, Integer> cart = new HashMap<>();
        cart.put("7801", 3);
        cart.put("9922", 1);

        int amount = cart.get("7801");
        System.out.println(amount);

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
