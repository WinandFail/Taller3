package co.edu.uniquindio.Taller3.controllers;

import co.edu.uniquindio.Taller3.models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import co.edu.uniquindio.Taller3.repositories.ProductRepository;
import java.io.IOException;

public class ProductFormController {

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtStock;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private ProductRepository productRepository;
    private ListProductController listProductController;
    @FXML
    private VBox mainContainer;
    private boolean esEdition = false;
    private Product editProduct;

    @FXML
    public void initialize() {
        productRepository = ProductRepository.getInstance();
    }
    public void setListProductController(ListProductController listProductController) {
        this.listProductController = listProductController;
        if (listProductController != null && listProductController.getDashboardController() != null) {
            this.mainContainer = listProductController.getDashboardController().getMainContainer();
        }
    }
    public void setEditProduct(Product product) {
        this.esEdition = true;
        this.editProduct = product;
        txtCode.setText(product.getCode());
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtStock.setText(String.valueOf(product.getStock()));
        txtCode.setDisable(true);
        btnSave.setText("Actualizar Producto");
    }
    @FXML
    private void onSaveProduct() {
        if (!validFields()) {
            return;
        }
        try {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (esEdition) {
                editProduct.setName(name);
                editProduct.setDescription(description);
                editProduct.setPrice(price);
                editProduct.setStock(stock);

                productRepository.updateProduct(editProduct);
                showAlert("Éxito", "Producto actualizado correctamente", Alert.AlertType.INFORMATION);
            } else {
                // Crear nuevo product - Verificar si el código ya existe
                if (productRepository.searchPerCode(code) != null) {
                    showAlert("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                    return;
                }

                Product newProduct = new Product(code, name, description, price, stock);
                productRepository.addProduct(newProduct);
                showAlert("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);
            }
            backToList();

        } catch (NumberFormatException e) {
            showAlert("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancel() {
        backToList();
    }

    private void backToList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/Taller3/vista/ListadoProducto.fxml"));
            Parent listadoProductos = loader.load();

            ListProductController controller = loader.getController();
            if (listProductController != null) {
                controller.setDashboardController(listProductController.getDashboardController());
            }
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(listadoProductos);

        } catch (IOException e) {
            showAlert("Error", "No se pudo volver al listado de productos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    private boolean validFields() {
        if (txtCode.getText().trim().isEmpty()) {
            showAlert("Error de validación", "El código es obligatorio", Alert.AlertType.WARNING);
            txtCode.requestFocus();
            return false;
        }
        if (txtName.getText().trim().isEmpty()) {
            showAlert("Error de validación", "El nombre es obligatorio", Alert.AlertType.WARNING);
            txtName.requestFocus();
            return false;
        }
        if (txtDescription.getText().trim().isEmpty()) {
            showAlert("Error de validación", "La descripción es obligatoria", Alert.AlertType.WARNING);
            txtDescription.requestFocus();
            return false;
        }
        if (txtPrice.getText().trim().isEmpty()) {
            showAlert("Error de validación", "El precio es obligatorio", Alert.AlertType.WARNING);
            txtPrice.requestFocus();
            return false;
        }
        if (txtStock.getText().trim().isEmpty()) {
            showAlert("Error de validación", "El stock es obligatorio", Alert.AlertType.WARNING);
            txtStock.requestFocus();
            return false;
        }
        try {
            double price = Double.parseDouble(txtPrice.getText().trim());
            if (price < 0) {
                showAlert("Error de validación", "El price debe ser un valor positivo", Alert.AlertType.WARNING);
                txtPrice.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Error de validación", "El precio debe ser un número válido", Alert.AlertType.WARNING);
            txtPrice.requestFocus();
            return false;
        }
        try {
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                showAlert("Error de validación", "El stock debe ser un valor positivo", Alert.AlertType.WARNING);
                txtStock.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Error de validación", "El stock debe ser un número entero válido", Alert.AlertType.WARNING);
            txtStock.requestFocus();
            return false;
        }

        return true;
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}