package co.edu.uniquindio.Taller3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class DashboardController {

    @FXML
    private VBox mainContainer;
    @FXML
    private void onSeeProducts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/Taller3/vista/ListadoProducto.fxml"));
            Parent listProducts = loader.load();

            ListProductController controller = loader.getController();
            controller.setDashboardController(this);

            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(listProducts);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la lista de productos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void showDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/Taller3/vista/Dashboard.fxml"));
            Parent dashboard = loader.load();

            DashboardController controller = loader.getController();
            controller.setMainContainer(this.mainContainer);

            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(dashboard);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VBox getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(VBox mainContainer) {
        this.mainContainer = mainContainer;
    }

    private void mostrarAlerta(String title, String message, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}