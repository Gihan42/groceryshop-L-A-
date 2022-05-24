package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController {
    public Button btnlogin;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public CheckBox checkBtnShowpw;
    public JFXTextField TxtShowPw;
    public Button btnLogout;
    public ProgressBar progresProses;
    public AnchorPane loginContext;
    public Label lblPercentage;

    public void initialize(){

    }
    public void loginOnaction(ActionEvent actionEvent) throws IOException {
        login();
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }

    public void ShowPasswordOnAction(ActionEvent actionEvent) {
        if (checkBtnShowpw.isSelected()) {
            pwdPassword.setVisible(false);
            TxtShowPw.setVisible(true);
            TxtShowPw.setText(pwdPassword.getText());
        } else {
            pwdPassword.setVisible(true);
            TxtShowPw.setVisible(false);
        }

    }

    private void login() throws IOException {
        String userName = txtUserName.getText();
        String pawd = pwdPassword.getText();
        if (userName.equalsIgnoreCase("gihan") && pawd.equalsIgnoreCase("123")) {
            Progress();
            lblPercentage.setVisible(true);
        } else {
            new Alert(Alert.AlertType.WARNING, "please try again").show();
        }
    }

    public void EnterOnAction(ActionEvent actionEvent) throws IOException {
        // login();
    }


    private Object Validate() {
        Pattern usernamePattern = Pattern.compile("^gihan$");
        Pattern passwordPattern = Pattern.compile("^123$");

        if (!usernamePattern.matcher(txtUserName.getText()).matches()) {
            //if the input is not matching
            addError(txtUserName);
            return txtUserName;
        } else {
            removeError(txtUserName);
            if (!passwordPattern.matcher(pwdPassword.getText()).matches()) {
                addError(pwdPassword);
                return pwdPassword;
            } else {
                removeError(pwdPassword);

            }
        }
        return true;
    }

    private void addError(TextField txtField) {
        if (txtField.getText().length() > 0) {
            txtField.setStyle("-fx-border-color: red");
            // txtField.getParent().setStyle("-fx-background-color: red");
        }
        btnlogin.setDisable(true);
    }

    private void removeError(TextField txtField) {
        txtField.setStyle("-fx-border-color: green");
        //    txtField.getParent().setStyle("-fx-background-color: green");
        btnlogin.setDisable(false);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) throws IOException {
        Validate();
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = Validate();
            //if the response is a text field
            //that means there is a error
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                login();
            }
        }
    }

    public void logoutOnAction(ActionEvent actionEvent) {
    }

    private void Progress() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(50);
                }
                return null;
            }
        };

        progresProses.progressProperty().bind(task.progressProperty());

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        progresProses.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                int presentage = (int) Math.round((Double) newValue * 100);
                lblPercentage.setText(presentage + "%");


                //set Your task for this
                if (presentage == 100) {
                    try {
                        setUI("AdminForm");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
