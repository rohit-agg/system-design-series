class Connection {

  private static Connection instance;

  private Connection() {
    System.out.println("Connection established");
  }

  public static Connection getInstance() {
    
    if (instance == null) {
      synchronized (Connection.class) { 
        if (instance == null) {
          instance = new Connection();
        }
      }
    }
    
    return instance;
  }
}

public class SingletonPattern {

  public static void main(String... args) {

    Connection connection1 = Connection.getInstance();
    Connection connection2 = Connection.getInstance();
    Connection connection3 = Connection.getInstance();
    Connection connection4 = Connection.getInstance();
    Connection connection5 = Connection.getInstance();
  }
}