import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Server {

  private String name;
  private double cpuUtilization;

  public Server(String name) {
    this.name = name;
    this.refresh();
    System.out.println("Server: " + this.name + ", CPU utilizaton: " + String.format("%.2f", this.cpuUtilization)
        + " initialized successfully");
  }

  public String getName() {
    return this.name;
  }

  public double getCpuUtilization() {
    return this.cpuUtilization;
  }

  public void refresh() {
    this.cpuUtilization = ThreadLocalRandom.current().nextDouble(5, 10);
  }
}

class LoadBalancer {

  private List<Server> servers;

  public LoadBalancer(List<Server> servers) {
    this.servers = servers;
    System.out.println("Load Balancer initialized successfully");
  }

  public Server getNextServer() {

    Server currentServer = null;
    double minCpuUtil = Double.MAX_VALUE;

    for (Server server : this.servers) {
      if (server.getCpuUtilization() < minCpuUtil) {
        minCpuUtil = server.getCpuUtilization();
        currentServer = server;
      }
    }

    currentServer.refresh();
    return currentServer;
  }
}

public class ResourceBased {

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
      System.out.println("Request " + i + " routed to " + nextServer.getName() + " with CPU Utilisation "
          + String.format("%.2f", nextServer.getCpuUtilization()) + "%");
      Thread.sleep(250);
    }

    scanner.close();
  }
}
