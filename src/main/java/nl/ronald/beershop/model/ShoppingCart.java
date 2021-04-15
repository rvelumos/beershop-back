//package nl.ronald.beershop.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShoppingCart {
//
//    private int orderNum;
//
//    private Customer customer;
//
//    private final List<Product> cartItems = new ArrayList<Product>();
//
//    public void addProductToCartByID(int id) {
//        Product product = getProductByProductID(id);
//        addToCart(product);
//    }
//
//    private Product getProductByProductID(int id) {
//        Product product = null;
//        List<Product> products = new Products.getProducts();
//        for (Product prod: products) {
//            if (prod.id() == id) {
//                product = prod;
//                break;
//            }
//        }
//        return product;
//    }
//
//    private void addToCart(Product product) {
//        cartItems.add(product);
//    }
//
//    public void removeProductByPID(int pid) {
//        Product prod = getProductByProductID(pid);
//        cartItems.remove(prod);
//    }
//
//    void printCartItems() {
//        for (Product prod: cartItems) {
//            System.out.println(prod.getName());
//        }
//    }
//
//}
