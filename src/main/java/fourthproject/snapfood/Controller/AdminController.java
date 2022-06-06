package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.FoodCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminController {

    private Stage adminStage;

    @FXML private Button addBTN;
    @FXML private Button addCategoryItemBTN;
    @FXML private TextField addressFLD;
    @FXML private Button backBTN;
    @FXML private CheckBox cafeCheckBox;
    @FXML private TextField categoryNameFLD;
    @FXML private CheckBox resturanCheckBox;

    @FXML void addAction(ActionEvent event) {

    }

    @FXML
    void addCategoryItemAction(ActionEvent event) {
        if (!categoryNameFLD.getText().isEmpty() &&
           ((resturanCheckBox.isSelected() && cafeCheckBox.isSelected()) ||
           (!resturanCheckBox.isSelected() && !cafeCheckBox.isSelected()))){
            var foodCategories = new ArrayList<FoodCategory>();
            var newFoodCategory = new FoodCategory();
            newFoodCategory.setName(categoryNameFLD.getText());
            if (resturanCheckBox.isSelected())
                newFoodCategory.setFoodCategory(FoodCategory.foodCategory.RESTURANT);
            else
                newFoodCategory.setFoodCategory(FoodCategory.foodCategory.CAFE);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/Request.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getAdminStage().setScene(new Scene(loader.getRoot()));
            getAdminStage().setResizable(false);
            AddCategoryItemController controller = loader.getController();
            controller.setAddCategoryItemStage(adminStage);

        }
    }

    @FXML
    void backAction(ActionEvent event) {

    }

    public Stage getAdminStage() {
        return adminStage;
    }

    public void setAdminStage (Stage adminStage) {
        this.adminStage = adminStage;
    }
}
