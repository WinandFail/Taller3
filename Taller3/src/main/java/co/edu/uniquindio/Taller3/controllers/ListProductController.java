package co.edu.uniquindio.Taller3.controllers;

import co.edu.uniquindio.Taller3.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import co.edu.uniquindio.Taller3.repositories.ProductRepository;
import java.io.IOException;

public class ListProductController {

    @FXML
    private VBox mainContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Product, String> colCode;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, String> colDescription;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, Integer> colStock;

    @FXML
    private Button btnCreateProduct;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    private ProductRepository productRepository;
    private ObservableList<Product> productsList;
    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        productRepository = ProductRepository.getInstance();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrice.setCellFactory(column -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", price));
                }
            }
        });
        loadProducts();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    public void loadProducts() {
        productsList = FXCollections.observableArrayList(productRepository.getProducts());
        tableProducts.setItems(productsList);
    }
    @FXML
    private void onCreateProduct() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/Taller3/vista/FormularioProducto.fxml"));
            Parent form = loader.load();
            ProductFormController controller = loader.getController();
            controller.setListProductController(this);
            if (dashboardController != null) {
                dashboardController.getMainContainer().getChildren().clear();
                dashboardController.getMainContainer().getChildren().add(form);
            }
        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar el formulario", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    @FXML
    private void onEditProduct() {
        Product productSelected = tableProducts.getSelectionModel().getSelectedItem();
        if (productSelected == null) {
            showAlert("Advertencia", "Por favor seleccione un producto para editar", Alert.AlertType.WARNING);
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/Taller3/vista/FormularioProducto.fxml"));
            Parent form = loader.load();
            ProductFormController controller = loader.getController();
            controller.setListProductController(this);
            controller.setEditProduct(productSelected);
            if (dashboardController != null) {
                dashboardController.getMainContainer().getChildren().clear();
                dashboardController.getMainContainer().getChildren().add(form);
            }

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar el formulario de edición", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    @FXML
    private void onDeleteProduct() {
        Product productSelected = tableProducts.getSelectionModel().getSelectedItem();
        if (productSelected == null) {
            showAlert("Advertencia", "Por favor seleccione un producto para eliminar", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar eliminación");
        confirmation.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmation.setContentText("Producto: " + productSelected.getName());
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                productRepository.deleteProduct(productSelected);
                loadProducts();
                showAlert("Éxito", "Producto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }
    @FXML
    private void onBack() {
        if (dashboardController != null) {
            dashboardController.showDashboard();
        }
    }
    public void refreshTable() {
        loadProducts();
    }
    public DashboardController getDashboardController() {
        return dashboardController;
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}