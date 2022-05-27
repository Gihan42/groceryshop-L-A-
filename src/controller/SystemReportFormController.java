package controller;

import TM.LeastMovableMostMovbleTm;
import TM.OrderDailyTm;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.SystemReportBo;
import bo.custom.bo.BOFactory;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.SystemReportBoImpl;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.ReportDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SystemReportFormController {
    public AnchorPane SystemReportContext;

    public DatePicker datePicker;

    public TableColumn DColOrderId;
    public TableColumn DCOLdATE;
    public TableColumn dcolCustomerId;
    public TableView tblDaileIncomeTable;
    public Button btnload;

    public TableView<LeastMovableMostMovbleTm> tblMostMovebleItem;
    public TableColumn colMOrderId;
    public TableColumn colMItemCode;
    public TableColumn colMQty;
    public TableColumn colMDiscount;

    public JFXTextField txtMOrderId;
    public JFXTextField txtMItemCode;
    public JFXTextField txtMQty;
    public JFXTextField txtMDiscount;

    public JFXTextField txtMDescription;
    public JFXTextField txtMPackSize;
    public JFXTextField txtMQtyoHand;
    public JFXTextField txtMUnitPrice;

    public Label lblTitle;

    public Label lblTotal;
    public Label lblSales;
    public BarChart barChartForReports;


    SystemReportBo systemReportBo= (SystemReportBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.SYSTEM_REPORT);
    ItemBo itemBo= (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    OrderBo orderBo= (OrderBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDER);

    public void initialize() {
   /*   /  DColOrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
        DCOLdATE.setCellValueFactory(new PropertyValueFactory<>("date"));
        dcolCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));*/
        getAllMostMovableItem();
        colMOrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colMItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colMQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        tblMostMovebleItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue !=null){
                    txtMOrderId.setText(newValue.getOid());
                    txtMItemCode.setText(newValue.getItemCode());
                    txtMQty.setText(String.valueOf(newValue.getQty()));
                    txtMDiscount.setText(String.valueOf(newValue.getDiscount()));
                    allItemDetails();
                }
        });

    }

    private void dailyInCome() {
        tblDaileIncomeTable.getItems().clear();
        LocalDate date = datePicker.getValue();
        try {
            ArrayList<OrderDto> allOrders = systemReportBo.getAllOrderByDaily(String.valueOf(date));
            for (OrderDto order : allOrders
            ) {
                tblDaileIncomeTable.getItems().add(new OrderDailyTm(
                        order.getOid(),
                        order.getDate(),
                        order.getCustomerId()
                ));

            }

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Check This Date"+date+" & Try Again").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void HomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        setUI("AdminstoratorForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) SystemReportContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        tblDaileIncomeTable.getItems().clear();
        dailyInCome();

    }
    public void getAllMostMovableItem(){
        try {
            ArrayList<OrderDetailsDto> arrayList = systemReportBo.mostMovableItem();
            for (OrderDetailsDto dto:arrayList
                 ) {
                tblMostMovebleItem.getItems().add(new LeastMovableMostMovbleTm(
                      dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getDiscount()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void allItemDetails(){
        try {
            ItemDto search = itemBo.search(txtMItemCode.getText());
            txtMDescription.setText(search.getDescription());
            txtMPackSize.setText(search.getPackSize());
            txtMQtyoHand.setText(String.valueOf(search.getQtyOnhand()));
            txtMUnitPrice.setText(String.valueOf(search.getUnitPrice()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void leastMovableItemOnAction(ActionEvent actionEvent) {
        tblMostMovebleItem.getItems().clear();
        lblTitle.setText("least Movable Item");
      try{
          ArrayList<OrderDetailsDto> arrayList = systemReportBo.leastMovableItem();
        for (OrderDetailsDto dto:arrayList
        ) {
            tblMostMovebleItem.getItems().add(new LeastMovableMostMovbleTm(
                    dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getDiscount()
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void MostMovableItemOnAction(ActionEvent actionEvent) {
        tblMostMovebleItem.getItems().clear();
        lblTitle.setText(" Most Movable Item");
        getAllMostMovableItem();
    }

    public void DailyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        generateReport(2);
    }
    private void generateReport(int num) throws SQLException, ClassNotFoundException {
        barChartForReports.getData().clear();
        double tot=0;
        int item=0;
        XYChart.Series totItems = new XYChart.Series<String,Number>();
        totItems.setName("items");

        XYChart.Series sales = new XYChart.Series<String,Number>();
        totItems.setName("sales");

        ArrayList<ReportDto> report =  orderBo.generateReport(num);
        if(report!=null){
            for (ReportDto dto : report) {
                totItems.getData().add(new XYChart.Data<String,Number>(dto.getItemCode(), dto.getTotalItemsSold()));
                sales.getData().add(new XYChart.Data<String,Number>(dto.getItemCode(), dto.getTotalEarned()));
                tot+=dto.getTotalEarned();
                item+=dto.getTotalItemsSold();
            }
        }
        lblTotal.setText(String.valueOf(tot));
        lblSales.setText(String.valueOf(item));
        barChartForReports.getData().addAll(totItems,sales);
    }
    public void MonthlyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        generateReport(1);
    }

    public void AnnualyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        generateReport(3);
    }
}
