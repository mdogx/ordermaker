import java.io.*;
import java.util.*;

public class Ordermaker {

    private static Menu todayMenu;
    private static List<Customer> todayCustomers;
    private static Customer currentCustomer;
    private static final Scanner in = new Scanner(System.in);
    private static String input;
        
    public static void main (String[] args) throws IOException {
        System.out.println("\n\nДобрый день!");
        todayMenu = new Menu();
        todayCustomers = new ArrayList<Customer>();
        todayMenu.loadFromFile("Menu.txt");
        todayMenu.print();
        makeOrder();
    }
    
    public static void makeOrder () {
        while (true) {
            System.out.println("\nВыберите номер нужного действия: \n\t(1) — Добавить заказ;\n\t(2) — Просмотреть заказы;\n\t(3) — Просмотреть меню на сегодня;\n\t(0) — Завершить работу.");
            input = in.next();
            
            if (!(input.matches("^[0-3]*$"))){
                System.out.println("Некорректный ввод.");
                continue;
            }
            
            int i = Integer.valueOf(input);
            if (i == 0) {
                System.out.println("Всего хорошего!\n");
                break;
            }    
            
            switch (i) {
                case 1: addOrder();
                        continue;
                case 2: printOrders();
                        continue;
                case 3: todayMenu.print();
                        continue;
                default: System.out.println("Неверный ввод..");
            }
        }
    }
    
    public static void addOrder () {
        System.out.println("\nВведите имя нового клиента");
        currentCustomer = new Customer();
        input = in.nextLine();
        input = in.nextLine();
        currentCustomer.setName(input);
        
        System.out.println("\nВведите номер выбранного блюда, или (0) для отмены заказа.");
        
        while (true) { // cycle input order data
            input = in.next();
            
            if (!(input.matches("^[0-9]*$"))){
                System.out.println("Неверный ввод. Введите номер желаемого блюда.");
                continue;
            }
            
            int i = Integer.valueOf(input);
            if (i == 0) {
                break;
            }
            
            if (i > todayMenu.getDishesAmount()) {
                System.out.println("Блюда с таким номером не существует.");
                continue;
            }
            
            currentCustomer.addToOrder(todayMenu.getDish(i-1));
            System.out.println("Блюдо добавлено в заказ.");
            System.out.println("\nВведите номер следующего блюда, или (0) для завершения.");
        }
        
        todayCustomers.add(currentCustomer);
        System.out.println("Заказ завершен.");
    }
    
    public static void printOrders () {
        if (todayCustomers.size() == 0) { // check for customers existence
            System.out.println("Заказов на сегодня пока нет");
        } else {
            System.out.println("\nСегодня были приняты следующие заказы:\n"); // print recipients
            for (int m = 0; m < todayCustomers.size(); m++) {
                System.out.println((m+1)+": "+todayCustomers.get(m).name+" сделал заказ на "+todayCustomers.get(m).getOrderTotalCost()+" руб.:");
                for (int n = 0; n < todayCustomers.get(m).getOrdersAmmount(); n++) {
                    System.out.println("\t— " + todayCustomers.get(m).getDishFromOrder(n).name);
                }
            }
            int todayTotalCost = 0;
            System.out.println("\nСегодня нужно приготовить следующие блюда:"); // print cooking order
            for (int m = 0; m < todayCustomers.size(); m++) {
                todayTotalCost += todayCustomers.get(m).getOrderTotalCost();
                for (int n = 0; n < todayCustomers.get(m).getOrdersAmmount(); n++) {
                    System.out.println("\t— " + todayCustomers.get(m).getDishFromOrder(n).name+" - "+todayCustomers.get(m).getDishFromOrder(n).weight+" грамм, "+todayCustomers.get(m).getDishFromOrder(n).cost+" рублей.");
                }
            }
            System.out.println("Всего заказов на "+todayTotalCost+" руб.");
        }
    }
}
