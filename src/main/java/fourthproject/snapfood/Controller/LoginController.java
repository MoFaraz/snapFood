package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{

          private Stage loginPage;
    @FXML private CheckBox checkBox;
    @FXML private Label loginLBL;
    @FXML private Label passwordLBL;
    @FXML private PasswordField passwordTXT;
    @FXML private Button signInBTN;
    @FXML private Label userLBL;
    @FXML private TextField userTXT;

    @FXML void forgetPassAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/ForgetPass.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLoginPage().setScene(new Scene(loader.getRoot()));
        getLoginPage().setResizable(false);
       /* ForgetPassController controller = loader.getController();
        controller.initFunction6(loginPage);*/
    }

    @FXML
    void loginAction(ActionEvent event) {
        if (DBUtils.logInUser(event,userTXT.getText(),passwordTXT.getText())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            getLoginPage().setScene(new Scene(loader.getRoot()));
            getLoginPage().setResizable(false);
        }

    }

    @FXML
    void newUserAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/SignUpPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLoginPage().setScene(new Scene(loader.getRoot()));
        getLoginPage().setResizable(false);
        SignUpController signUpController = loader.getController();
        signUpController.initFunction2(loginPage);
    }

    public void initFunction (Stage loginPage) {
        this.loginPage = loginPage;
    }

    public Stage getLoginPage() {
        return loginPage;
    }
}
