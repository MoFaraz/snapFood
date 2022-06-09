package fourthproject.snapfood;

import fourthproject.snapfood.Controller.AdminController;
import fourthproject.snapfood.Controller.DBUtils;
import fourthproject.snapfood.Controller.PasswordToHash;
import fourthproject.snapfood.Model.Admin;
import fourthproject.snapfood.Model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/LoginPage.fxml"));

        loader.load();

        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}