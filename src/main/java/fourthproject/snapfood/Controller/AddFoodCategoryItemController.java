package fourthproject.snapfood.Controller;

import fourthproject.snapfood.Main;
import fourthproject.snapfood.Model.FoodCategory;
import fourthproject.snapfood.Model.Item;
import fourthproject.snapfood.Model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCategoryItemController implements Initializable {

          private ArrayList<Item> items = new ArrayList<>();
          private Place newPlace;
    @FXML private Button addItemBTN;
    @FXML private Button backBTN;
    @FXML private TextField itemNameFLD;
    @FXML private TextField itemPriceFLD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addItemBTN.setOnAction(event -> {
            addItem();
        });

        backBTN.setOnAction(event -> {
            insertToDB(newPlace);
            loadAdminPage();
        });
    }

    private void loadAdminPage () {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/AdminPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) addItemBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
    }

    private void addItem () {
        var item = new Item();

        item.setName(itemNameFLD.getText());
        item.setPrice(itemPriceFLD.getText());

        items.add(item);

        itemNameFLD.clear();
        itemPriceFLD.clear();
    }

    private void insertToDB (Place newPlace) {
        newPlace.getFoodCategory().setItems(items);
        DBUtils.insertToPlace(newPlace);
        /*DBUtils.insertToFoodCategory(newPlace);*/
        DBUtils.insertToItem(newPlace);
    }

    public void initPage(Place newPlace) {
        this.newPlace = newPlace;
    }

}
