import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();//creates an empty inventory
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n🛒 Inventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Stock");
            System.out.println("3. List All Products");
            System.out.println("4. Search Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Save Inventory");
            System.out.println("7. Load Inventory");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();//takes user choice
            scanner.nextLine();//prevents input bugs

            switch (choice) {
                case 1:
                    addProduct(inventory, scanner);
                    break;
                case 2:
                    updateStock(inventory, scanner);
                    break;
                case 3:
                    searchProduct(inventory,scanner);
                    break;
                case 4:
                    removeProduct(inventory,scanner);
                case 5:
                    saveInventory(inventory,scanner);
                case 6:
                    loadInventory(inventory,scanner);
                case 7:
                    exitProgram(scanner);
            }

        }
    }

    private static void addProduct(Inventory inv, Scanner sc) {
        //helper method only usable in main class
        System.out.println("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();//clears leftover Enter keypress
        System.out.println("Get Product Name: ");
        String name = sc.nextLine();//uses next line to capture names with spaces

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();  // Clear buffer

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();  // Clear buffer

        inv.addProduct(new Product(id, name, price, qty));
        System.out.println("New product added!");

    }

    private static void updateStock(Inventory inv, Scanner sc) {
        System.out.println("Enter product Id: ");
        int id = sc.nextInt();
        System.out.println("Enter quantity change(+-): ");
        int change = sc.nextInt();

        inv.updateStock(id, change);

    }

    private static void listProducts(Inventory inv){
        System.out.println("\n📋 Product List");
        System.out.println("---------------------------------------------");
        System.out.println("ID  | Name               | Price    | Qty");
        System.out.println("---------------------------------------------");

        for (Product p : inv.getAllProducts()){
            System.out.printf("%-3d | %-18s |$%-7.2f |%-3d%n",p.getId(),p.getName(),p.getPrice(),p.getQuantity());

        }
        if(inv.getAllProducts().isEmpty()){
            System.out.println("No products found");

        }
        System.out.println("---------------------------------------------");
    }

    private static void searchProduct(Inventory inv,Scanner sc){
        System.out.println("Enter product Id to search: ");
        int id=sc.nextInt();
        sc.nextLine();//clear buffer

        Product found=inv.searchProduct(id);

        if (found != null) {
            System.out.println("\n🔍 Product Found:");
            System.out.println("ID: " + found.getId());
            System.out.println("Name: " + found.getName());
            System.out.println("Price: $" + found.getPrice());
            System.out.println("Quantity: " + found.getQuantity());
        }else {
            System.out.println("❌ Product not found!");
        }

    }
    private static void removeProduct(Inventory inv,Scanner scanner){
        listProducts(inv);

        System.out.println("Enter product Id to remove: ");
        int id= scanner.nextInt();
        scanner.nextLine();

        Product product=inv.searchProduct(id);
        if (product==null){
            System.out.println("Product not found");
            return;
        }
        // Show product details
        System.out.println("\n⚠️ About to remove:");
        System.out.println(product); // Uses toString()

        // Get confirmation
        System.out.print("Are you sure? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if(confirmation.equals("y")){
            boolean wasRemoved=inv.removeProduct(id);
            System.out.println(wasRemoved ? "✅ Removed!" : "❌ Failed!");

        }else {
            System.out.println("🚫 Removal cancelled.");
        }


    }
    private static void saveInventory(Inventory inv,Scanner sc){
        System.out.println("Enter File name to save: ");
        String filename=sc.nextLine().trim();

        try{
            inv.saveToFile(filename);
            System.out.println("✅ Inventory saved successfully to: " + filename);
        }catch (IOException e){
            System.out.println("❌ Error saving file: " + e.getMessage());
            System.out.println("Tip: Check write permissions or file path.");
        }





    }
    //loads product from file
    //replace current inventory
    private static void loadInventory(Inventory inv,Scanner sc){

        System.out.println("Enter filename to load: ");
        String filename=sc.nextLine().trim();
        System.out.println("Tip: Check write permissions or file path.");


        try{
            inv.loadFromFile(filename);
            System.out.println("✅ Inventory loaded successfully");
            System.out.println("Loaded "+ inv.getAllProducts().size() + " products" );
        }catch (IOException e){
            System.out.println("❌ Error loading file: " + e.getMessage());
            System.out.println("Tip: file might not exist or  is corrupted");
        }catch (NumberFormatException e) {
            System.out.println("❌ Corrupted data in file!");
            System.out.println("Tip: Ensure file has correct ID,Price,Quantity format.");
        }

    }
    /**
     * Safely exits the program with confirmation
     */
    private static void exitProgram(Scanner scanner) {
        System.out.print("\n⚠️ Are you sure you want to exit? (Y/N): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("y")) {
            System.out.println("\n💾 Saving inventory to 'autosave.txt'...");
            try {
                new Inventory().saveToFile("autosave.txt"); // Quick save
                System.out.println("✅ Auto-save complete!");
            } catch (IOException e) {
                System.out.println("❌ Auto-save failed: " + e.getMessage());
            }

            System.out.println("\n🛑 Exiting program...");
            scanner.close();
            System.exit(0); // Graceful exit
        } else {
            System.out.println("🚀 Continue working!");
        }
    }

}