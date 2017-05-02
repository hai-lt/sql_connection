package hailt.models;

public class UserRecord {
  public static final String[] ATTRIBUTES = { "id", "username", "password" };
  private int id;
  private String username, password;
  
  public UserRecord(String...strings) {
    id = Integer.parseInt(strings[0]);
    username = strings[1];
    password = strings[2];
  }
  
  public UserRecord(ObjectRecord object) {
    id = Integer.parseInt(object.getValues()[0]);
    username = object.getValues()[1];
    password = object.getValues()[2];
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
