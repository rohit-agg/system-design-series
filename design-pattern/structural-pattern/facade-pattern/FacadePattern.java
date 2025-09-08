class Inventory {

  private Integer quantity;
  
  public Inventory(Integer quantity) {
    this.quantity = quantity;
  }

  public void buy(int quantity) {
    this.quantity -= quantity;
    System.out.println("Bought " + quantity);
  }
}

class Payment {

  private Double amount;

  public void pay(Double amount) {
    this.amount = amount;
    System.out.println("Paying " + amount);
  }
}

class Shipment {

  public void prepare() {
    System.out.println("Preparing shipment");
  }
}

class Order {

  private Inventory inventory;
  private Payment payment;
  private Shipment shipment;

  public Order() {
    this.inventory = new Inventory(100);
    this.payment = new Payment();
    this.shipment = new Shipment();
  }

  public void placeOrder(int quantity, Double amount) {
    this.inventory.buy(quantity);
    this.payment.pay(amount);
    this.shipment.prepare();
    System.out.println("Order placed successfully");
  }
}

public class FacadePattern {

  public static void main(String... args) {

    Order order = new Order();
    order.placeOrder(2, 100.0);
  }
}