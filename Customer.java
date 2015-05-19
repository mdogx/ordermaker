import java.io.*;
import java.util.*;

public class Customer {
    
    public List<Dish> orders = new ArrayList<>();
    public String name;

    public void setName(String customerName) {
        this.name = customerName;
    }
    
    public void addToOrder(Dish dish) {
        orders.add(dish);
    }
    
    public int getOrdersAmmount() {
        return orders.size();
    }
    
    public  Dish getDishFromOrder(int i) {
        return orders.get(i);
    }
    
    public int getOrderTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < this.getOrdersAmmount(); i++) {
            totalCost += this.getDishFromOrder(i).cost;
        }
        return totalCost;
    }
}
