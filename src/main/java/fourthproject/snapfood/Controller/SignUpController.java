package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    private Stage signUpStage;
    @FXML
    private Button backBTN;
    @FXML
    private PasswordField conPassFLd;
    @FXML
    private PasswordField emailFLD;
    @FXML
    private TextField passFLD;
    @FXML
    private Button signUpBTN;


    @FXML
    void backBTNAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/LoginPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getSignUpStage().setScene(new Scene(loader.getRoot()));
        getSignUpStage().setResizable(false);
        LoginController loginController = loader.getController();
        loginController.initFunction(signUpStage);
    }

    @FXML
    void signUpAction(ActionEvent event) {
        if (RegEx.emailRegEx(emailFLD.getText()) &&
                RegEx.passwordRegEx(passFLD.getText()) &&
                RegEx.passwordRegEx(conPassFLd.getText()) &&
                passFLD.getText().equals(passFLD.getText())) {
            emailFLD.setStyle("-fx-border-color: green");
            passFLD.setStyle("-fx-border-color: green");
            conPassFLd.setStyle("-fx-border-color: green");
            DBUtils.signUp(event, emailFLD.getText(), passFLD.getText());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/VerificationPage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            getSignUpStage().setScene(new Scene(loader.getRoot()));
            getSignUpStage().setResizable(false);

        }
    }

    public void initFunction2(Stage signUpStage) {
        this.signUpStage = signUpStage;
    }

    public Stage getSignUpStage() {
        return signUpStage;
    }
}
