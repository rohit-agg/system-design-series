import java.util.Map;
import java.util.HashMap;

class Ball {

  private String color;

  public Ball(String color) {
    this.color = color;
  }

  public void draw(int x, int y) {
    System.out.println("Drawing " + color + " ball at (" + x + "," + y + ")");
  }
}

class BallFactory {

  private static Map<String, Ball> balls = new HashMap<>();

  public static Ball getBall(String color) {
    
    Ball ball = balls.get(color);
    if (ball == null) {
      ball = new Ball(color);
      balls.put(color, ball);
      System.out.println("Creating " + color + " ball");
    }
    
    return ball;
  }
}

public class FlyweightPattern {

  public static void main(String... args) {

    Ball redBall1 = BallFactory.getBall("Red");
    redBall1.draw(10, 20);

    Ball redBall2 = BallFactory.getBall("Red");
    redBall2.draw(30, 40);

    Ball blueBall = BallFactory.getBall("Blue");
    blueBall.draw(50, 60);
  }
}