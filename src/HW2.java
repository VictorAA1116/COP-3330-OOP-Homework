import java.util.Scanner;

public class HW2
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to my program. You will be asked to enter the scores of a test one by one, and to enter -1 to stop.");

        boolean process = true;

        while (process)
        {
            ProcessClassScores();

            System.out.print("\nWould you like to process another class? (Y or N): ");

            process = (new Scanner(System.in).nextLine().equalsIgnoreCase("y"));
        }

        System.out.println("Goodbye!");
    }

    private static void ProcessClassScores()
    {
        Scanner scanner = new Scanner(System.in);
        int currentScore = 0;
        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        int F = 0;
        int numScores = 0;
        int passingScores = 0;
        float scoresTotal = 0f;

        while (currentScore != -1)
        {
            System.out.print("Enter Score (Enter -1 to stop): ");
            currentScore = scanner.nextInt();

            if (currentScore == -1)
            {
                break;
            }
            else if (currentScore > 100 || currentScore < -1)
            {
                System.out.println("Score " + currentScore + " Rejected");
            }
            else if (currentScore >= 90)
            {
                A++;
                passingScores++;
                scoresTotal += currentScore;
                numScores++;
            }
            else if (currentScore >= 80)
            {
                B++;
                passingScores++;
                scoresTotal += currentScore;
                numScores++;
            }
            else if (currentScore >= 70)
            {
                C++;
                passingScores++;
                scoresTotal += currentScore;
                numScores++;
            }
            else if (currentScore >= 60)
            {
                D++;
                scoresTotal += currentScore;
                numScores++;
            }
            else
            {
                F++;
                scoresTotal += currentScore;
                numScores++;
            }
        }

        if (numScores < 1) return;

        System.out.println("\nHere is your report:");
        System.out.println("    - A total of " + numScores + " scores entered. " + passingScores + " of them are 70 or higher.\n");

        System.out.println("    - Letter Grade distribution scores:");
        System.out.println("        - " + A + " Students earned the grade of A (90 - 100)");
        System.out.println("        - " + B + " Students earned the grade of B (80 - 89)");
        System.out.println("        - " + C + " Students earned the grade of C (70 - 79)");
        System.out.println("        - " + D + " Students earned the grade of D (60 - 69)");
        System.out.println("        - " + F + " Students earned the grade of F (59 or below)\n");

        float average = scoresTotal / numScores;
        System.out.printf("    - The average score is: %.2f\n", average);
    }
}
