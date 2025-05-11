import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Server {

  private String name;
  private int numberOfRequests;
  private double averageResponseTime;

  public Server(String name) {
    this.name = name;
    this.numberOfRequests = 0;
    this.averageResponseTime = 0.0;
    System.out.println("Server: " + this.name + ", no of Requests: 0, avg Response Time: 0ms initialized successfully");
  }

  public String getName() {
    return this.name;
  }

  public double getAverageResponseTime() {
    return this.averageResponseTime;
  }

  public void addRequest(int responseTime) {

    double totalResponseTime = this.numberOfRequests * this.averageResponseTime;
    this.numberOfRequests++;
    this.averageResponseTime = (totalResponseTime + responseTime) / this.numberOfRequests;
  }
}

class LoadBalancer {

  List<Server> servers;

  public LoadBalancer(List<Server> servers) {
    this.servers = servers;
  }

  public Server getNextServer() {

    int minResponseTime = ThreadLocalRandom.current().nextInt(100, 125);
    int responseTime = ThreadLocalRandom.current().nextInt(minResponseTime, 150);
    Double avgResponseTime = Double.MAX_VALUE;
    Server currentServer = null;

    for (Server server : this.servers) {
      if (server.getAverageResponseTime() < avgResponseTime) {
        currentServer = server;
        avgResponseTime = server.getAverageResponseTime();
      }
    }

    currentServer.addRequest(responseTime);
    return currentServer;
  }
}

public class LeastResponseTime {

  public static void main(String... args) throws InterruptedException {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the number of servers: ");
    int servers = scanner.nextInt();
    System.out.println();

    List<Server> serverList = new ArrayList<>();
    for (int i = 1; i <= servers; i++) {
      serverList.add(new Server("Server " + i));
    }

    LoadBalancer loadBalancer = new LoadBalancer(serverList);

    System.out.println();
    System.out.print("Enter the number of requests to simulate: ");
    int numOfRequests = scanner.nextInt();
    System.out.println();

    for (int i = 1; i <= numOfRequests; i++) {
      Server nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + i + " routed to " + nextServer.getName() + " with avg response time "
          + String.format("%.2f", nextServer.getAverageResponseTime()) + "ms");
      Thread.sleep(250);
    }

    scanner.close();
  }
}
