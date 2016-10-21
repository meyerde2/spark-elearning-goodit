package app.user;

/**
 * Created by Dennis on 29.09.2016.
 */


public class User {

    int id;
    String username;
    String firstname;
    String lastname;
    String salt;
    String hashedPassword;
    int role;
    int openGameId;


    public User(int id, String username, String salt, String hashedPassword) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.firstname = null;
        this.lastname = null;
        this.role = 0;
        this.openGameId = 0;
    }

    public User(int id, String username, String firstname, String lastname, String salt, String hashedPassword,  int role, int openGameId){
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.openGameId = openGameId;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSalt() {
        return salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getRole() {
        return role;
    }

    public int getOpenGameId() {
        return openGameId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
