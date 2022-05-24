package controller;

import TM.ItemTm;
import bo.custom.ItemBo;
import bo.custom.bo.BOFactory;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXTextField;
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
import dto.ItemDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemController {
    public AnchorPane itemContext;

    public JFXTextField tstSaveItemCode1;
    public JFXTextField txtSavePackSize1;
    public JFXTextField txtSaveItemUnitPrice1;
    public JFXTextField txtSaveItemDescription1;
    public JFXTextField txtSaveItemQtyOnHand1;
    public JFXTextField txtSaveItemDiscount;

    public JFXTextField txtUpdateItemCode;
    public JFXTextField txtUpdateItemPackSize;
    public JFXTextField txtUpdateUnitiPrice;
    public JFXTextField txtUpdateItemDescription;
    public JFXTextField txtUpdateQtyONHand;
    public JFXTextField txtUpdateItemDiscount;
    public TextField txtSearchItem;

    public JFXTextField tstDeletedCode;
    public JFXTextField txtDeleteItemUnitPrice;
    public JFXTextField txtDeletePackSize;
    public JFXTextField txtDeletetemDescription;
    public JFXTextField txtDeletetemQtyOnHand;
    public JFXTextField txtDeletetemDisount;

    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQtyOnHand;
    public TableColumn colUNITPRICE;
    public TableColumn colDiscount;


    ItemBo itemBo= (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    public void initialize(){
        getAllItem();

    colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
    colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnhand"));
    colUNITPRICE.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
}

    private void getAllItem(){
        tblItem.getItems().clear();
        try {
            ArrayList<ItemDto> allItem= itemBo.getAllItem();
            for(ItemDto dto : allItem){
                tblItem.getItems().add(new ItemTm(dto.getCode(),dto.getDescription(),
                        dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getDiscount()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void ItemSaveOnAction(ActionEvent actionEvent){
      /*  ItemDto Dto=new ItemDto(
              String code=  tstSaveItemCode.getText(),
              String description=   txtSaveItemDescription.getText(),
              String packSize=   txtSavePackSize.getText(),
               Integer.parseInt( txtSaveItemQtyOnHand.getText()),
               Double.parseDouble(txtSaveItemUnitPrice.getText())
        );
        try {
            CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?)",Dto.getCode(),Dto.getDescription(),Dto.getPackSize(),Dto.getQtyOnhand(),Dto.getUnitPrice());
            new Alert(Alert.AlertType.CONFIRMATION,"Item Is Saved").show();
            tstSaveItemCode.clear();
            txtSavePackSize.clear();
            txtSaveItemUnitPrice.clear();
            txtSaveItemDescription.clear();
            txtSaveItemQtyOnHand.clear();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Something Went Wrong Please Try Again").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        String code=  tstSaveItemCode1.getText();
        String description=   txtSaveItemDescription1.getText();
        String packSize=   txtSavePackSize1.getText();
        int qtyonhandd=   Integer.parseInt( txtSaveItemQtyOnHand1.getText());
        double unitPrice=   Double.parseDouble(txtSaveItemUnitPrice1.getText());
        double discount=Double.parseDouble(txtSaveItemDiscount.getText());
        try {
            boolean b=itemBo.saveItem(new ItemDto(code,description,packSize,qtyonhandd,unitPrice,discount));
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Is Saved").show();
                tstSaveItemCode1.clear();
                txtSavePackSize1.clear();
                txtSaveItemUnitPrice1.clear();
                txtSaveItemDescription1.clear();
                txtSaveItemQtyOnHand1.clear();
                getAllItem();
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING,"Something Went Wrong Please Try Again").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        /*ItemDto dto=new ItemDto(
                txtUpdateItemCode.getText(),
                txtUpdateItemDescription.getText(),
                txtUpdateItemPackSize.getText(),
                Integer.parseInt( txtUpdateQtyONHand.getText()),
                Double.parseDouble(txtUpdateUnitiPrice.getText())
        );
        try {
            if(CrudUtil.executeUpdate("UPDATE  item SET description=? , PackSize=? , qtyOnHand=? , unitPrice=? WHERE code=?",
                  dto.getDescription(),dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getCode()));
            {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
            }

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
               String Code= txtUpdateItemCode.getText();
               String description = txtUpdateItemDescription.getText();
               String pacSize= txtUpdateItemPackSize.getText();
               int qtyonHand=  Integer.parseInt( txtUpdateQtyONHand.getText());
               double unitprice=  Double.parseDouble(txtUpdateUnitiPrice.getText());
               double discount=Double.parseDouble(txtUpdateItemDiscount.getText());
        try {
           boolean b= itemBo.updateItem(new ItemDto(Code,description,pacSize,qtyonHand,unitprice,discount));
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
                getAllItem();
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearchItemOnAction(ActionEvent actionEvent) {
       /* boolean b= itemBo.existItem(txtSearchItem.getText());

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item WHERE code=?", txtSearchItem.getText());
          if(rst.next() && !b){
              txtUpdateItemCode.setText(rst.getString(1));
              txtUpdateItemDescription.setText(rst.getString(2));
              txtUpdateItemPackSize.setText(rst.getString(3));
              txtUpdateQtyONHand.setText(rst.getString(4));
              txtUpdateUnitiPrice.setText(rst.getString(5));
        }
          else{
              new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Code " +txtSearchItem.getText()).show();
          }*/

        ItemDto search = null;

        try {
                search = itemBo.search(txtSearchItem.getText());
                txtUpdateItemCode.setText(search.getCode());
                txtUpdateUnitiPrice.setText(String.valueOf(search.getUnitPrice()));
                txtUpdateItemPackSize.setText(search.getPackSize());
                txtUpdateItemDescription.setText((search.getDescription()));
                txtUpdateQtyONHand.setText(String.valueOf(search.getQtyOnhand()));
                txtUpdateItemDiscount.setText(String.valueOf(search.getDiscount()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released_Meals(KeyEvent keyEvent) {
    }

    public void HomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        setUI("AdminstoratorForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) itemContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.setTitle("AdminForm");
    }

    public void ItemDeleteOnAction(ActionEvent actionEvent) {
     /*   try {
            if(CrudUtil.executeUpdate("DELETE FROM item WHERE code=?",tstDeletedCode.getText()));  {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted").show();
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Plrase Try Again").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
           boolean b= itemBo.deleteItem(tstDeletedCode.getText());
           if(b){
               new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted").show();
               getAllItem();
           }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Plrase Try Again").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteSOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       /* ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item WHERE code=?", tstDeletedCode.getText());
        if(rst.next()){
            txtDeletetemDescription.setText(rst.getString(2));
            txtDeletePackSize.setText(rst.getString(3));
            txtDeletetemQtyOnHand.setText(rst.getString(4));
            txtDeleteItemUnitPrice.setText(rst.getString(5));
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Code").show();
        }*/

        try {
             ItemDto search = itemBo.search(tstDeletedCode.getText());
                txtDeleteItemUnitPrice.setText(String.valueOf(search.getUnitPrice()));
                txtDeletePackSize.setText(search.getPackSize());
                txtDeletetemDescription.setText(search.getDescription());
                txtDeletetemQtyOnHand.setText(String.valueOf(search.getQtyOnhand()));
                txtDeletetemDisount.setText(String.valueOf(search.getDiscount()));

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong Please Check Code "+tstDeletedCode.getText()).show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    boolean existItem(String id) throws SQLException, ClassNotFoundException {
     return   itemBo.existItem(id);
    }
}
