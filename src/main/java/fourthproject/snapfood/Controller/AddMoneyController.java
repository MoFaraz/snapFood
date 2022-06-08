package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMoneyController implements Initializable {

          private Customer customer;
    @FXML private Button backBTN;
    @FXML private Button increaseBTN;
    @FXML private TextField increaseFLD;
    @FXML private Label inventoryLBL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backBTN.setOnAction(event -> {
            loadPersonPage();
        });

        inventoryLBL.setText(String.valueOf(customer.getInventory()));

        increaseBTN.setOnAction(event -> {
            if (isEmpty()) {
                customer.setInventory(Integer.parseInt(increaseFLD.getText()) + customer.getInventory());
                inventoryLBL.setText(String.valueOf(customer.getInventory()));
            }
        });
    }

    private void loadPersonPage (){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/PersonPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) backBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
    }

    private boolean isEmpty () {
        return (!increaseFLD.getText().isEmpty());
    }

    public void initPage (Customer customer) {
        this.customer = customer;
    }
}