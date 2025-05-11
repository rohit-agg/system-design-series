import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LoadBalancer {

  private List<String> servers;
  private int current;

  public LoadBalancer(List<String> servers) {
    this.servers = servers;
    this.current = 0;
    System.out.println("Load Balancer initialized successfully");
  }

  public String getNextServer() {

    String nextServer = this.servers.get(this.current);
    this.current += 1;
    this.current %= this.servers.size();
    return nextServer;
  }
}

public class RoundRobin {

  public static void main(String... args) throws InterruptedException {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of servers: ");
    int servers = scanner.nextInt();

    List<String> serverList = new ArrayList<>();
    for (int i = 1; i <= servers; i++) {
      serverList.add("Server " + i);
    }

    LoadBalancer loadBalancer = new LoadBalancer(serverList);

    System.out.print("Enter the number of requests to simulate: ");
    int numOfRequests = scanner.nextInt();

    for (int i = 1; i <= numOfRequests; i++) {
      String nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + i + " routed to " + nextServer);
      Thread.sleep(250);
    }

    scanner.close();
  }
}