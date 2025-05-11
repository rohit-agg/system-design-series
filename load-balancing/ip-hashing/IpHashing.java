import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class LoadBalancer {

  private List<String> servers;

  public LoadBalancer(List<String> servers) {
    this.servers = servers;
    System.out.println("Load Balancer initialized successfully");
  }

  public String getNextServer(String ipAddress) {

    int ipHash = Math.abs(ipAddress.hashCode());
    String nextServer = this.servers.get(ipHash % this.servers.size());
    return nextServer;
  }
}

public class IpHashing {

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
      String ipAddress = generateRandomIpAddress();
      String nextServer = loadBalancer.getNextServer(ipAddress);
      System.out.println("Request " + i + " originating from " + ipAddress + " routed to " + nextServer);
      Thread.sleep(250);
    }

    scanner.close();
  }

  private static String generateRandomIpAddress() {

    int part1 = ThreadLocalRandom.current().nextInt(256);
    int part2 = ThreadLocalRandom.current().nextInt(256);
    int part3 = ThreadLocalRandom.current().nextInt(256);
    int part4 = ThreadLocalRandom.current().nextInt(256);
    return part1 + "." + part2 + "." + part3 + "." + part4;
  }
}
