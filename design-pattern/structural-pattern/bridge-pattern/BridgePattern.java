interface Shape {
  void draw();
}

interface Color {
  String fill();
}

class Circle implements Shape {

  private Color color;

  public Circle(Color color) {
    this.color = color;
  }

  @Override
  public void draw() {
    System.out.print("Drawing Circle with " + color.fill());
  }
}

class Square implements Shape {

  private Color color;

  public Square(Color color) {
    this.color = color;
  }

  @Override
  public void draw() {
    System.out.print("Drawing Square with " + color.fill());
  }
}

class Red implements Color {

  @Override
  public String fill() {
    return "Red Color";
  }
}

class Green implements Color {

  @Override
  public String fill() {
    return "Green Color";
  }
}

public class BridgePattern {

  public static void main(String... args) {

    Color red = new Red();
    Color green = new Green();

    Shape redCircle = new Circle(red);
    Shape greenCircle = new Circle(green);
    Shape redSquare = new Square(red);
    Shape greenSquare = new Square(green);

    redCircle.draw();
    System.out.println();
    greenCircle.draw();
    System.out.println();
    redSquare.draw();
    System.out.println();
    greenSquare.draw();
  }
}