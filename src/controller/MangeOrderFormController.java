package controller;

import TM.OrderTm;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.OrderDetailsBo;
import bo.custom.bo.BOFactory;
import bo.custom.impl.OrderBoImpl;
import bo.custom.impl.OrderDetailsBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.OrderDao;
import dao.custom.OrderDetailsDo;
import dao.custom.impl.OrderDaoImpl;
import dao.custom.impl.OrderDetailsDoImpl;
import dto.CustomerDto;
import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.OrderDetailsDto;
import dto.OrderDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MangeOrderFormController {
    public TableView tblOrders;
    public TextField txtSearchOrderId;
    public JFXTextField txtUpdateItemCode;
    public JFXTextField txtItemCode;
    public JFXTextField txtQty;
    public JFXTextField txtUpdateItemDiscount;
    

    public JFXTextField txtUpdateOrerCode;
    public JFXTextField txtUpdateQty;
    
    public JFXTextField txtRemoveOrerCode1;
    public JFXTextField txtRemoveCustomerCode;
    public JFXTextField txtRemoveOrderDate;
    public TextField txtSearchRemoveOrderId;
    public AnchorPane ManageContext;
    public JFXTextField txtUpdateTotal;
    
    public TableView tblSearchOrders;
    public TableColumn colIrderId;
    public TableColumn ColDate;
    public TableColumn ColCustomerId;
    public TextField txtSearchCustomerOrder;
    public JFXTextField txtunitprice;
    public JFXComboBox CMBoRDERiD;


    OrderDetailsBo orderDetailsDo= (OrderDetailsBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERDETAILS);
//    OrderDetailsDo orderDetailsDo= (OrderDetailsDo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
     OrderBo orderDao= (OrderBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDER);
    ItemBo itemBo= (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    public void initialize(){
       colIrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
      //  claculateQty();

    }
    public void HomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CashierForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) ManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }
    public void ModifyOrderDetailsOnAction(ActionEvent actionEvent) {
        String OrderCode= txtUpdateOrerCode.getText();
        String ItemCode= txtItemCode.getText();
        int qty= Integer.parseInt(txtUpdateQty.getText());
        double discount=Double.parseDouble( txtUpdateItemDiscount.getText()) ;
        double total= Double.parseDouble(txtUpdateTotal.getText());


        try {
            orderDetailsDo.updateOrderdetails( new OrderDetailsDto(
                    OrderCode,ItemCode,qty,discount,total
            ));
            new Alert(Alert.AlertType.CONFIRMATION,"Order Updated").show();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Something Went Wrong").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void txtSearchItemOnAction(ActionEvent actionEvent)  {
        try {
            OrderDetailsDto search = orderDetailsDo.search(txtSearchOrderId.getText());
            txtUpdateOrerCode.setText(search.getOid());
            txtItemCode.setText(search.getItemCode());
            txtUpdateQty.setText(String.valueOf(search.getQty()));
            txtUpdateItemDiscount.setText(String.valueOf(search.getDiscount()));
            txtUpdateTotal.setText(String.valueOf(search.getTotalPrice()));
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Please Check Order Id ("+txtSearchOrderId.getText()+")").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void textFields_Key_Released_Meals(KeyEvent keyEvent) {
    }
    public void RemoveOrderDetailsOnAction(ActionEvent actionEvent) {
        try {
            orderDao.deleteOrder(txtSearchRemoveOrderId.getText());
            new Alert(Alert.AlertType.CONFIRMATION,"Remove Order").show();
            txtRemoveOrerCode1.clear();
            txtRemoveCustomerCode.clear();
            txtRemoveOrderDate.clear();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Something Went Wrong Check"+txtSearchRemoveOrderId.getText()).show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void txtRemoveSearchItemOnAction(ActionEvent actionEvent) {
        try {
            OrderDto search = orderDao.search(txtSearchRemoveOrderId.getText());
            txtRemoveOrerCode1.setText(search.getOid());
            txtRemoveCustomerCode.setText(search.getCustomerId());
            txtRemoveOrderDate.setText(search.getDate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearchOrderCusIdOnAction(ActionEvent actionEvent) {
        tblSearchOrders.getItems().clear();
        try {
            ArrayList<OrderDto> allOrder = orderDetailsDo.getAllOrdersByCusId(txtSearchCustomerOrder.getText());
            for (OrderDto dto:allOrder
            ) {
                tblSearchOrders.getItems().add(new OrderTm(dto.getOid(),dto.getDate(),dto.getCustomerId()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   /* private void claculateQty(){
        double unitprice=Double.parseDouble(txtunitprice.getText());
        int qty= Integer.parseInt(txtQty.getText());
        double total=Double.parseDouble(txtUpdateTotal.getText())-unitprice;
        try {
                ItemDto search = itemBo.search(txtItemCode.getText());
                txtunitprice.setText(String.valueOf(search.getUnitPrice()));
                txtUpdateTotal.setText(String.valueOf(total));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }*/

}
