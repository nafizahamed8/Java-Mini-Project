public class Product {
    //private variable to store product data
    private int id;
    private String name;
    private double price;
    private int quantity;

    //constructor initialize an new product object
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //tostring formats product info for display
    @Override
    public String toString(){
        return String.format("ID:%d | %s | Price:$%.2f | Qty:%d ",id,name,price,quantity);

    }
}
