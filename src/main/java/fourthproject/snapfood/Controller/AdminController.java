package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.FoodCategory;
import fourthproject.snapfood.Model.Place;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML private Button addBTN;
    @FXML private Button addCategoryItemBTN;
    @FXML private TextField addressFLD;
    @FXML private Button backBTN;
    @FXML private CheckBox cafeCheckBox;
    @FXML private TextField categoryNameFLD;
    @FXML private CheckBox restaurantCheckBox;
    @FXML private TextField placeFLD;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCategoryItemBTN.setOnAction(event -> {
            if (!isFieldEmpty(categoryNameFLD) &&
                    !isFieldEmpty(placeFLD) )
           /*((resturanCheckBox.isSelected() && cafeCheckBox.isSelected()) ||
           (!resturanCheckBox.isSelected() && !cafeCheckBox.isSelected())))*/{

               /* var newPlace = new Place();
                var newFoodCategory = new FoodCategory();

                newPlace.setName(placeFLD.getText());
                newPlace.setAddress(addressFLD.getText());

                newFoodCategory.setName(categoryNameFLD.getText());
                if (restaurantCheckBox.isSelected())
                    newFoodCategory.setFoodCategory(FoodCategory.type.RESTURANT);
                else
                    newFoodCategory.setFoodCategory(FoodCategory.type.CAFE);

                newPlace.setFoodCategory(newFoodCategory);
                try {
                    loadAddCategoryItemStage(newPlace);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        backBTN.setOnAction(event -> {
                loadLoginPage();
        });
    }

    private void loadAddCategoryItemStage (Place newPlace) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/AddCategoryItemPage.fxml"));
        loader.load();

        Stage stage = (Stage) addCategoryItemBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);

        AddFoodCategoryItemController controller = loader.getController();
        controller.initPage(newPlace);
    }

    private void loadLoginPage (){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/LoginPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) addCategoryItemBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
    }

    private boolean isFieldEmpty (TextField textField) {
        return textField.getText().isEmpty();
    }

}
