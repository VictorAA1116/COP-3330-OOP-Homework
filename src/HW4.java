import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HW4
{
    public static void main(String[] args)
    {
        Book[] books = new Book[100];
        System.out.println();
        System.out.println("----------Welcome to the book Program!----------");
        System.out.println();

        System.out.print("Would you like to create a book object? (yes/no): ");
        boolean keepGettingBooks = ReadResponse();
        int i = 0;

        while (keepGettingBooks)
        {
            Book newBook = GetBookEntry();

            books[i] = newBook;

            PrintEntry(newBook);

            System.out.print("\nWould you like to create another book object? (yes/no): ");

            keepGettingBooks = ReadResponse();

            if (keepGettingBooks)
            {
                i++;
            }
        }

        System.out.println("Sure!");

        PrintAllBooks(books);

        System.out.print("\nWould you like to search for a book? (yes/no): ");
        boolean keepSearching = ReadResponse();

        while (keepSearching)
        {
            SearchForBook(books);

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
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
        }

        return confirmed == 1;
    }

    private static Book GetBookEntry()
    {
        System.out.print("\nEnter the author, title and the isbn of the book separated by /: ");

        String[] splitResponse = ReadInitialEntry();

        String author = splitResponse[0];
        String title = splitResponse[1];
        String isbn = splitResponse[2];

        System.out.println("Got it!");

        System.out.print("\nNow, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");

        int bookType = CheckBookType()? 1 : 0;

        System.out.println("Got it!");

        if (bookType == 1)
        {
            System.out.print("\nEnter the list price of " + title.toUpperCase() + " by " + author.toUpperCase() + ": ");

            float price = (new Scanner(System.in).nextFloat());

            System.out.print("\nIs it on sale? (yes/no): ");

            boolean onSale = ReadResponse();
            double saleRate = 0.0d;

            if (onSale)
            {
                saleRate = ReadSaleRate();
            }

            System.out.println("Got it!");

            return new BookstoreBook(author, title, isbn, onSale, price, saleRate);
        }
        else
        {
            LibraryBook newBook = new LibraryBook(author, title, isbn);

            ReadSubjectEntry(newBook);

            return newBook;
        }
    }

    private static String[] ReadInitialEntry()
    {
        boolean validResponse = false;
        String[] splitResponse = new String[0];

        while (!validResponse)
        {
            String response = (new Scanner(System.in).nextLine());

            splitResponse = response.split("/");
            if (splitResponse.length != 3)
            {
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
            else
            {
                validResponse = true;
            }
        }

        return splitResponse;
    }

    private static boolean CheckBookType()
    {
        boolean validInput = false;
        int bookType = -1;

        while (!validInput)
        {
            String response = (new Scanner(System.in).nextLine());

            if (response.equalsIgnoreCase("LB"))
            {
                bookType = 0;
                validInput = true;
            }
            else if (response.equalsIgnoreCase("BB"))
            {
                bookType = 1;
                validInput = true;
            }
            else
            {
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
        }

        if (bookType == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static double ReadSaleRate()
    {
        System.out.print("\nDeduction percentage: ");

        boolean validResponse = false;
        double saleRate = -1.0d;

        while (!validResponse)
        {
            String response = (new Scanner(System.in).nextLine());

            if (response.substring(response.length()-1).equalsIgnoreCase("%"))
            {
                response = response.substring(0, response.length()- 1);
            }

            saleRate = Double.parseDouble(response);

            if (saleRate > 0 && saleRate <= 100)
            {
                validResponse = true;
            }
            else
            {
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
        }

        return saleRate;
    }

    private static void ReadSubjectEntry(LibraryBook book)
    {
        System.out.print("\nWhat's the subject?: ");

        boolean validResponse = false;

        while (!validResponse)
        {
            String subject = (new Scanner(System.in).nextLine());

            if (book.VerifySubject(subject))
            {
                validResponse = true;
                book.SetSubject(subject);
            }
            else
            {
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
        }

        System.out.println("Got it!");
    }

    private static void PrintEntry(Book book)
    {
        System.out.println("\nHere is your " + book.getBookType().toLowerCase() + " information");

        System.out.println(book);
    }

    private static void PrintAllBooks(Book[] books)
    {
        System.out.println("\nHere are all the books you entered...");

        LibraryBook[] libraryBooks = new LibraryBook[100];
        int l = 0;

        BookstoreBook[] bookstoreBooks = new BookstoreBook[100];
        int b = 0;

        for (Book book: books)
        {
            if (book == null) continue;

            if (book.getBookType() == "Library Book")
            {
                libraryBooks[l] = (LibraryBook) book;
                l++;
            }
            else
            {
                bookstoreBooks[b] = (BookstoreBook) book;
                b++;
            }
        }

        System.out.println("Library Books (" +  l + ")");

        for (LibraryBook book : libraryBooks)
        {
            if (book == null) continue;

            System.out.println("    " + book);
        }

        System.out.println("____");

        System.out.println("Bookstore Books (" + b + ")");

        for (BookstoreBook book : bookstoreBooks)
        {
            if (book == null) continue;

            System.out.println("    " + book);
        }

        System.out.println("____");
    }

    private static void SearchForBook(Book[] books)
    {
        System.out.print("\nSearch by isbn, author or title?: ");
        boolean validResponse = false;
        int searchMethod = -1;

        while (!validResponse)
        {
            String response = (new Scanner(System.in).nextLine());

            if (response.equalsIgnoreCase("isbn"))
            {
                searchMethod = 0;
                validResponse = true;
            }
            else if (response.equalsIgnoreCase("author"))
            {
                searchMethod = 1;
                validResponse = true;
            }
            else if (response.equalsIgnoreCase("title"))
            {
                searchMethod = 2;
                validResponse = true;
            }
            else
            {
                System.out.print("Oops! That's not a valid entry. Please try again: ");
            }
        }

        Book[] results = new Book[100];

        int LBresults = 0;
        int BBresults = 0;

        validResponse = false;

        if (searchMethod == 0)
        {
            System.out.print("\nEnter the first three characters of the isbn: ");
            String response = "";

            while (!validResponse)
            {
                response = (new Scanner(System.in).nextLine());

                if (response.length() == 3)
                {
                    validResponse = true;
                }
                else
                {
                    System.out.print("Oops! That's not a valid entry. Please try again: ");
                }
            }

            int b = 0;

            for (Book book : books)
            {
                if (book == null) continue;

                if (book.GetISBN().substring(0,3).equalsIgnoreCase(response))
                {
                    if (book.getBookType().equalsIgnoreCase("Library Book"))
                    {
                        results[b] = book;
                        LBresults++;
                    }
                    else
                    {
                        results[b] = book;
                        BBresults++;
                    }

                    b++;
                }
            }
        }
        else if (searchMethod == 1)
        {
            System.out.print("\nEnter the first three letters of the author: ");

            String response = "";

            while (!validResponse)
            {
                response = (new Scanner(System.in).nextLine());

                if (response.length() == 3)
                {
                    validResponse = true;
                }
                else
                {
                    System.out.print("Oops! That's not a valid entry. Please try again: ");
                }
            }

            int b = 0;

            for (Book book : books)
            {
                if (book == null) continue;

                if (book.GetAuthor().substring(0,3).equalsIgnoreCase(response))
                {
                    if (book.getBookType().equalsIgnoreCase("Library Book"))
                    {
                        results[b] = book;
                        LBresults++;
                    }
                    else
                    {
                        results[b] = book;
                        BBresults++;
                    }

                    b++;
                }
            }
        }
        else
        {
            System.out.print("\nEnter the first three letters of the title: ");

            String response = "";

            while (!validResponse)
            {
                response = (new Scanner(System.in).nextLine());

                if (response.length() == 3)
                {
                    validResponse = true;
                }
                else
                {
                    System.out.print("Oops! That's not a valid entry. Please try again: ");
                }
            }

            int b = 0;

            for (Book book : books)
            {
                if (book == null) continue;

                if (book.GetTitle().substring(0,3).equalsIgnoreCase(response))
                {
                    if (book.getBookType().equalsIgnoreCase("Library Book"))
                    {
                        results[b] = book;
                        LBresults++;
                    }
                    else
                    {
                        results[b] = book;
                        BBresults++;
                    }

                    b++;
                }
            }
        }

        System.out.println("\nWe found " + LBresults + " Library Book(s) and " + BBresults + " Bookstore Book(s):");

        for (Book book : results)
        {
            if (book == null) continue;

            System.out.println("    " + book);
        }

        System.out.println("____");
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
            String formattedPrice = String.format("%.2f", price);
            String formattedSalePrice = String.format("%.2f", GenerateSalePrice());
            return "[" + super.toString() + "-$" + formattedPrice + " listed for $" + formattedSalePrice + "]";
        }
        else
        {
            String formattedPrice = String.format("%.2f", price);
            return "[" +super.toString() + "-$" + formattedPrice + "]";
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
        boolean validSubject = VerifySubject(subject);
        if (!validSubject) return;

        this.subject = subject;
        GenerateCallNumber();
    }

    public boolean VerifySubject(String subject)
    {
        return subjectCodes.containsValue(subject.toUpperCase());
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
            if (subject.equalsIgnoreCase(subjectCodeEntry.getValue().toString()))
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
        SetCallNumber(GetSubjectCode() + "." + GetFloorNum() + "." + GetAuthor().substring(0,3).toUpperCase() + "." + GetISBN().substring(GetISBN().length() - 1).toUpperCase());
    }

    @Override
    public String toString()
    {
        return "[" + super.toString() + "-" + callNumber + "]";
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
        return isbn + "-" + title.toUpperCase() + " by " + author.toUpperCase();
    }
}
