package hailt.config.mysql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author tanhai - 2017/05/01
 *
 */
public class Database {
  private static final String PORT_TAG = "port:";
  private static final String DATABASE_TAG = "database:";
  private static final String USERNAME_TAG = "username:";
  private static final String ADDRESS_TAG = "address:";
  private static final String PASSWORD_TAG = "password:";
  private String address, database, username, password;
  private int port;
  private static Database instance;
  private Connection connection;

  protected Database() {
    prepareConnection();
    reload();
  }

  public boolean reload() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://" + getAddress() + ":" + getPort() + "/" + getDatabase();
      connection = DriverManager.getConnection(url, getUsername(), getPassword());
      return true;
    } catch (Exception e) {
      System.out.println("Could not connect: " + e.getMessage());
      return false;
    }
  }

  public Statement getStatement() {
    try {
      return connection.createStatement();
    } catch (SQLException e) {
      return null;
    }
  }

  public Connection getConnection() {
    return connection;
  }

  public static Database getInstance() {
    if (instance != null) {
      return instance;
    }

    synchronized (Database.class) {
      if (instance != null) {
        return instance;
      }
      return instance = new Database();
    }
  }

  public String configDatabaseFile() {
    return "database.conf";
  }

  private void prepareConnection() {
    FileReader fr = null;
    try {
      String content = "";
      fr = new FileReader(configDatabaseFile());
      BufferedReader br = new BufferedReader(fr);
      String s;
      while ((s = br.readLine()) != null) {
        content += s + ";";
      }
      this.address = content.substring(content.indexOf(ADDRESS_TAG) + ADDRESS_TAG.length(),
          content.indexOf(";", content.indexOf(ADDRESS_TAG))).trim();
      this.password = content.substring(content.indexOf(PASSWORD_TAG) + PASSWORD_TAG.length(),
          content.indexOf(";", content.indexOf(PASSWORD_TAG))).trim();
      this.username = content.substring(content.indexOf(USERNAME_TAG) + USERNAME_TAG.length(),
          content.indexOf(";", content.indexOf(USERNAME_TAG))).trim();
      this.port = Integer.parseInt(content
          .substring(content.indexOf(PORT_TAG) + PORT_TAG.length(), content.indexOf(";", content.indexOf(PORT_TAG)))
          .trim());
      this.database = content.substring(content.indexOf(DATABASE_TAG) + DATABASE_TAG.length(),
          content.indexOf(";", content.indexOf(DATABASE_TAG))).trim();
      fr.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Ensure that database.conf located in your app"
          + " or you need to extend Database class and define configDatabaseFile() method");
    }
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
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

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }
}
