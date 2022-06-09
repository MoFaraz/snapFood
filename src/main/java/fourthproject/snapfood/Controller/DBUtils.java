package fourthproject.snapfood.Controller;


import fourthproject.snapfood.Exception.PlaceNotFound;
import fourthproject.snapfood.Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;



public class DBUtils {


    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/snapFood";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";

    public static void signUp(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            psCheckUserExist = connection.prepareStatement("SELECT * FROM person WHERE email = ?");
            psCheckUserExist.setString(1, email);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                password = PasswordToHash.toHash(password);
                psInsert = connection.prepareStatement("INSERT INTO person (email,password) VALUES (?,?)");
                psInsert.setString(1, email);
                psInsert.setString(2, password);
                psInsert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psInsert, psCheckUserExist, resultSet);
        }
    }

    public static Customer logInUser(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT id,firstname,lastname,password,mobilePhone FROM person WHERE  email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();


            Customer customer;
            int id = 0;
            int inventory = 0;
            String firstname = null;
            String lastname = null;
            String mobilePhone = null;
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        id = resultSet.getInt("id");
                        firstname = resultSet.getString("firstname");
                        lastname = resultSet.getString("lastname");
                        mobilePhone = resultSet.getString("mobilePhone");
                    }
                }

                if (id == 0)
                    return null;

                preparedStatement = connection.prepareStatement("SELECT inventory FROM customer WHERE personId = ?");
                preparedStatement.setInt(1 , id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    inventory = resultSet.getInt("inventory");
                }

                customer = new Customer(id , firstname , lastname ,password , email , mobilePhone , inventory);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean findUser(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT password , email_id FROM data WHERE  username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Customer> getAllCustomer () {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        var customersId = new ArrayList<Customer>();

        var customers = new ArrayList<Customer>();

        Customer customer;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT personId,inventory FROM customer");
            resultSet = preparedStatement.executeQuery();

            int id;
            int personId;
            int inventory;
            String firstname;
            String lastname;
            String password;
            String email;
            String mobilePhone;

            while (resultSet.next()) {
                personId = resultSet.getInt("personId");
                inventory = resultSet.getInt("inventory");
                customer = new Customer(personId , inventory);
                customersId.add(customer);
            }

            preparedStatement = connection.prepareStatement("SELECT id , firstname , lastname , password , email , mobilePhone FROM person");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                for (Customer value : customersId) {
                    id = resultSet.getInt("id");
                    if (id == value.getPersonId()) {
                        firstname = resultSet.getString("firstname");
                        lastname = resultSet.getString("lastname");
                        password = resultSet.getString("password");
                        email = resultSet.getString("email");
                        mobilePhone = resultSet.getString("mobilePhone");

                        customer = new Customer(id, firstname, lastname, password, email, mobilePhone, value.getInventory());
                        customers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static ArrayList<Admin> getAllAdmin () {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        var adminId = new ArrayList<Admin>();

        var admins = new ArrayList<Admin>();

        Admin admin;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT personId,mainAdmin FROM admin");
            resultSet = preparedStatement.executeQuery();

            int id;
            int personId;
            int mainAdmin;
            String firstname;
            String lastname;
            String password;
            String email;
            String mobilePhone;

            while (resultSet.next()) {
                personId = resultSet.getInt("personId");
                mainAdmin = resultSet.getInt("mainAdmin");
                if (mainAdmin == 1)
                    admin = new Admin(personId , true);
                else
                    admin = new Admin(personId , false);

                adminId.add(admin);
            }

            preparedStatement = connection.prepareStatement("SELECT id , firstname , lastname , password , email , mobilePhone FROM person");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                for (Admin value : adminId) {
                    id = resultSet.getInt("id");
                    if (id == value.getPersonId()) {
                        firstname = resultSet.getString("firstname");
                        lastname = resultSet.getString("lastname");
                        password = resultSet.getString("password");
                        email = resultSet.getString("email");
                        mobilePhone = resultSet.getString("mobilePhone");

                        admin = new Admin(id,firstname,lastname,password,email,mobilePhone);
                        admins.add(admin);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public static ArrayList<Place> getAllPlace () {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        var places = new ArrayList<Place>();

        Place place;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT address FROM place");
            resultSet = preparedStatement.executeQuery();

            String address;
            while (resultSet.next()) {
                address = resultSet.getString("address");
                place = new Place(address);
                places.add(place);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;

    }

    public static void increaseInventory (int inventory , int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE customer SET inventory = ? WHERE personId = ?");
            preparedStatement.setInt(1, inventory);
            preparedStatement.setInt(2 , id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getUserID(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT ID FROM data WHERE  username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("UserNot Found");
            } else {
                while (resultSet.next()) {
                    return resultSet.getInt("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getUsername(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT username FROM data WHERE  ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("UserNot Found");
            } else {
                while (resultSet.next()) {
                    return resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static void insertToPlace (Place newPlace) {
        Connection connection = null;
        PreparedStatement psInsert;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            psInsert = connection.prepareStatement("INSERT INTO place (address) VALUES (?)");
            psInsert.setString(1, newPlace.getName());
            psInsert.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertToFoodCategory (String name , boolean type) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            psCheckUserExist = connection.prepareStatement("SELECT * FROM foodCategory WHERE name = ?");
            psCheckUserExist.setString(1, name);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                throw new PlaceNotFound("There Is Already A Food Category With This Name");
            } else {
            while (resultSet.next()) {
                psInsert = connection.prepareStatement("INSERT INTO foodCategory (name,type) VALUES (?,?)");
                psInsert.setString(1, name);
                if (type) //RESTAURANT
                    psInsert.setInt(2, 1);
                else //CAFE
                    psInsert.setInt(2 , 2);
                psInsert.executeUpdate();
                }
            }
        } catch (SQLException | PlaceNotFound e) {
            e.printStackTrace();
        } finally {
            close(psInsert, psCheckUserExist, resultSet);
        }
    }

    public static void insertToItem (Place newPlace) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            psCheckUserExist = connection.prepareStatement("SELECT * FROM foodCategory WHERE name = ?");
            psCheckUserExist.setString(1, newPlace.getFoodCategory().getName());
            resultSet = psCheckUserExist.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new PlaceNotFound("There Is No Food Category With This Name");
            } else {
                while (resultSet.next()) {
                    int foodCategoryId;
                    foodCategoryId = resultSet.getInt("id");
                    psInsert = connection.prepareStatement("INSERT INTO item (name,price,foodCategoryId) VALUES (?,?,?)");
                    for (int i = 0; i < newPlace.getFoodCategory().getItems().size(); i++) {
                        psInsert.setString(1, newPlace.getFoodCategory().getItems().get(i).getName());
                        psInsert.setString(2, newPlace.getFoodCategory().getItems().get(i).getPrice());
                        psInsert.setInt(3, foodCategoryId);
                        psInsert.executeUpdate();
                    }
                }

            }

        } catch (SQLException | PlaceNotFound e) {
            e.printStackTrace();
        } finally {
            close(psInsert, psCheckUserExist, resultSet);
        }
    }

    private static void close(PreparedStatement psInsert, PreparedStatement psCheckUserExist, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (psCheckUserExist != null) {
            try {
                psCheckUserExist.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}