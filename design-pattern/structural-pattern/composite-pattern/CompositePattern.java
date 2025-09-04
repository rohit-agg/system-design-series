import java.util.List;
import java.util.ArrayList;

interface Task {
  void display();
}

class SimpleTask implements Task {
  private String title;

  public SimpleTask(String title) {
    this.title = title;
  }

  @Override
  public void display() {
    System.out.println("Simple Task: " + title);
  }
}

class TaskList implements Task {
  private String title;
  private List<Task> taskList;

  public TaskList(String title) {
    this.title = title;
    this.taskList = new ArrayList<>();
  }

  public void addTask(Task task) {
    taskList.add(task);
  }

  public void removeTask(Task task) {
    taskList.remove(task);
  }

  @Override
  public void display() {
    System.out.println("Task List: " + title);
    for (Task task : taskList) {
      task.display();
    }
  }
}

public class CompositePattern {

  public static void main(String... args) {

    Task simpleTask1 = new SimpleTask("Buy groceries");
    Task simpleTask2 = new SimpleTask("Clean the house");

    TaskList dailyTasks = new TaskList("Daily Tasks");
    dailyTasks.addTask(simpleTask1);
    dailyTasks.addTask(simpleTask2);

    Task simpleTask3 = new SimpleTask("Finish project report");
    TaskList workTasks = new TaskList("Work Tasks");
    workTasks.addTask(simpleTask3);
    workTasks.addTask(dailyTasks);

    workTasks.display();
  }
}