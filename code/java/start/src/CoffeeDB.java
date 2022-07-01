import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Coffee {
    private String name;
    private double price;

    public Coffee(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class CoffeeDB {
    private List<Coffee> coffees;

    private void initData() {
        coffees = Arrays.asList(
                new Coffee("Mocha", 10),
                new Coffee("Latte", 12),
                new Coffee("Mocha with Milk", 11),
                new Coffee("Espresso", 15),
                new Coffee("Espresso with Ice", 15),
                new Coffee("Americano", 8),
                new Coffee("Americano with Ice", 8));
    }

    public CoffeeDB() {
        initData();
    }

    public void addCoffee(String name, double price) {
        coffees.add(new Coffee(name, price));
    }

    public List<Coffee> findByName(String name) {
        List<Coffee> results = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (coffee.getName().contains(name)) {
                results.add(coffee);
            }
        }
        return results;
    }

    // A concise version: works only for JDK 16 and abover
//    public List<Coffee> findByName2(String name) {
//        return coffees.stream().
//                filter(coffee -> coffee.getName().contains(name))
//                .collect(Collectors.toList());
//    }

    public static void main(String[] args) {
        CoffeeDB db = new CoffeeDB();
        List<Coffee> results = db.findByName("Ice");
        results.forEach(System.out::println);
    }
}
