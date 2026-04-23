import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Final Project Done by : Victor Avila

public class FinalProject
{
    public static void main(String[] args)
    {
        UniversityClass university = new UniversityClass();

        System.out.println("\n\t\t\tWelcome to the Personnel Management System");

        ShowMainMenu(university);
    }

    public static String ValidateInput(String input, String[] validInputs)
    {
        if (input.isEmpty() || validInputs.length < 1) return null;

        for (String validOption : validInputs)
        {
            if (input.equalsIgnoreCase(validOption))
            {
                return validOption;
            }
        }

        return null;
    }

    public static void ShowMainMenu(UniversityClass university)
    {
        System.out.println("\nChoose one of the options:");
        System.out.println("1 - Add a faculty");
        System.out.println("2 - Add a student");
        System.out.println("3 - Print tuition invoice for a student");
        System.out.println("4 - Print faculty information");
        System.out.println("5 - Add a staff member");
        System.out.println("6 - Print the information of a staff member");
        System.out.println("7 - Delete a person");
        System.out.println("8 - Exit Program");
        System.out.println();
        System.out.print("Enter your selection: ");

        String[] validInputs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String response;

        while (true)
        {
            response = ValidateInput((new Scanner(System.in).nextLine()), validInputs);

            if (response != null) break;

            System.out.println("Try Again!");
        }

        switch (response)
        {
            case "1":
                AddFaculty();
                break;

            case "2":
                AddStudent(university);
                break;

            case "3":
                PrintTuition();
                break;

            case "4":
                PrintFacultyInfo();
                break;

            case"5":
                AddStaff();
                break;

            case "6":
                PrintStaffInfo();
                break;

            case "7":
                DeletePerson();
                break;

            case "8":
                ExitProgram();
                break;
        }
    }

    public static void AddFaculty()
    {

    }

    public static void AddStudent(UniversityClass university)
    {
        boolean allInputsValid = false;
        Scanner scanner = new Scanner(System.in);

        Student newStudent = new Student();

        int attempts = 1;

        while (!allInputsValid && attempts <= 3)
        {
            attempts++;

            System.out.println("\nEnter Student Info:");
            System.out.print("\tName: ");

            boolean nameValid = false;

            try
            {
                nameValid = newStudent.SetFullName(scanner.nextLine());
            }
            catch (InputMismatchException ex)
            {
                nameValid = false;
            }

            if (!nameValid)
            {
                System.out.println("\nInvalid Name Format. Must be all alphabetic characters");

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            System.out.print("\tID: ");

            boolean IDvalid = false;

            try
            {
                IDvalid = newStudent.SetID(scanner.nextLine());
            }
            catch (InputMismatchException ex)
            {
                IDvalid = false;
            }


            if (!IDvalid)
            {
                System.out.println("\nInvalid ID Format. Must be LetterLetterDigitDigitDigitDigit");

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            System.out.print("\tGPA: ");

            boolean GPAvalid = false;

            try
            {
                GPAvalid = newStudent.SetGPA(scanner.nextDouble());
            }
            catch (InputMismatchException ex)
            {
                GPAvalid = false;
            }

            if (!GPAvalid)
            {
                System.out.println("\nInvalid GPA Format. Must be a numerical value between 0.0 and 4.0");

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            System.out.print("\tCredit Hours: ");

            boolean CreditsValid = false;

            try
            {
                CreditsValid = newStudent.SetCreditHours(scanner.nextInt());
            }
            catch (InputMismatchException ex)
            {
                CreditsValid = false;
            }

            if (!CreditsValid)
            {
                System.out.println("\nInvalid Credit Hours Format. Must be a positive number");

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            allInputsValid = true;
        }

        if (allInputsValid)
        {
            university.AddPerson(newStudent);
            System.out.println("\nStudent Added!");
        }

        ShowMainMenu(university);
    }

    public static void PrintTuition()
    {

    }

    public static void PrintFacultyInfo()
    {

    }

    public static void AddStaff()
    {

    }

    public static void PrintStaffInfo()
    {

    }

    public static void DeletePerson()
    {

    }

    public static void ExitProgram()
    {

    }
}

class UniversityClass
{
    private ArrayList<Person> AllPeople = new ArrayList<Person>(100);

    public boolean AddPerson(Person person)
    {
        if (!ValidateID(person.GetID()))
        {
            return false;
        }

        AllPeople.add(person);
        return true;
    }

    public boolean DeletePerson(Person person)
    {
        AllPeople.remove(person);
        return true;
    }

    public boolean ValidateID (String ID)
    {
        for (Person person : AllPeople)
        {
            if (person == null) continue;

            if (ID.equalsIgnoreCase(person.GetID()))
            {
                return false;
            }
        }

        return true;
    }
}

abstract class Person
{
    private String fullName;
    private String id;

    public Person (String fullName, String id)
    {
        SetFullName(fullName);
        SetID(id);
    }

    public Person ()
    {
        fullName = null;
        id = null;
    }

    public abstract void print();

    public String GetFullName()
    {
        return fullName;
    }

    public boolean SetFullName(String newName)
    {
        if (newName.isBlank()) return false;

        for (int i = 0; i < newName.length(); i++)
        {
            if (!Character.isAlphabetic(newName.charAt(i)) && !Character.isWhitespace(newName.charAt(i)))
            {
                return false;
            }
        }

        this.fullName = newName;
        return true;
    }

    public String GetID()
    {
        return id;
    }

    public boolean SetID(String newID)
    {
        if (newID.length() != 6) return false;

        boolean validInput = true;

        for (int i = 0; i < newID.length() - 1; i++)
        {
            if (i <= 1)
            {
                if (!Character.isAlphabetic(newID.charAt(i)))
                {
                    validInput = false;
                }
            }
            else
            {
                if (!Character.isDigit(newID.charAt(i)))
                {
                    validInput = false;
                }
            }
        }

        if (!validInput) return false;

        this.id = newID;
        return true;
    }
}

abstract class Employee extends Person
{
    private String department;

    public Employee(String fullName, String id, String department)
    {
        SetFullName(fullName);
        SetID(id);
        SetDepartment(department);
    }

    public Employee ()
    {
        SetFullName(null);
        SetID(null);
        SetDepartment(null);
    }

    public String GetDepartment()
    {
        return department;
    }

    public boolean SetDepartment(String newDepartment)
    {
        boolean validInput =
                newDepartment.equalsIgnoreCase("Mathematics")
                || newDepartment.equalsIgnoreCase("Engineering")
                || newDepartment.equalsIgnoreCase("English");

        if (validInput)
        {
            this.department = newDepartment;
            return true;
        }

        return false;
    }
}

class Faculty extends Employee
{
    private String rank;

    @Override
    public void print()
    {
        System.out.print("");
    }

    public String GetRank()
    {
        return rank;
    }

    public boolean SetRank(String newRank)
    {
        if (newRank.equalsIgnoreCase("Professor") || newRank.equalsIgnoreCase("Adjunct"))
        {
            this.rank = newRank;
            return true;
        }

        return false;
    }
}

class Staff extends Employee
{
    private String status;

    @Override
    public void print()
    {

    }

    public String GetStatus()
    {
        return status;
    }

    public boolean SetStatus(String newStatus)
    {
        if (newStatus.equalsIgnoreCase("Part-Time") || newStatus.equalsIgnoreCase("Full-Time"))
        {
            this.status = newStatus;
            return true;
        }

        return false;
    }
}

class Student extends Person
{
    private double gpa;
    private int creditHours;

    @Override
    public void print()
    {

    }

    public double GetGPA()
    {
        return gpa;
    }

    public boolean SetGPA(double newGPA)
    {
        if (newGPA < 0 || newGPA > 4)
        {
            return false;
        }

        this.gpa = newGPA;
        return true;
    }

    public int GetCreditHours()
    {
        return creditHours;
    }

    public boolean SetCreditHours(int newCreditHours)
    {
        if (newCreditHours < 0) return false;

        this.creditHours = newCreditHours;
        return true;
    }

    private double CalculateTuition()
    {
        double rate = 236.45d;
        int adminFee = 52;

        boolean discount = GetGPA() >= 3.85d;

        double tuition = rate * GetCreditHours() + adminFee;

        if (discount)
        {
            tuition *= 0.75d;
        }

        return tuition;
    }
}
