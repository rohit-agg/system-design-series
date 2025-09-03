import java.util.Scanner;

interface Service {
  void name();
}

interface Cloud {
  Service service(String service) throws Exception;
}

class AWSDatabase implements Service {
  
  @Override
  public void name() {
    System.out.println("AWS Database Service: RDS / Aurora");
  }
}

class AWSStorage implements Service {
  
  @Override
  public void name() {
    System.out.println("AWS Storage Service: S3" );
  }
}

class GCPDatabase implements Service {
  
  @Override
  public void name() {
    System.out.println("GCP Database Service: Cloud SQL / Spanner");
  }
}

class GCPStorage implements Service {
  
  @Override
  public void name() {
    System.out.println("GCP Storage Service: Cloud Storage" );
  }
}

class AWS implements Cloud {

  @Override
  public Service service(String service) throws Exception {

    if (service == null) {
      throw new Exception("Service cannot be null");
    }

    if (service.equalsIgnoreCase("DATABASE")) {
      return new AWSDatabase();
    } else if (service.equalsIgnoreCase("STORAGE")) {
      return new AWSStorage();
    }

    throw new Exception("Invalid service: " + service);
  }
}

class GCP implements Cloud {
  
  @Override
  public Service service(String service) throws Exception {

    if (service == null) {
      throw new Exception("Service cannot be null");
    }

    if (service.equalsIgnoreCase("DATABASE")) {
      return new GCPDatabase();
    } else if (service.equalsIgnoreCase("STORAGE")) {
      return new GCPStorage();
    }

    throw new Exception("Invalid service: " + service);
  }
}

class CloudFactory {
  
  public static Cloud getCloud(String cloudType) throws Exception {
    
    if (cloudType == null) {
      throw new Exception("Cloud type cannot be null");
    }
    
    if (cloudType.equalsIgnoreCase("AWS")) {
      return new AWS();
    } else if (cloudType.equalsIgnoreCase("GCP")) {
      return new GCP();
    }
    
    throw new Exception("Invalid cloud type: " + cloudType);
  }
}

public class AbstractFactoryPattern {

  public static void main(String... args) throws Exception {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the cloud: ");
    String cloud = scanner.next();
    System.out.print("Enter the service: ");
    String service = scanner.next();
    System.out.println();

    Cloud cloudObj = CloudFactory.getCloud(cloud.toUpperCase());
    Service serviceObj = cloudObj.service(service.toUpperCase());
    serviceObj.name();

    scanner.close();
  } 
}