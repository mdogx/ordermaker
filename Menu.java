import java.io.*;
import java.util.*;

public class Menu {
    
    private List<Dish> todayDishes = new ArrayList<Dish>();

    public void loadFromFile(String sourceFile) throws IOException {

        // connect to source file
        BufferedReader br = new BufferedReader(new FileReader(sourceFile));
        String currentString;
        
        // create list of dishes
        while ((currentString = br.readLine()) != null) {
            Dish currentDish = new Dish();
            currentDish.createFromString(currentString);
            todayDishes.add(currentDish);
        }
    }
    
    public void print () {
        
        // print greeting and list of dishes    
        System.out.println("\nСегодня мы можем приготовить следующие блюда: ");
        
        if (todayDishes.size() == 0) {
            System.out.println("Сегодня мы не готовим.");
        }
        
        for (int i = 0; i < todayDishes.size(); i++) {
            System.out.print("\t"+(i+1)+": "+todayDishes.get(i).name+" — "+todayDishes.get(i).cost+" рублей");
            
            // add char to end of each line
            if (i < ((todayDishes.size()-1))) { 
                System.out.println(";");
            } else {
                System.out.println(".\n");
            }
        }
    }
    
    public List<Dish> getDishesList() {
        return todayDishes;
    }
    
    public Dish getDish(int i) {
        return todayDishes.get(i);
    }
    
    public int getDishesAmount() {
        return todayDishes.size();
    }
}
