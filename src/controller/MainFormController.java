package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane MainContext;

    public void btnAdministratorOnAction(ActionEvent actionEvent) throws IOException {
        setUI("AdminstoratorForm");
    }

    public void btnCashierOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CashierForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) MainContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }
}
