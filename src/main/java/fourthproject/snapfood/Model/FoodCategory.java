package fourthproject.snapfood.Model;

import java.util.ArrayList;

public class FoodCategory {

    private String name;
    private String id;
    private ArrayList<Item> items;
    private foodCategory foodCategory;

    public enum foodCategory{
      RESTURANT,
      CAFE
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public FoodCategory.foodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory.foodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}
