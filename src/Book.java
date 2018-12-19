import java.util.Arrays;
import java.util.List;

class Book {
    String title;
    String author;
    int numberOfPages;
    String ISBN;

    // default ctor
    Book(String tit,String aut,int num) {
        title = tit;
        author = aut;
        numberOfPages = num;
        ISBN = "unknown";
    }

    Book(String tit,String aut,int num, String isbn) {
        title = tit;
        author = aut;
        numberOfPages = num;
        ISBN = isbn;
    }
}

public class ExampleBooks {
    public static void main(String[] args) {
        Book b1 = new Book("Java 8 lambdas", "Richard Warbuton", 182, "O'Reilly");
        Book b2 = new Book("Java 8 in action", "Raoul-Gabriel Urma", 497, "Manning");
        Book b3 = new Book("Functional thinking", "Neal Ford", 179, "O'Reilly");
        Book b4 = new Book("Learning scala", "Jason Swartz", 255, "O'Reilly");
        Book b5 = new Book("Parallel and concurrent programming in Haskell", "Simon Marlow", 321, "O'Reilly");
        Book b6 = new Book("Presentation patterns", "Neal Ford", 265, "Addisson Wesley");
        List<Book> books = Arrays.asList(b1, b2, b3, b4, b5, b6);

        System.out.println(b.title + " : " + b.author + " : " + b.numberOfPages);
    }
}