import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ORDER_FILE = "src/orders.txt";
    public static final String MENU_FILE = "src/menu.txt";

    public static Scanner scanner = new Scanner(System.in);

    public static List<String[]> customerOrders = new ArrayList<>();
    public static List<String[]> menuItems = new ArrayList<>();
    


    public static void main(String[] args) {
        loadCustomerOrders();
        loadMenuItems();
        login();
    }
    public static void login() {
        String correctUsername = "admin";
        String correctPassword = "admin123";
        int attempts = 3;
        System.out.println ("");

        System.out.println("================ Welcome to CAFE MOCHA System =======================");
        System.out.println("                 ========     ==    ========   ");
        System.out.println("                  ======     ====    ======                          ");
        System.out.println("                   ====     ======    ====                            ");
        System.out.println("                    ==     ========    ==                   ");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println();
System.out.println("   ===========================");    
System.out.println("  /                           \\              ( (                                 ");
System.out.println(" /    ~~  CAFE  MOCHA ~~       \\              ) )                                ");
System.out.println("|                               |           --------                              ");
System.out.println(" \\_____________________________/           |        |]                       ");
System.out.println("  |___________________________|             \\      /                                ");
System.out.println("  |                           |              `===='                                   ");
System.out.println("  |___________________________|                                                      ");

 System.out.println("=====================================================================");
        while (attempts > 0) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Login Successful! Welcome, " + username + ".");
                System.out.println("......................................................................");
                displayControlPanel();
                return;
            } else if (!username.equals(correctUsername) && !password.equals(correctPassword)) {
                System.out.println("Invalid username and password. Please try again.");
            } else if (!username.equals(correctUsername)) {
                System.out.println("Invalid username. Please try again.");
            } else {
                System.out.println("Invalid password. Please try again.");
            }

                System.out.println(".......................................................................");
            attempts--;
            System.out.println("Attempts remaining: " + attempts);
        }

        System.out.println("Too many failed attempts. Exiting the system.");
        System.exit(0);
    }

    public static void displayControlPanel() {
        while (true) {
            System.out.println("\n===================== Cafe Mocha Control Panel=======================");
            System.out.println("                   1. Add New Customer Order");
            System.out.println("                   2. Display Customer Orders");
            System.out.println("                   3. Delete Customer Order");
            System.out.println("                   4. Update Customer Order");
            System.out.println("                   5. Calculate and Print Bill");
            System.out.println("                   6. Add New Menu Item");
            System.out.println("                   7. Display Menu Items");
            System.out.println("                   8. Update Menu Item");
            System.out.println("                   9. Delete Menu Item");
            System.out.println("                   10. Help");
            System.out.println("                   11. Exit");
            System.out.print("Choose an option: ");
            int choice = getValidIntegerInput();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addNewCustomerOrder();
                case 2 -> displayCustomerOrder();
                case 3 -> deleteCustomerOrder();
                case 4 -> updateCustomerOrder();
                case 5 -> calculateAndPrintBill();
                case 6 -> addNewMenuItem();
                case 7 -> displayMenuItems();
                case 8 -> updateMenuItem();
                case 9 -> deleteMenuItem();
                case 10 -> displayHelp();
                case 11 -> {
                    exitSystem();
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }
    //add new customer order
    public static void addNewCustomerOrder() {
        System.out.println("===== Add New Customer Order =====");

        System.out.print("Enter Order Number: ");
        String orderNumber = scanner.nextLine().trim();

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Item Numbers (comma-separated): ");
        String itemNumbers = scanner.nextLine().trim();

        if (validateCustomerOrder(orderNumber, customerName, address, phoneNumber, email, itemNumbers)) {
            String[] order = {orderNumber, customerName, address, phoneNumber, email, itemNumbers};
            customerOrders.add(order);
            saveCustomerOrders();
            System.out.println("Customer order added successfully.");
            System.out.println("------------------------------------------------------------------");
        }
    }
    public static void displayCustomerOrder() {
    System.out.println("=========== Display Customer Orders ========");
    if (customerOrders.isEmpty()) {
        System.out.println("No orders found.");
    } else {
        for (String[] order : customerOrders) {
           
            if (order.length >= 6) {
                System.out.println("Order Number: " + order[0]);
                System.out.println("Customer Name: " + order[1]);
                System.out.println("Address: " + order[2]);
                System.out.println("Phone Number: " + order[3]);
                System.out.println("Email Address: " + order[4]);
                System.out.println("Item Numbers: " + order[5]);
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println(" ");
            }
        }
    }
    }

    //delete customer orders
    public static void deleteCustomerOrder() {
        System.out.println("===== Delete Customer Order =====");
        System.out.print("Enter Order Number to Delete: ");
        String orderNumber = scanner.nextLine().trim();

        boolean orderFound = false;

        for (int i = 0; i < customerOrders.size(); i++) {
            if (customerOrders.get(i)[0].equals(orderNumber)) {
                customerOrders.remove(i);
                saveCustomerOrders();
                System.out.println("Customer order deleted successfully.");
                System.out.println("------------------------------------------------------------------");
                orderFound = true;
                break;
            }
        }

        if (!orderFound) {
            System.out.println("Order not found.");
            System.out.println("------------------------------------------------------------------");
        }
    }
     //update customer orders
    public static void updateCustomerOrder() {
        System.out.println("===== Update Customer Order =====");
        System.out.print("Enter Order Number to Update: ");
        String orderNumber = scanner.nextLine().trim();

        for (String[] order : customerOrders) {
            if (order[0].equals(orderNumber)) {
                System.out.print("Enter New Customer Name: ");
                order[1] = scanner.nextLine().trim();
                System.out.print("Enter New Address: ");
                order[2] = scanner.nextLine().trim();
                System.out.print("Enter New Phone Number: ");
                order[3] = scanner.nextLine().trim();
                System.out.print("Enter New Email Address: ");
                order[4] = scanner.nextLine().trim();
                System.out.print("Enter New Item Numbers (comma-separated): ");
                order[5] = scanner.nextLine().trim();
                saveCustomerOrders();
                System.out.println("Customer order updated successfully.");
                System.out.println("------------------------------------------------------------------");
                return;
            }
        }
        System.out.println("Order not found.");
        System.out.println("------------------------------------------------------------------");
    }
    //calculate bill and print bill
    
    public static void calculateAndPrintBill() {
    System.out.println("===== Calculate and Print Bill =====");
    System.out.print("Enter Order Number to Calculate Bill: ");
    String orderNumber = scanner.nextLine().trim();
    double total = 0.0;
    double discount = 0.0;
    double taxRate = 0.0;

  
    double discountRate = 0.10; // 10% discount
    taxRate = 0.08; // 8% tax

    for (String[] order : customerOrders) {
        if (order[0].equals(orderNumber)) {
            String[] items = order[5].split(",");
            for (String item : items) {
                for (String[] menuItem : menuItems) {
                    if (menuItem[0].equals(item.trim())) {
                        total += Double.parseDouble(menuItem[2]);
                    }
                }
            }
            
            discount = total * discountRate;
            
            double subtotalAfterDiscount = total - discount;
            
            double tax = subtotalAfterDiscount * taxRate;
            
            double finalTotal = subtotalAfterDiscount + tax;

            // Print the bill
            
            System.out.println("--------------------------------------------------------");
            System.out.println("|                 CAFE MORCHA BILL                      |");      
            System.out.println("--------------------------------------------------------");
            System.out.println("     Subtotal: RS:" + total);
            System.out.println("     Discount: -RS:" + discount);
            System.out.println("     Subtotal after Discount: RS:" + subtotalAfterDiscount);
            System.out.println("     Tax: RS:" + tax);
            System.out.println("     Total Bill Amount: RS:" + finalTotal);
            System.out.println("---------------------------------------------------------");
            saveBillToFile(orderNumber, total, discount, tax, finalTotal);
            return;
        }
    }
    System.out.println("Order not found.");
   System.out.println("------------------------------------------------------------------");
}
 //save bill
public static void saveBillToFile(String orderNumber, double total, double discount, double tax, double finalTotal) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/bills.txt", true))) {
        writer.write("Order Number: " + orderNumber);
        writer.newLine();
        writer.write("Subtotal: RS" + total);
        writer.newLine();
        writer.write("Discount: -RS" + discount);
        writer.newLine();
        writer.write("Subtotal after Discount: RS" + (total - discount));
        writer.newLine();
        writer.write("Tax: RS:" + tax);
        writer.newLine();
        writer.write("Total Bill Amount: RS:" + finalTotal);
        writer.newLine();
        writer.write("-----------------------------------");
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Error saving bill: " + e.getMessage());
    }
}

     //add new menu item
    public static void addNewMenuItem() {
    System.out.println("===== Add New Menu Item =====");
    System.out.print("Enter Item Number: ");
    String itemNumber = scanner.nextLine().trim();
    System.out.print("Enter Item Name: ");
    String itemName = scanner.nextLine().trim();
    System.out.print("Enter Item Price: ");
    String price = scanner.nextLine().trim();
    System.out.print("Enter Item Description: ");
    String description = scanner.nextLine().trim();
    System.out.print("Enter Item Category: ");
    String category = scanner.nextLine().trim(); 

    // Validation 
    if (validateMenuItem(itemNumber, itemName, price, description, category)) {
        String[] menuItem = {itemNumber, itemName, price, description, category}; 
        menuItems.add(menuItem);
        saveMenuItems();
        System.out.println("Menu item added successfully.");
        System.out.println("------------------------------------------------------------------");
    } else {
        System.out.println("Invalid input. Please ensure all fields are filled correctly.");
    }
}
    
    public static void displayMenuItems() {
    System.out.println("================== Display Menu Items ==============");
    if (menuItems.isEmpty()) {
        System.out.println("No menu items found.");
    } else {
        for (String[] menuItem : menuItems) {
            
            if (menuItem.length >= 5) {
                System.out.println("Item Number: " + menuItem[0]);
                System.out.println("Item Name: " + menuItem[1]);
                System.out.println("Price: RS:" + menuItem[2]);
                System.out.println("Description: " + menuItem[3]);
                System.out.println("Category: " + menuItem[4]); 
                
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println(" ");
            }
        }
    }
}


    //update menu item
    public static void updateMenuItem() {
        System.out.println("======== Update Menu Item =======");
        System.out.print("Enter Item Number to Update: ");
        String itemNumber = scanner.nextLine().trim();

        for (String[] menuItem : menuItems) {
            if (menuItem[0].equals(itemNumber)) {
                System.out.print("Enter New Item Name: ");
                menuItem[1] = scanner.nextLine().trim();
                System.out.print("Enter New Item Price: ");
                menuItem[2] = scanner.nextLine().trim();
                System.out.print("Enter New Item Description: ");
                menuItem[3] = scanner.nextLine().trim();
                System.out.print("Enter Item Category: ");
                menuItem[4] = scanner.nextLine().trim();
                saveMenuItems();
                System.out.println("Menu item updated successfully.");
               System.out.println("------------------------------------------------------------------");
                return;
            }
        }
        System.out.println("Item not found.");
    }
     
    //delete menu item
    public static void deleteMenuItem() {
        System.out.println("===== Delete Menu Item =====");
        System.out.print("Enter Item Number to Delete: ");
        String itemNumber = scanner.nextLine().trim();

        boolean itemFound = false;

        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i)[0].equals(itemNumber)) {
                menuItems.remove(i);
                saveMenuItems();
                System.out.println("Menu item deleted successfully.");
               System.out.println("------------------------------------------------------------------");
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found.");
            System.out.println("------------------------------------------------------------------");
        }
    }
    //user mannual
    public static void displayHelp() {
        System.out.println("======================================== Help===================================");
        System.out.println("1. Add New Customer Orders: Allows you to add new customer orders.");
        System.out.println("2. Display Customer Orders: Displays all customer orders.");
        System.out.println("3. Delete Customer Order: Deletes an existing customer order.");
        System.out.println("4. Update Customer Order: Updates details of an existing customer order.");
        System.out.println("5. Calculate and Print Bill: Calculates the total bill for a given order number.");
        System.out.println("6. Add New Menu Item: Adds a new item to the menu.");
        System.out.println("7. Display Menu Items: Displays all menu items.");
        System.out.println("8. Update Menu Item: Updates details of an existing menu item.");
        System.out.println("9. Delete Menu Item: Deletes an existing menu item.");
        System.out.println("10. Help: Displays this help information.");
        System.out.println("11. Exit: Exits the system.");
        System.out.println("=================================================================================");
    }
    //exit system
    public static void exitSystem() {
        System.out.println("Exiting the system. Goodbye!");
        
           System.out.println(" _________________________ ");
           System.out.println("|                         |");
           System.out.println("|     WELCOME BACK!       |");
           System.out.println("|_________________________|");
        
        System.out.println("------------------------------------------------------------------");
        
        System.exit(0);
        login();
        
    }
    //file reading
    public static void loadCustomerOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] order = line.split(",");
                customerOrders.add(order);
            }
        } catch (IOException e) {
            System.out.println("Error loading customer orders: " + e.getMessage());
        }
    }

    public static void loadMenuItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] menuItem = line.split(",");
                menuItems.add(menuItem);
            }
        } catch (IOException e) {
            System.out.println("Error loading menu items: " + e.getMessage());
        }
    }
//file saving
    public static void saveCustomerOrders() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (String[] order : customerOrders) {
                writer.write(String.join(",", order));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customer orders: " + e.getMessage());
        }
    }

    public static void saveMenuItems() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (String[] menuItem : menuItems) {
                writer.write(String.join(",", menuItem));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving menu items: " + e.getMessage());
        }
    }

    public static void saveBillToFile(String orderNumber, double total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/bills.txt", true))) {
            writer.write("Order Number: " + orderNumber + ", Total Bill Amount: RS:" + total);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving bill: " + e.getMessage());
        }
    }

    public static int getValidIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    //validations
    public static boolean validateCustomerOrder(String orderNumber, String customerName, String address, String phoneNumber, String email, String itemNumbers) {
        
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            System.out.println("Order number cannot be empty.");
            return false;
        }

       
        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return false;
        }

        
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return false;
        }

        
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches("\\d{10,15}")) {
            System.out.println("Phone number must be between 10 and 15 digits.");
            return false;
        }

       
        if (email == null || email.trim().isEmpty() || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        
        if (itemNumbers == null || itemNumbers.trim().isEmpty()) {
            System.out.println("Item numbers cannot be empty.");
            return false;
        }

        String[] items = itemNumbers.split(",");
        for (String item : items) {
            if (item.trim().isEmpty()) {
                System.out.println("Item numbers must not be empty.");
                return false;
            }
        }

        
        return true;
    }
      

    public static boolean validateMenuItem(String itemNumber, String itemName, String price, String description, String category) {
        
        if (itemNumber == null || itemNumber.trim().isEmpty()) {
            System.out.println("Item number cannot be empty.");
            return false;
        }

        
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return false;
        }

        
        try {
            double itemPrice = Double.parseDouble(price);
            if (itemPrice <= 0) {
                System.out.println("Price must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Price must be a valid number.");
            return false;
        }

       
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Description cannot be empty.");
            return false;
        }
        
         // Add validation logic for menu items 
         if (itemNumber.isEmpty() || itemName.isEmpty() || price.isEmpty() || description.isEmpty() || category.isEmpty()) {
             System.out.println("categoryadmin cannot be empty.");
        return false;
    }
        return true;
    }
       
}
