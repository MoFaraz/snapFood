package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

          private Customer customer;
    @FXML private CheckBox checkBox;
    @FXML private PasswordField passFLD;
    @FXML private Button signInBTN;
    @FXML private TextField userFLD;
    @FXML private Hyperlink newUserHLink;
    @FXML private Hyperlink forgetPassHLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newUserHLink.setOnAction(event -> {
            loadSignUpPage();
        });

        signInBTN.setOnAction(event -> {
            customer = login(event , userFLD , passFLD);
            if (customer != null)
                loadPersonPage(customer);
        });

        forgetPassHLink.setOnAction(event -> {
            loadForgetPassPage();
        });
    }



    private void loadSignUpPage (){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/SignUpPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) signInBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
    }

    private void loadPersonPage (Customer customer) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/PersonPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) signInBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));

        PersonController controller = loader.getController();
        controller.initPage(customer);
        controller.setInventoryLBL(customer.getInventory());
    }

    private void loadForgetPassPage () {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ForgetPassPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) forgetPassHLink.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
    }

    private Customer login (ActionEvent event , TextField username , TextField password) {
       return DBUtils.logInUser(event,username.getText(),password.getText());
    }

}
