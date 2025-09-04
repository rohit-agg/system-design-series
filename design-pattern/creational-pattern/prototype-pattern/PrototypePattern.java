interface Proto {
  Proto clone();
  String getName();
}

class Prototype implements Proto {

  private String name;

  public Prototype(String name) {
    this.name = name;
  }

  @Override
  public Proto clone() {
    return new Prototype(this.name);
  }

  @Override
  public String getName() {
    return this.name;
  }
}

public class PrototypePattern {  

  public static void main(String... args) {

    Proto obj1 = new Prototype("System Design Series");
    Proto obj2 = obj1.clone();

    System.out.println("Prototype Name: " + obj2.getName());
  }
}