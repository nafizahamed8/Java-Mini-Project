import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Inventory {

    private List<Product> products;//stores all product

    public Inventory(){
        products=new ArrayList<>();//creates an empty cart

    }

    public void addProduct(Product product){
        products.add(product);//adds a new item in the cart
    }

    public void updateStock(int productId,int quantityChange){
        for(Product p: products){
            if(p.getId()==productId){
                p.setQuantity(p.getQuantity()+quantityChange);//updates quantity
                return;
            }
        }
        System.out.println("Products not found");

    }
    public List<Product>getAllProducts(){
        return new ArrayList<>(products);// returns a copy of cart
    }

    public Product searchProduct(int id){
        for(Product p:products){
            if(p.getId()==id){
                return p;//returns the product if found
            }
        }
        return null;//if not found

    }

    public boolean removeProduct(int id){
        return products.removeIf(p-> p.getId()==id );//remove product in 2 steps.modern java implementation
        //java 's lambda expression
    }

    public void saveToFile(String filename)throws IOException{
        try(PrintWriter writer=new PrintWriter(filename)){
            for(Product p: products){
                writer.println(p.getId() +"," + p.getName() +","+p.getPrice() +"," + p.getQuantity());
            }
            //saves data in csv format
        }
    }
    public void loadFromFile(String filename)throws IOException{
        products.clear();
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            //Efficiently reads line by line BufferReader
            //Filereader connects to the file.
            String line;
            while((line = reader.readLine())!=null){
                String[]data=line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int qty = Integer.parseInt(data[3]);
                products.add(new Product(id,name, price ,qty));//rebuild products

            }

        }
    }
}
