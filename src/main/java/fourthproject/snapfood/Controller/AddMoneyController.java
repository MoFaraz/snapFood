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
        increaseBTN.setOnAction(event -> {
            if (isEmpty()) {
                customer.setInventory(Integer.parseInt(increaseFLD.getText()) + customer.getInventory());
                DBUtils.increaseInventory(customer.getInventory() , customer.getId());
                inventoryLBL.setText(String.valueOf(customer.getInventory()));
                increaseFLD.clear();
            }
        });

        backBTN.setOnAction(event -> {
            loadPersonPage(customer);
        });
    }

    private void loadPersonPage (Customer customer){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/PersonPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) backBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));

        PersonController controller = loader.getController();
        controller.initPage(customer);
        controller.setInventoryLBL(customer.getInventory());
    }

    private boolean isEmpty () {
        return (!increaseFLD.getText().isEmpty());
    }

    public void initPage (Customer customer) {
        this.customer = customer;
    }

    public void setInventoryLBL (int inventory) {
        inventoryLBL.setText(String.valueOf(inventory));
    }
}