package co.edu.uniquindio.Taller3.repositories;

import co.edu.uniquindio.Taller3.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static ProductRepository instance;
    private List<Product> products;

    private ProductRepository() {
        products = new ArrayList<>();

        initializeSampleData();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private void initializeSampleData() {
        products.add(new Product("PROD001", "Laptop Gamer", "Laptop para gaming de alta gama", 1500.00, 10));
        products.add(new Product("PROD002", "Mouse Inalámbrico", "Mouse ergonómico inalámbrico", 25.50, 50));
        products.add(new Product("PROD003", "Teclado Mecánico", "Teclado mecánico RGB", 89.99, 25));
        products.add(new Product("PROD004", "Monitor 24\"", "Monitor Full HD 24 pulgadas", 199.99, 15));
    }
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Product searchPerCode(String codigo) {
        return products.stream()
                .filter(product -> product.getCode().equals(codigo))
                .findFirst()
                .orElse(null);
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updateProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCode().equals(updateProduct.getCode())) {
                products.set(i, updateProduct);
                break;
            }
        }
    }

    public void deleteProduct(Product product) {
        products.removeIf(p -> p.getCode().equals(product.getCode()));
    }


    public List<Product> getProductsWithStockLow(int stockMinimum) {
        return products.stream()
                .filter(product -> product.getStock() < stockMinimum)
                .toList();
    }
}