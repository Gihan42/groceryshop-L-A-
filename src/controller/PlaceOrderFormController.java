package controller;

import TM.CartTm;
import bo.custom.PurchOrderBo;
import bo.custom.bo.BOFactory;
import bo.custom.impl.PurchOrderBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceOrderFormController {
    public AnchorPane placeOrderContext;
    public JFXComboBox <String>cmbCustomerId;
    public JFXComboBox <String>cmbItemCode;
    public Label lblDate;
    public Label lblTime;

    public JFXTextField txtCustomerName;

    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXTextField txtPackSize;
    public JFXTextField txtDiscount;
    public TableView <CartTm>tblOrderDetails;

    public Label lblOrderId;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colDelete;

    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public JFXButton btnPrintbil;
    private String orderId;

   // CustomerDao dto=new CustomerDaoIMPL();
   // ItemDao itemDAO=new ItemDaoImpl();
    OrderDao orderDAO=new OrderDaoImpl();
   // OrderDetailsDo orderDetailsDAO=new OrderDetailsDoImpl();
    PurchOrderBo purchaseOrderBO= (PurchOrderBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PURCHASE_ORDER);
    ObservableList<CartTm> tmList=FXCollections.observableArrayList();

    public void initialize(){

        try {
            LoadCustomersId();
            LoadAllItemCode();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        generateDateTime();
        generateNewOrderId();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        btnPlaceOrder.setDisable(true);
        orderId = generateNewOrderId();
        lblOrderId.setText( orderId);
        btnPrintbil.setDisable(true);

    }
    private void generateDateTime() {
        lblDate.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void txtQty_OnAction(ActionEvent actionEvent) {
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        int qty=Integer.parseInt(txtQty.getText());
        double discount=Double.parseDouble(txtDiscount.getText());
        double totalCost=(unitPrice*qty)-(unitPrice*qty)*discount/100 ;

        CartTm isexists=isExist(cmbItemCode.getValue());
        if( isexists!=null){
            System.out.println(isexists);
            for (CartTm temp:tmList) {
                if(temp.equals(isexists)){
                    temp.setQty(temp.getQty()+qty);
                    temp.setTotal(temp.getTotal()+totalCost);
                    tblOrderDetails.refresh();
                }
            }
        }
            else {
                Button btn=new Button("Delete");
                CartTm tm = new CartTm(
                        cmbItemCode.getValue(),
                        txtDescription.getText(),
                        qty,
                        (int) unitPrice,
                        totalCost,
                        btn
                );
                btn.setOnAction(e->{

                                tmList.remove(tm);
                                CalculateTotal();
                        }

                );
                tmList.add(tm);
                tblOrderDetails.setItems(tmList);
            }
            btnPlaceOrder.setDisable(false);
            CalculateTotal();
    }
    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
      /*  OrderDto order=new OrderDto(
                orderId,
                lblDate.getText(),
                cmbCustomerId.getValue(),
                Double.parseDouble(lblTotal.getText())
        );
        ArrayList<OrderDetailsDto>details=new ArrayList<>();
        for (CartTm tm:tmList
        ) {
            details.add(new OrderDetailsDto(
                    orderId,
                    tm.getCode(),
                    tm.getQty(),
                    Double.parseDouble(txtDiscount.getText()),
                    Double.parseDouble(lblTotal.getText())
            ));
        }
        Connection connection=null;
        try{
            connection= DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSave= new PurchOrderBoImpl().saveOrder(order);
            if(isOrderSave){
                boolean isDetailSave =purchaseOrderBO.saveOrderDetails(details);
                if(isDetailSave){
                    connection.commit();
                    new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
                }
            }
            else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"try Again").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
*/
        double discount=Double.parseDouble(txtDiscount.getText());
        String oId = lblOrderId.getText();
        String cId = cmbCustomerId.getValue();
        String date = "2022-05-14";
        OrderDto orderDTO = new OrderDto(oId,date,cId,Double.parseDouble(lblTotal.getText()));
        ArrayList<OrderDetailsDto> dtoLst = new ArrayList<>();
        for (CartTm tdm : tmList) {
            dtoLst.add(new OrderDetailsDto(oId,tdm.getCode(), tdm.getQty(), discount, tdm.getTotal()));

        }
        try {
            if(purchaseOrderBO.purchaseOrder(orderDTO,dtoLst)){
                new Alert(Alert.AlertType.INFORMATION,"order saved successfully!").show();
                lblOrderId.setText(purchaseOrderBO.generateNewOrderID());
                // refreshing the choose_item table
                //loadItemsToChoose();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"order cannot be saved \n error : " + e.getMessage());
        }

        orderId = generateNewOrderId();
        lblOrderId.setText( orderId);
        CalculateTotal();
        btnPrintbil.setDisable(false);

    }

  /*  public boolean saveOrder(String orderId, LocalDate orderDate, String customerId,double total, List<OrderDetailsDto> orderDetails) throws SQLException, ClassNotFoundException {
        try {
            purchaseOrderBO.purchaseOrder(new OrderDto(orderId, orderDate, customerId,total,orderDetails));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }*/
    public void cmbCustomerOnAction(ActionEvent actionEvent) {
       String id= cmbCustomerId.getValue();
        try {
            CustomerDto search = purchaseOrderBO.searchCustomer(id);
            txtCustomerName.setText(search.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ItemCodeOnAction(ActionEvent actionEvent) {
      String code= cmbItemCode.getValue();
        try {
            ItemDto search = purchaseOrderBO.searchItem(code);
            txtDescription.setText(search.getDescription());
            txtQtyOnHand.setText(String.valueOf(search.getQtyOnhand()));
            txtPackSize.setText(search.getPackSize());
            txtUnitPrice.setText(String.valueOf(search.getUnitPrice()));
            txtDiscount.setText(String.valueOf(search.getDiscount()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private CartTm isExist(String itemCode){
       for (CartTm tm:tmList) {
            if (tm.getCode().equals(itemCode)){
                return tm;
            }
        }
        return null;
    }
    private void CalculateTotal(){
        double total=0;
        for (CartTm tn:tmList
             ) {
            total+=tn.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }
    public void BackHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CashierForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) placeOrderContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }
    public String generateNewOrderId() {
        try {
         return   purchaseOrderBO.generateNewOrderID();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OID-001";
    }
    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return purchaseOrderBO.checkItemIsAvailable(code);
    }
    private void LoadCustomersId() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> all= purchaseOrderBO.getAllCustomers();
        for (CustomerDto cus:all
             ) {
            cmbCustomerId.getItems().add(cus.getId());
        }
    }
    private void LoadAllItemCode() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> allItem=purchaseOrderBO.getAllItems();
        for(ItemDto dto:allItem){
            cmbItemCode.getItems().add(dto.getCode());
        }
    }
    }



