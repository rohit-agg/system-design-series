interface Document {
  void print();
}

class XMLDocument {

  public void printXML() {
    System.out.println("Printing XML Document");
  }
}

class DocumentAdapter implements Document {

  private XMLDocument xmlDocument;

  public DocumentAdapter() {
    this.xmlDocument = new XMLDocument();
  }

  public void print() {
    System.out.println("Converting to XML Document");
    this.xmlDocument.printXML();
  }
}

class JsonDocument implements Document {

  private DocumentAdapter documentAdapter;

  public JsonDocument() {
    this.documentAdapter = new DocumentAdapter();
  }

  public void print() {
    System.out.println("Printing JSON Document");
    this.documentAdapter.print();
  }
}

public class AdapterPattern {

  public static void main(String... args) {

    JsonDocument jsonDocument = new JsonDocument();
    jsonDocument.print();
  }
}