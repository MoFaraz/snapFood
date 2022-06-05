package fourthproject.snapfood.Model;

public class FoodCategory {

    private String name;
    private String id;

    enum foodCategory{
      RESTURANT,
      CAFE
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


}
