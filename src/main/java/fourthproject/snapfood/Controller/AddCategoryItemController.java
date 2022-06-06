package fourthproject.snapfood.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryItemController {

          private Stage addCategoryItemStage;
    @FXML private Button addItemBTN;
    @FXML private Button backBTN;
    @FXML private TextField itemNameFLD;
    @FXML private TextField itemPriceFLD;

    @FXML void addItemAction(ActionEvent event) {

    }

    @FXML void backAction(ActionEvent event) {

    }

    public Stage getAddCategoryItemStage() {
        return addCategoryItemStage;
    }

    public void setAddCategoryItemStage(Stage addCategoryItemStage) {
        this.addCategoryItemStage = addCategoryItemStage;
    }
}
