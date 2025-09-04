import java.util.Scanner;

class Employee {

  private String name;
  private String employeeId;
  private String department;

  public void setName(String name) {
    this.name = name;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void display() {

    System.out.println("Employee Details:");
    System.out.println("Name: " + this.name);
    System.out.println("Employee ID: " + this.employeeId);
    System.out.println("Department: " + this.department);
  }
}

class EmployeeBuilder {

  private Employee employee;

  public EmployeeBuilder() {
    this.employee = new Employee();
  }

  public EmployeeBuilder name(String name) {
    this.employee.setName(name);
    return this; 
  }

  public EmployeeBuilder employeeId(String employeeId) {
    this.employee.setEmployeeId(employeeId);
    return this; 
  }

  public EmployeeBuilder department(String department) {
    this.employee.setDepartment(department);
    return this; 
  }

  public Employee build() {
    return this.employee;
  }
}

public class BuilderPattern {

  public static void main(String... args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println();
    System.out.print("Enter the name: ");
    String name = scanner.nextLine();
    System.out.print("Enter the employee ID: ");
    String employeeId = scanner.nextLine();
    System.out.print("Enter the department: ");
    String department = scanner.nextLine();
    System.out.println();

    Employee employee = new EmployeeBuilder()
        .name(name)
        .employeeId(employeeId)
        .department(department)
        .build();
    employee.display();

    scanner.close();
  }
}