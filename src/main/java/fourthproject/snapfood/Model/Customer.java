package fourthproject.snapfood.Model;

public class Customer extends Person{

    private int inventory;


    public Customer(int id, String name, String lastname, String password,
                    String email, String mobilePhone,
                    int inventory) {
        setId(id);
        setName(name);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setInventory(inventory);
    }

    public Customer (int personId , int inventory) {
        setPersonId(personId);
        setInventory(inventory);
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
