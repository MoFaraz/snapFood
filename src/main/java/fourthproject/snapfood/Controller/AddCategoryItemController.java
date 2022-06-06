package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.FoodCategory;
import fourthproject.snapfood.Model.Item;
import fourthproject.snapfood.Model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategoryItemController {

          private Stage addCategoryItemStage;
          private ArrayList<Item> items = new ArrayList<>();

          private Place newPlace;
    @FXML private Button addItemBTN;
    @FXML private Button backBTN;
    @FXML private TextField itemNameFLD;
    @FXML private TextField itemPriceFLD;

    @FXML void addItemAction(ActionEvent event) {
        var item = new Item();

        item.setName(itemNameFLD.getText());
        item.setPrice(itemPriceFLD.getText());

        items.add(item);
    }

    @FXML void backAction(ActionEvent event) {
        /*newPlace.getFoodCategory().setItems(items);
        DBUtils.insertToPlace(newPlace);
        DBUtils.insertToFoodCategory(newPlace);
        DBUtils.insertToItem(newPlace);*/

        getAddCategoryItemStage().close();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/AdminPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage adminStage = new Stage();
        adminStage.setScene(new Scene(loader.getRoot()));
        AdminController controller = loader.getController();
        controller.setAdminStage(adminStage);
        adminStage.show();
    }

    public Stage getAddCategoryItemStage() {
        return addCategoryItemStage;
    }

    public void setAddCategoryItemStage(Stage addCategoryItemStage) {
        this.addCategoryItemStage = addCategoryItemStage;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setNewPlace(Place newPlace) {
        this.newPlace = newPlace;
    }
}
