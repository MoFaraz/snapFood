package fourthproject.snapfood;

import fourthproject.snapfood.Controller.AdminController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/AdminPage.fxml"));

        loader.load();

        AdminController controller = loader.getController();
        controller.setAdminStage(stage);

        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}