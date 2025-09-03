import java.util.Scanner;

interface Shape {
  void area();
}

class Circle implements Shape {
  
  @Override
  public void area() {
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the radius: ");
    int radius = scanner.nextInt();
    System.out.println();

    float area = 3.14f * radius * radius;
    System.out.println("Area: " + area);

    scanner.close();
  }
}

class Rectangle implements Shape {
  
  @Override
  public void area() {
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the length: ");
    int length = scanner.nextInt();
    System.out.print("Enter the breadth: ");
    int breadth = scanner.nextInt();
    System.out.println();

    int area = length * breadth;
    System.out.println("Area: " + area);

    scanner.close();
  }
}

class Square implements Shape {
  
  @Override
  public void area() {
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the length: ");
    int length = scanner.nextInt();
    System.out.println();

    int area = length * length;
    System.out.println("Area: " + area);

    scanner.close();
  }
}

class ShapeFactory {
  
  public static Shape getShape(String shapeType) throws Exception {
    
    if (shapeType == null) {
      throw new Exception("Shape type cannot be null");
    }
    
    if (shapeType.equalsIgnoreCase("CIRCLE")) {
      return new Circle();
    } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
      return new Rectangle();
    } else if (shapeType.equalsIgnoreCase("SQUARE")) {
      return new Square();
    }
    
    throw new Exception("Unknown shape type: " + shapeType);
  }
}

public class FactoryPattern {

  public static void main(String... args) throws Exception {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the shape: ");
    String shape = scanner.next();
    System.out.println();

    Shape shapeObj = ShapeFactory.getShape(shape.toUpperCase());
    shapeObj.area();

    scanner.close();
  }
}