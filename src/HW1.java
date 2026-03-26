import java.util.Scanner;

public class HW1
{
    public static void main(String[] args)
    {
        System.out.println("Enter your dollar amount: ");

        float amount = (new Scanner(System.in)).nextFloat();

        int hundreds = (int) Math.floor(amount / 100);
        amount %= 100;

        int fifties = (int) Math.floor(amount / 50);
        amount %= 50;

        int twenties = (int) Math.floor(amount / 20);
        amount %= 20;

        int tens = (int) Math.floor(amount / 10);
        amount %= 10;

        int fives = (int) Math.floor(amount / 5);
        amount %= 5;

        int ones = (int) Math.floor(amount);
        amount %= 1;

        int quarters = (int) Math.floor(amount / 0.25f);
        amount %= 0.25f;

        int dimes = (int) Math.floor(amount / 0.1f);
        amount %= 0.1f;

        int nickels = (int) Math.floor(amount / 0.05f);
        amount %= 0.05f;

        int cents = Math.round(amount / 0.01f);

        System.out.println("You have: ");
        System.out.println("- " + hundreds + " hundred(s)");
        System.out.println("- " + fifties + " fifty(s)");
        System.out.println("- " + twenties + " twenty(s)");
        System.out.println("- " + tens + " ten(s)");
        System.out.println("- " + fives + " five(s)");
        System.out.println("- " + ones + " one(s)");
        System.out.println("- " + quarters + " quarter(s)");
        System.out.println("- " + dimes + " dime(s)");
        System.out.println("- " + nickels + " nickel(s)");
        System.out.println("- " + cents + " cent(s)");

        System.out.println("Goodbye!");
    }
}
