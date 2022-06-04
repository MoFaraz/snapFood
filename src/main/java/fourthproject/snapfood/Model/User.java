package fourthproject.snapfood.Model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String emailId;
    private String status;
    private String request;
    private String block;
    private String friends;
    private int arrayID;

    public User(String username, String password, String emailId, String status, String request, String block, String friends, int arrayID) {
        setUsername(username);
        setPassword(password);
        setEmailId(emailId);
        setStatus(status);
        setRequest(request);
        setBlock(block);
        setFriends(friends);
        setArrayID(arrayID);
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public int getArrayID() {
        return arrayID;
    }

    public String getStatus() {
        return status;
    }

    public String getRequest() {
        return request;
    }

    public String getBlock() {
        return block;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setArrayID(int arrayID) {
        this.arrayID = arrayID;
    }

    public void addFriendsUser (String friendsID , ArrayList<User> allUser) {
        for (User user : allUser)
            if (user.getStatus().equals("1"))
                user.setFriends(user.getFriends() + friendsID);
    }

}
