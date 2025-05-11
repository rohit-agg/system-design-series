import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class LoadBalancer {

  private Map<String, Integer> servers;

  public LoadBalancer(List<String> servers) {
    this.servers = new HashMap<>();
    for (String server : servers) {
      this.servers.put(server, 0);
    }
    System.out.println("Load Balancer initialized successfully");
  }

  public String getNextServer() {

    int minConnection = Integer.MAX_VALUE;
    String currentServer = null;
    for (Map.Entry<String, Integer> server : servers.entrySet()) {
      if (server.getValue() < minConnection) {
        currentServer = server.getKey();
        minConnection = server.getValue();
      }
    }

    this.servers.put(currentServer, minConnection + 1);
    return currentServer;
  }
}

public class LeastConnection {

  public static void main(String... args) throws InterruptedException {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the number of servers: ");
    int servers = scanner.nextInt();
    System.out.println();

    List<String> serverList = new ArrayList<>();
    for (int i = 1; i <= servers; i++) {
      serverList.add("Server " + i);
    }

    LoadBalancer loadBalancer = new LoadBalancer(serverList);

    System.out.println();
    System.out.print("Enter the number of requests to simulate: ");
    int numOfRequests = scanner.nextInt();
    System.out.println();

    for (int i = 1; i <= numOfRequests; i++) {
      String nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + i + " routed to " + nextServer);
      Thread.sleep(250);
    }

    scanner.close();
  }
}
