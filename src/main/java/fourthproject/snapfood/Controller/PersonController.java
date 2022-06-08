package fourthproject.snapfood.Controller;
import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.Customer;
import fourthproject.snapfood.Model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonController implements Initializable {

          private Customer customer;
          private ArrayList<Customer> customers = DBUtils.getAllCustomer();
          private ImageView imageView;
    @FXML private Button addMoneyBTN;
    @FXML private Button friendsRequestBTN;
    @FXML private Button logoutBTN;
    @FXML private VBox personVbox;
    @FXML private HBox searchFLD;
    @FXML private Label inventoryLBL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inventoryLBL.setText(String.valueOf(customer.getInventory()));

        addMoneyBTN.setOnAction(event -> {
            loadAddMoneyPage(customer);
        });



        friendsRequestBTN.setOnAction(event -> {

            if (customers != null)
                for (Customer newCustomer : customers) {
                    if (newCustomer == customer)
                        continue;
                    imageView = new ImageView();
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    Button userBTN = new Button(newCustomer.getName());
                    userBTN.setStyle("-fx-background-color:  #636566;");
                    hBox.setMargin(userBTN, new Insets(5, 0, 0, 6));
                    File file = new File("src/main/resources/fourthproject/snapfood/image/person.png");
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(38);
                    imageView.setFitHeight(37);
                    hBox.setMargin(imageView, new Insets(8, 0, 0, 5));


                    hBox.getChildren().addAll(imageView, userBTN);
                    personVbox.getChildren().add(hBox);
                }
        });


        logoutBTN.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/LoginPage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) logoutBTN.getScene().getWindow();
            stage.setScene(new Scene(loader.getRoot()));
            stage.setResizable(false);
        });


    }

    private void loadAddMoneyPage (Customer customer) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/AddMoneyPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) addMoneyBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));

        AddMoneyController controller = loader.getController();
        controller.initPage(customer);
    }

    public void initPage (Customer customer) {
        this.customer = customer;
    }
}
