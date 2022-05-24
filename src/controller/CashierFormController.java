package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierFormController {
    public AnchorPane MainContext;

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PlaceOrderForm");
    }

    public void searchOrderOnAction(ActionEvent actionEvent) {
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) MainContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Main");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void ManageOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ManageOrderForm");
    }
}
