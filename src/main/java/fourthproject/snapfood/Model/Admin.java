package fourthproject.snapfood.Model;

public class Admin extends Person{

    private boolean mainAdmin;

    public Admin(int id , String name, String lastname, String email, String password, String mobilePhone) {
        setId(id);
        setName(name);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setMobilePhone(mobilePhone);
    }

    public Admin (int personId , boolean mainAdmin){
        setPersonId(personId);
        setMainAdmin(mainAdmin);
    }


    public boolean getMainAdmin() {
        return mainAdmin;
    }

    public void setMainAdmin(boolean mainAdmin) {
        this.mainAdmin = mainAdmin;
    }
}
