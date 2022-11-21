package com.javafx.pos.controller;

import com.javafx.pos.SingletonDatabase;
import com.javafx.pos.pojo.Product;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PosController implements Initializable {

    @FXML
    public TextField searchField;
    @FXML
    public TableColumn<Product,String> priceColumn;
    @FXML
    public TableColumn<Product,String> productColumn;
    @FXML
    public TableView<Product> productPriceTableView;

    @FXML
    public TextField productField;
    @FXML
    public ListView receipt;
    @FXML
    public TextField nameField, quantityField;

    private ObservableList<Product> PRODUCT_LIST;
    private ObservableList<Product> ITEM_LIST;

    private static Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PRODUCT_LIST = FXCollections.observableArrayList();
       ITEM_LIST = FXCollections.observableArrayList();
       loadProductsList();
       productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
       priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
       productPriceTableView.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> showProduct(newValue));
       productPriceTableView.setItems(PRODUCT_LIST);
    }



    private void loadProductsList()
    {
        if ( !PRODUCT_LIST.isEmpty())
            PRODUCT_LIST.clear();

        PRODUCT_LIST.addAll(getProducts());

    }
    private ObservableList<Product> getProducts()
    {
        ObservableList<Product> list = FXCollections.observableArrayList();
        session = SingletonDatabase.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from products",Product.class);
        List<Product> products = query.list();
        tx.commit();
        products.stream().forEach(list::add);
        return list;
    }
    private void showProduct(Product product)
    {
        if (product != null)
        {

        }
    }
    public void onPaymentAction(ActionEvent actionEvent) {

    }
}
