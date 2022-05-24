package controller;

import TM.CustomerTm;
import bo.custom.CustomerBo;
import bo.custom.bo.BOFactory;
import bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.CustomerDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {
    public Button btnSave;
    public JFXTextField tstSaveCusId;
    public JFXTextField txtSaveCusName;
    public JFXTextField txtSaveCusAddress;
    public JFXTextField txtSaveCustomerTitle;
    public JFXTextField txtSaveCustomerCity;
    public JFXTextField txtSaveCustomerProvince;
    public JFXTextField txtSaveCustomerPostalCode;
    
    public TableColumn colId1;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn ColProvince;
    public TableColumn colPostalCode;
    public TableView tblCustomer1;
    
    public JFXTextField txtUpdateCustomerName;
    public JFXTextField txtUpdateCustomerCity;
    public JFXTextField txtUpdateCustomerId;
    public JFXTextField txtUpdateCusTitle;
    public JFXTextField txtUpdateCustomerAddress;
    public JFXTextField txtUpdateCustomerProvince;
    public JFXTextField txtUpdateCustomerPostalCode;
    
    public JFXTextField txtDeleteCustomerTitle1;
    public JFXTextField tstDeleteCusId1;
    public JFXTextField txtDeleteCustomerPostalCode1;
    public JFXTextField txtDeleteCustomerCity1;
    public JFXTextField txtDeleteCusName1;
    public JFXTextField txtDeleteCusAddress1;
    public JFXTextField txtDeleteCustomerProvince1;

    public AnchorPane customerContext;

    CustomerBo customerBo= (CustomerBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    public void initialize(){
        try {
            getAllCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("CusTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        ColProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

    }
    public void SaveOnAction(ActionEvent actionEvent)  {
        String id = tstSaveCusId.getText();
        String name = txtSaveCusName.getText();
        String title=txtSaveCustomerTitle.getText();
        String address=txtSaveCusAddress.getText();
        String province=txtSaveCustomerProvince.getText();
        String postalCode=txtSaveCustomerPostalCode.getText();
        String city=txtSaveCustomerCity.getText();

        boolean b= false;
        try {
            b = customerBo.saveCustomer(new CustomerDto(id,title,name,address,city,province,postalCode));
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Save").show();
                getAllCustomer();
            }
            else{
                new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void getAllCustomer() throws SQLException, ClassNotFoundException {
        tblCustomer1.getItems().clear();
        ArrayList<CustomerDto> allCustomer = customerBo.getAllCustomer();
        for (CustomerDto dto:allCustomer
             ) {
            tblCustomer1.getItems().add(new CustomerTm(dto.getId(),dto.getCusTitle(),dto.getName(),dto.getAddress(),
                    dto.getCity(),dto.getProvince(),dto.getPostalCode()));
        }
    }
    public void UpddateOnAction(ActionEvent actionEvent){
        /*CustomerDto dto=new CustomerDto(
                txtUpdateCustomerId.getText(),txtUpdateCusTitle.getText(),txtUpdateCustomerName.getText(),
                txtUpdateCustomerAddress.getText(),txtUpdateCustomerCity.getText(),txtUpdateCustomerProvince.getText(),txtUpdateCustomerPostalCode.getText()
        );
        try {
           if( CrudUtil.executeUpdate("UPDATE  Customer SET CusTitle=? , name=? ,  address=? , City=? , Province=? , PostalCode=? WHERE id=?",
                    dto.getCusTitle(), dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(),
                    dto.getPostalCode(), dto.getId()));
            {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
            }
            getAllCustomer();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        String id = txtUpdateCustomerId.getText();
        String name = txtUpdateCustomerName.getText();
        String title=txtUpdateCusTitle.getText();
        String address=txtUpdateCustomerAddress.getText();
        String province=txtUpdateCustomerProvince.getText();
        String postalCode=txtUpdateCustomerPostalCode.getText();
        String city=txtUpdateCustomerCity.getText();
        try {
          boolean b= customerBo.updateCustomer(new CustomerDto(id,title,name,address,city,province,postalCode));
          if(b){
              new Alert(Alert.AlertType.CONFIRMATION, "Update Save").show();
              getAllCustomer();
          }
          else{
              new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
          }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void SearchCustomerOnAction(ActionEvent actionEvent) {
            search();
    }
    private void search() {
        CustomerDto search = null;
        try {
           boolean b= customerBo.existCustomer(txtUpdateCustomerId.getText());
            if(!b){
                new Alert(Alert.AlertType.ERROR, " Please Check Id "+ txtUpdateCustomerId.getText()).show();
            }
            search = customerBo.search(txtUpdateCustomerId.getText());
            txtUpdateCusTitle.setText(search.getCusTitle());
            txtUpdateCustomerName.setText(search.getName());
            txtUpdateCustomerAddress.setText(search.getAddress());
            txtUpdateCustomerCity.setText(search.getCity());
            txtUpdateCustomerProvince.setText(search.getProvince());
            txtUpdateCustomerPostalCode.setText(search.getPostalCode());
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Id").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DeleteOnAction(ActionEvent actionEvent) {
        try {
                customerBo.deleteCustomer(tstDeleteCusId1.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted").show();
                getAllCustomer();
            txtDeleteCustomerTitle1.clear();
            txtDeleteCusName1.clear();
            txtDeleteCusAddress1.clear();
            txtDeleteCustomerCity1.clear();
            txtDeleteCustomerProvince1.clear();
            txtDeleteCustomerPostalCode1.clear();
            } catch (SQLException sqlException) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Id").show();
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

    }
    public void SearchDOnAction(ActionEvent actionEvent)  {
      /*  ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?", tstDeleteCusId1.getText());
        if(rst.next()){
            txtDeleteCusName1.setText(rst.getString(3));
            txtDeleteCustomerTitle1.setText(rst.getString(2));
            txtDeleteCustomerCity1.setText(rst.getString(5));
            txtDeleteCusAddress1.setText(rst.getString(4));
            txtDeleteCustomerProvince1.setText(rst.getString(6));
            txtDeleteCustomerPostalCode1.setText(rst.getString(7));
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Pleace Check Id").show();
        }*/
        CustomerDto search = null;
        try {
           boolean b= customerBo.existCustomer(tstDeleteCusId1.getText());
            if(!b){
                new Alert(Alert.AlertType.ERROR, " Please Check Id "+ tstDeleteCusId1.getText()).show();
            }
            search = customerBo.search(tstDeleteCusId1.getText());
            txtDeleteCustomerTitle1.setText(search.getCusTitle());
            txtDeleteCusName1.setText(search.getName());
            txtDeleteCusAddress1.setText(search.getAddress());
            txtDeleteCustomerCity1.setText(search.getCity());
            txtDeleteCustomerProvince1.setText(search.getProvince());
            txtDeleteCustomerPostalCode1.setText(search.getPostalCode());
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Id").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void HomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CashierForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) customerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("Admin Form");
    }

    public void BtnPLaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PlaceOrderForm");
    }
}
