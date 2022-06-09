package fourthproject.snapfood.Model;

import java.util.ArrayList;

public class FoodCategory {

    private String name;
    private String id;
    private ArrayList<Item> items;
    private type foodCategory;

    public enum type {
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

    public type getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(type foodCategory) {
        this.foodCategory = foodCategory;
    }
}
