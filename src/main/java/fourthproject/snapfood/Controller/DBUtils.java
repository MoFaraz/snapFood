package fourthproject.snapfood.Controller;


import fourthproject.snapfood.Exception.PlaceNotFound;
import fourthproject.snapfood.Model.Customer;
import fourthproject.snapfood.Model.FoodCategory;
import fourthproject.snapfood.Model.Person;
import fourthproject.snapfood.Model.Place;
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

    public static boolean logInUser(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT password FROM person WHERE  email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(PasswordToHash.toHash(password))) {
                        PreparedStatement update = connection.prepareStatement("UPDATE person SET status = ? WHERE email = ?");
                        update.setInt(1, 1);
                        update.setString(2, email);
                        update.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void logout() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement update = connection.prepareStatement("UPDATE person SET status = ? WHERE ID = ?");
            update.setInt(1, 0);
            update.setInt(2, getOnlineID());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static int getOnlineID() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT ID FROM data WHERE  status = 1");
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

            psInsert = connection.prepareStatement("INSERT INTO place (name,address,type) VALUES (?,?,?)");
            psInsert.setString(1, newPlace.getName());
            psInsert.setString(2, newPlace.getAddress());
            if (newPlace.getFoodCategory().getFoodCategory() == FoodCategory.foodCategory.RESTURANT)
                psInsert.setInt(3,1);
            else
                psInsert.setInt(3 , 2);
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

    public static void insertToFoodCategory (Place newPlace) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            psCheckUserExist = connection.prepareStatement("SELECT * FROM place WHERE name = ?");
            psCheckUserExist.setString(1, newPlace.getName());
            resultSet = psCheckUserExist.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new PlaceNotFound("There Is No Place With This Name");
            } else {
                int placeId;
                placeId = resultSet.getInt("id");
                psInsert = connection.prepareStatement("INSERT INTO foodCategory (name,placeId) VALUES (?,?)");
                psInsert.setString(1, newPlace.getFoodCategory().getName());
                psInsert.setInt(2, placeId);
                psInsert.executeUpdate();
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
                int foodCategoryId;
                foodCategoryId = resultSet.getInt("id");
                psInsert = connection.prepareStatement("INSERT INTO item (name,price,foodCategoryId) VALUES (?,?,?)");
                for (int i = 0 ; i < newPlace.getFoodCategory().getItems().size() ; i++){
                    psInsert.setString(1 , newPlace.getFoodCategory().getItems().get(i).getName());
                    psInsert.setString(2 , newPlace.getFoodCategory().getItems().get(i).getPrice());
                    psInsert.setInt(3 , foodCategoryId);
                    psInsert.executeUpdate();
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