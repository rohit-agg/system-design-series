interface Coffee {
  int cost();
}

class SimpleCoffee implements Coffee {

  @Override
  public int cost() {
    return 5;
  }
}

class CoffeeDecorator implements Coffee {

  protected Coffee decoratedCoffee;

  public CoffeeDecorator(Coffee coffee) {
    this.decoratedCoffee = coffee;
  }

  @Override
  public int cost() {
    return decoratedCoffee.cost();
  }
}

class CaramelCoffee extends CoffeeDecorator {

  public CaramelCoffee(Coffee coffee) {
    super(coffee);
  }

  @Override
  public int cost() {
    return super.cost() + 2;
  }
}

class HazelnutCoffee extends CoffeeDecorator {

  public HazelnutCoffee(Coffee coffee) {
    super(coffee);
  }

  @Override
  public int cost() {
    return super.cost() + 3;
  }
}

public class DecoratorPattern {

  public static void main(String... args) {

    Coffee simpleCoffee = new SimpleCoffee();
    System.out.println("Cost of Simple Coffee: $" + simpleCoffee.cost());

    Coffee caramelCoffee = new CaramelCoffee(simpleCoffee);
    System.out.println("Cost of Caramel Coffee: $" + caramelCoffee.cost());

    Coffee hazelnutCaramelCoffee = new HazelnutCoffee(caramelCoffee);
    System.out.println("Cost of Hazelnut Caramel Coffee: $" + hazelnutCaramelCoffee.cost());
  }
}