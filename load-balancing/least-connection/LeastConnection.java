import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

class LoadBalancer {

  private PriorityQueue<Entry<String, Integer>> servers;

  public LoadBalancer(List<String> servers) {
    this.servers = new PriorityQueue<>(
      Comparator.comparingInt(Entry::getValue)
    );
    for (String server : servers) {
      this.servers.offer(new SimpleEntry<>(server, 0));
    }
    System.out.println("Load Balancer initialized successfully");
  }

  public Entry<String, Integer> getNextServer() {
    Entry<String, Integer> nextServer = this.servers.poll();
    this.servers.offer(new SimpleEntry<>(nextServer.getKey(), nextServer.getValue() + 1));
    return nextServer;
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
      Entry<String, Integer> nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + i + " routed to " + nextServer.getKey() + " which had " + nextServer.getValue() + " connections.");
      Thread.sleep(250);
    }

    scanner.close();
  }
}
