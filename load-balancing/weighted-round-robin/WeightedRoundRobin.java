import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Server {

  private String name;
  private int weight;

  public Server(String name, int weight) {
    this.name = name;
    this.weight = weight;
    System.out.println("Server: " + this.name + ", weight: " + this.weight + " initialized successfully");
  }

  public String getName() {
    return this.name;
  }

  public int getWeight() {
    return this.weight;
  }
}

class LoadBalancer {

  private List<Server> servers;
  private int totalWeight;
  private int[] cumulativeWeights;
  private int currentWeight;
  private int currentServer;

  public LoadBalancer(List<Server> servers) {
    this.servers = servers;
    this.totalWeight = calculateTotalWeight();
    this.cumulativeWeights = calculateCumulativeWeights();
    this.currentWeight = 0;
    this.currentServer = 0;
    System.out.println("Load Balancer initialized successfully");
  }

  private int calculateTotalWeight() {
    int totalWeight = 0;
    for (int i = 0; i < this.servers.size(); i++) {
      totalWeight += this.servers.get(i).getWeight();
    }
    return totalWeight;
  }

  private int[] calculateCumulativeWeights() {
    int[] cumulativeWeights = new int[this.servers.size()];
    cumulativeWeights[0] = this.servers.get(0).getWeight();
    for (int i = 1; i < this.servers.size(); i++) {
      cumulativeWeights[i] = cumulativeWeights[i - 1] + this.servers.get(i).getWeight();
    }
    return cumulativeWeights;
  }

  public Server getNextServer() {

    Server nextServer = this.servers.get(this.currentServer);

    this.currentWeight += 1;
    if (this.currentWeight >= this.cumulativeWeights[this.currentServer]) {
      this.currentServer += 1;
    }

    this.currentWeight %= this.totalWeight;
    this.currentServer %= this.servers.size();
    return nextServer;
  }
}

public class WeightedRoundRobin {

  public static void main(String... args) throws InterruptedException {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the number of servers: ");
    int servers = scanner.nextInt();
    System.out.print("Enter the maximum weight of a server: ");
    int maxWeight = scanner.nextInt();
    System.out.println();

    List<Server> serverList = new ArrayList<>();
    for (int i = 1; i <= servers; i++) {
      serverList.add(new Server("Server " + i, ThreadLocalRandom.current().nextInt(1, maxWeight + 1)));
    }

    LoadBalancer loadBalancer = new LoadBalancer(serverList);

    System.out.println();
    System.out.print("Enter the number of requests to simulate: ");
    int numOfRequests = scanner.nextInt();
    System.out.println();

    for (int i = 1; i <= numOfRequests; i++) {
      Server nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + i + " routed to " + nextServer.getName());
      Thread.sleep(250);
    }

    scanner.close();
  }
}
