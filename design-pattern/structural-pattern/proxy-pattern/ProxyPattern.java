interface IDocument {

  String getTitle();
  void display();
  void update();
  void remove();
}

class Document implements IDocument {

  private String title;

  public Document(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void display() {
    System.out.println("Displaying document: " + title);
  }

  public void update() {
    System.out.println("Updating document: " + title);
  }

  public void remove() {
    System.out.println("Removing document: " + title);
  }
}

class DocumentAdmin implements IDocument {

  private Document document;

  public DocumentAdmin(Document document) {
    this.document = document;
  }

  public String getTitle() {
    return document.getTitle();
  }

  public void display() {
    System.out.println("Admin accessing document: " + document.getTitle());
    document.display();
  }

  public void update() {
    System.out.println("Admin updating document: " + document.getTitle());
    document.update();
  }

  public void remove() {
    System.out.println("Admin removing document: " + document.getTitle());
    document.remove();
  }
}

class DocumentCollaborator implements IDocument {

  private Document document;

  public DocumentCollaborator(Document document) {
    this.document = document;
  }

  public String getTitle() {
    return document.getTitle();
  }

  public void display() {
    System.out.println("Collaborator accessing document: " + document.getTitle());
    document.display();
  }

  public void update() {
    System.out.println("Collaborator updating document: " + document.getTitle());
    document.update();
  }

  public void remove() {
    System.out.println("Collaborator cannot remove document: " + document.getTitle());
  }
}

class DocumentViewer implements IDocument {

  private Document document;

  public DocumentViewer(Document document) {
    this.document = document;
  }

  public String getTitle() {
    return document.getTitle();
  }

  public void display() {
    System.out.println("Viewer accessing document: " + document.getTitle());
    document.display();
  }

  public void update() {
    System.out.println("Viewer cannot update document: " + document.getTitle());
  }

  public void remove() {
    System.out.println("Viewer cannot remove document: " + document.getTitle());
  }
}

public class ProxyPattern {

  public static void main(String... args) {

    Document document = new Document("Design Patterns in Java");
    DocumentAdmin admin = new DocumentAdmin(document);
    DocumentCollaborator collaborator = new DocumentCollaborator(document);
    DocumentViewer viewer = new DocumentViewer(document);

    viewer.display();
    viewer.update();
    System.out.println();

    collaborator.update();
    collaborator.remove();
    System.out.println();

    admin.remove();
  }
}