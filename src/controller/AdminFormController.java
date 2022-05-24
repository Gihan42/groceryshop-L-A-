package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane AdminContext;
    public Label lblName;
    public Button btnCustomer;

    public void ItemOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ItemForm");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        lblName.setText("Manage Customer");
        setUI("CustomerForm");
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void searchOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SystemReportForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) AdminContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Main");
    }
}
