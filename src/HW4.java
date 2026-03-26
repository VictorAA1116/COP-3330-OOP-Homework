import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HW4
{
    public static void main(String[] args)
    {
        Book[] books = new Book[100];

        System.out.println("Welcome to the book Program!");

        System.out.print("Would you like to create a book object? (yes/no):");
        boolean keepGettingBooks = ReadResponse();

        while (keepGettingBooks)
        {
            GetBookEntry();

            System.out.print("\nWould you like to create another book object? (yes/no): ");

            keepGettingBooks = ReadResponse();
        }

        System.out.println("\nSure!");

        PrintAllBooks();

        System.out.print("\nWould you like to search for a book? (yes/no): ");
        boolean keepSearching = ReadResponse();

        while (keepSearching)
        {
            GetBookEntry();

            System.out.print("\nWould you like to search for another book? (yes/no): ");

            keepSearching = ReadResponse();
        }

        System.out.println("Take Care!");
    }

    private static boolean ReadResponse()
    {
        int confirmed = -1;

        while (confirmed == -1)
        {
            String response = (new Scanner(System.in).nextLine());

            if (response.equalsIgnoreCase("yes"))
            {
                confirmed = 1;
            }
            else if (response.equalsIgnoreCase("no"))
            {
                confirmed = 0;
            }
            else
            {
                System.out.print("\nOops! That's not a valid entry. Please try again: ");
            }
        }

        return confirmed == 1;
    }

    private static void GetBookEntry()
    {
        System.out.print("\nEnter the author, title and the isbn of the book separated by /: ");

        System.out.println("Got it!");

        //PrintEntry();
    }

    private static void PrintEntry(Book book)
    {
        System.out.println("Here is your " + book.getBookType().toLowerCase() + " information");

        System.out.println(book);
    }

    private static void PrintAllBooks()
    {

    }
}

class BookstoreBook extends Book
{
    private boolean onSale;
    private double price;
    private double saleRate;

    public BookstoreBook(String author, String title, String isbn) {
        super(author, title, isbn);
    }

    public BookstoreBook(String author, String title, String isbn, boolean onSale, double price, double saleRate)
    {
        SetAuthor(author);
        SetTitle(title);
        SetISBN(isbn);
        SetOnSale(onSale);
        SetPrice(price);
        SetSaleRate(saleRate);
    }

    @Override
    public String getBookType() {
        return "Bookstore Book";
    }

    public boolean GetOnSale()
    {
        return onSale;
    }

    public void SetOnSale(boolean onSale)
    {
        this.onSale = onSale;
    }

    public double GetPrice()
    {
        return price;
    }

    public void SetPrice(double price)
    {
        this.price = price;
    }

    private double GetSaleRate()
    {
        return saleRate;
    }

    private void SetSaleRate(double saleRate)
    {
        this.saleRate = saleRate;
    }

    private double GenerateSalePrice()
    {
        return GetPrice() - (GetPrice() * (GetSaleRate() / 100.0d));
    }

    @Override
    public String toString()
    {
        if (onSale)
        {
            return super.toString() + ", $" + price + " listed for $" + GenerateSalePrice();
        }
        else
        {
            return super.toString() + ", $" + price;
        }
    }
}

class LibraryBook extends Book
{
    private String subject;
    private String callNumber;

    private Map<Character,String> subjectCodes = new HashMap<>();

    private void InitializeSubjectCodes()
    {
        subjectCodes.put('A', "GENERAL");
        subjectCodes.put('B', "PHILOSOPHY");
        subjectCodes.put('C', "RELIGION");
        subjectCodes.put('D', "WORLD HISTORY");
        subjectCodes.put('E', "HISTORY OF THE AMERICAS");
        subjectCodes.put('F', "GEOGRAPHY");
        subjectCodes.put('G', "ANTHROPOLOGY");
        subjectCodes.put('H', "SOCIAL STUDIES");
        subjectCodes.put('I', "INTERNET");
        subjectCodes.put('J', "POLITICAL SCIENCE");
        subjectCodes.put('K', "LAW");
        subjectCodes.put('L', "EDUCATION");
        subjectCodes.put('M', "MUSIC");
        subjectCodes.put('N', "FINE ARTS");
        subjectCodes.put('P', "LANGUAGE");
        subjectCodes.put('Q', "SCIENCE");
        subjectCodes.put('R', "MEDICINE");
        subjectCodes.put('S', "AGRICULTURE");
        subjectCodes.put('T', "TECHNOLOGY");
        subjectCodes.put('U', "MILITARY");
    }

    public LibraryBook(String author, String title, String isbn) {
        super(author, title, isbn);
        InitializeSubjectCodes();
    }

    public LibraryBook(String author, String title, String isbn, String subject, String callNumber)
    {
        SetAuthor(author);
        SetTitle(title);
        SetISBN(isbn);
        SetSubject(subject);
        SetCallNumber(callNumber);
        InitializeSubjectCodes();
    }

    @Override
    public String getBookType() {
        return "Library Book";
    }

    public String GetSubject()
    {
        return subject;
    }

    public void SetSubject(String subject)
    {
        this.subject = subject;
        GenerateCallNumber();
    }

    public String GetCallNumber()
    {
        return callNumber;
    }

    public void SetCallNumber(String callNumber)
    {
        this.callNumber = callNumber;
    }

    private String GetSubjectCode()
    {
        for (Map.Entry subjectCodeEntry : subjectCodes.entrySet())
        {
            if (subject == subjectCodeEntry.getValue())
            {
                return subjectCodeEntry.getKey().toString();
            }
        }

        return null;
    }

    private String GetFloorNum()
    {
        int floor;
        floor = (int)(Math.random() * 15);

        String floorString;

        if (floor > 9)
        {
            floorString = String.valueOf(floor);
        }
        else
        {
            floorString = "0" + floor;
        }
        return floorString;
    }

    private void GenerateCallNumber()
    {
        SetCallNumber(GetSubjectCode() + "." + GetFloorNum() + "." + GetAuthor().substring(0,3) + "." + GetISBN().substring(GetISBN().length() - 1));
    }

    @Override
    public String toString()
    {
        return super.toString() + "-" + callNumber;
    }
}

abstract class Book
{
    private String author;
    private String title;
    private String isbn;

    public Book (String author, String title, String isbn)
    {
        SetAuthor(author);
        SetTitle(title);
        SetISBN(isbn);
    }

    public Book()
    {
        SetAuthor(null);
        SetTitle(null);
        SetISBN(null);
    }

    public abstract String getBookType();

    public String GetAuthor()
    {
        return author;
    }

    public void SetAuthor(String author)
    {
        this.author = author;
    }

    public String GetTitle()
    {
        return title;
    }

    public void SetTitle(String title)
    {
        this.title = title;
    }

    public String GetISBN()
    {
        return isbn;
    }

    public void SetISBN(String isbn)
    {
        this.isbn = isbn;
    }

    @Override
    public String toString()
    {
        return isbn + "-" + title + " by " + author;
    }
}
