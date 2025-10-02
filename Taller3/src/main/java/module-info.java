module co.edu.uniquindio.Taller3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.Taller3 to javafx.fxml;
    opens co.edu.uniquindio.Taller3.controllers to javafx.fxml;
    opens co.edu.uniquindio.Taller3.models to javafx.base;
    
    exports co.edu.uniquindio.Taller3;
    exports co.edu.uniquindio.Taller3.controllers;
    exports co.edu.uniquindio.Taller3.models;
}

