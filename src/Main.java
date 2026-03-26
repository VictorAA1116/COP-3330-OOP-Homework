import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        System.out.println("Enter your name");
        String name = (new Scanner(System.in)).nextLine();

        System.out.println("Your name is " + name);

        boolean x = true;

        System.out.println(x);
    }
}