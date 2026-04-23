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
                AddFaculty(university);
                break;

            case "2":
                AddStudent(university);
                break;

            case "3":
                PrintTuition(university);
                break;

            case "4":
                PrintFacultyInfo(university);
                break;

            case"5":
                AddStaff(university);
                break;

            case "6":
                PrintStaffInfo(university);
                break;

            case "7":
                DeletePerson(university);
                break;

            case "8":
                ExitProgram(university);
                break;
        }
    }

    public static void AddFaculty(UniversityClass university)
    {
        boolean allInputsValid = false;
        Scanner scanner = new Scanner(System.in);
        Faculty newFaculty = new Faculty();

        int attempts = 1;

        while (!allInputsValid && attempts <= 3)
        {
            attempts++;

            System.out.println("\nEnter Faculty Info: ");
            System.out.print("\tName: ");

            if (!ValidateName(newFaculty, scanner, attempts))
            {
                continue;
            }

            System.out.println("\tID: ");

            if (!ValidateID(newFaculty, scanner, attempts, university))
            {
                continue;
            }

            System.out.println("\tDepartment: ");

            if (!ValidateDepartment(newFaculty, scanner, attempts))
            {
                continue;
            }

            System.out.println("\tRank:");

            if (!ValidateRank(newFaculty, scanner, attempts))
            {
                continue;
            }

            allInputsValid = true;
        }

        if (allInputsValid)
        {
            university.AddPerson(newFaculty);
            System.out.println("\nFaculty Added!");
        }

        ShowMainMenu(university);
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

            if (!ValidateName(newStudent, scanner, attempts))
            {
                continue;
            }

            System.out.print("\tID: ");

            if (!ValidateID(newStudent, scanner, attempts, university))
            {
                continue;
            }

            System.out.print("\tGPA: ");

            if (!ValidateGPA(newStudent, scanner, attempts))
            {
                continue;
            }

            System.out.print("\tCredit Hours: ");

            if (!ValidateCreditHours(newStudent, scanner, attempts))
            {
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

    public static void PrintTuition(UniversityClass university)
    {
        int attempts = 1;

        while (attempts <= 3)
        {
            attempts++;

            System.out.print("\nEnter the student's ID: ");

            String ID = (new Scanner(System.in)).nextLine();

            Student foundStudent = (Student) university.SearchForPerson(ID, UniversityClass.PersonType.Student);

            if (foundStudent == null)
            {
                System.out.println("Sorry, no student exists with ID = " + ID);

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            foundStudent.printInvoice();
            attempts = 4;
        }

        ShowMainMenu(university);
    }

    public static void PrintFacultyInfo(UniversityClass university)
    {
        int attempts = 1;

        while (attempts <= 3)
        {
            attempts++;

            System.out.print("\nEnter the faculty's ID: ");

            String ID = (new Scanner(System.in)).nextLine();

            Faculty foundFaculty = (Faculty) university.SearchForPerson(ID, UniversityClass.PersonType.Faculty);

            if (foundFaculty == null)
            {
                System.out.println("Sorry, no faculty exists with ID = " + ID);

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            foundFaculty.print();
            attempts = 4;
        }

        ShowMainMenu(university);
    }

    public static void AddStaff(UniversityClass university)
    {
        boolean allInputsValid = false;
        Scanner scanner = new Scanner(System.in);
        Staff newStaff = new Staff();

        int attempts = 1;

        while (!allInputsValid && attempts <= 3)
        {
            attempts++;

            System.out.println("\nEnter Staff Info: ");
            System.out.print("\tName: ");

            if (!ValidateName(newStaff, scanner, attempts))
            {
                continue;
            }

            System.out.println("\tID: ");

            if (!ValidateID(newStaff, scanner, attempts, university))
            {
                continue;
            }

            System.out.println("\tDepartment: ");

            if (!ValidateDepartment(newStaff, scanner, attempts))
            {
                continue;
            }

            System.out.println("\tRank:");

            if (!ValidateStatus(newStaff, scanner, attempts))
            {
                continue;
            }

            allInputsValid = true;
        }

        if (allInputsValid)
        {
            university.AddPerson(newStaff);
            System.out.println("\nStaff Added!");
        }

        ShowMainMenu(university);
    }

    public static void PrintStaffInfo(UniversityClass university)
    {
        int attempts = 1;

        while (attempts <= 3)
        {
            attempts++;

            System.out.print("\nEnter the staff's ID: ");

            String ID = (new Scanner(System.in)).nextLine();

            Staff foundStaff = (Staff) university.SearchForPerson(ID, UniversityClass.PersonType.Staff);

            if (foundStaff == null)
            {
                System.out.println("Sorry, no staff exists with ID = " + ID);

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            foundStaff.print();
            attempts = 4;
        }

        ShowMainMenu(university);
    }

    public static void DeletePerson(UniversityClass university)
    {
        int attempts = 1;
        Person foundPerson = null;

        while (attempts <= 3)
        {
            attempts++;

            System.out.print("Enter the person's ID: ");

            String ID = (new Scanner(System.in).nextLine());

            foundPerson = university.SearchForPerson(ID, UniversityClass.PersonType.Any);

            if (foundPerson == null)
            {
                System.out.println("Sorry, no person exists with ID = " + ID);

                if (attempts <= 3)
                {
                    System.out.println("Try Again!");
                }

                continue;
            }

            attempts = 4;
        }
        
        if (foundPerson != null)
        {
            System.out.println("The following person was found with the ID: " + foundPerson.GetID());

            foundPerson.print();

            System.out.print("\nAre you sure you want to delete this person? (Y/N): ");

            String[] validInputs = {"Y", "N"};
            String response = null;
            attempts = 1;

            while (attempts <= 3)
            {
                attempts++;

                response = ValidateInput((new Scanner(System.in).nextLine()), validInputs);

                if (response != null) break;

                System.out.println("Try Again!");
            }

            if (response != null && response.equalsIgnoreCase("Y"))
            {
                university.DeletePerson(foundPerson);
                System.out.println(foundPerson.GetFullName() + "has been deleted.");
            }
            else
            {
                System.out.println("Cancelling deletion of " + foundPerson.GetFullName());
            }
        }

        ShowMainMenu(university);
    }

    public static void ExitProgram(UniversityClass university)
    {
        System.out.print("\nWould you like to create the report? (Y/N): ");

        String[] validInputs = {"Y", "N"};
        String response;

        while (true)
        {
            response = ValidateInput((new Scanner(System.in).nextLine()), validInputs);

            if (response != null) break;

            System.out.println("Try Again!");
        }

        if (response.equalsIgnoreCase("Y"))
        {
            CreateReport(university);
        }

        System.out.println("\nGoodbye!");
    }

    public static void CreateReport(UniversityClass university)
    {
        System.out.println("Would you like to sort your students by GPA or name? (Enter 1 for GPA. Enter 2 for name): ");

        String[] validInputs = {"1", "2"};
        String response;

        while (true)
        {
            response = ValidateInput((new Scanner(System.in).nextLine()), validInputs);

            if (response != null) break;

            System.out.println("Try Again!");
        }

        if (response.equalsIgnoreCase("1"))
        {
            university.CreateReport(1);
        }
        else
        {
            university.CreateReport(2);
        }
    }

    public static boolean ValidateName(Person person, Scanner scanner, int attempts)
    {
        boolean nameValid = false;

        try
        {
            nameValid = person.SetFullName(scanner.nextLine());
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
        }

        return nameValid;
    }

    public static boolean ValidateID(Person person, Scanner scanner, int attempts, UniversityClass university)
    {
        boolean IDvalid = false;

        try
        {
            IDvalid = person.SetID(scanner.nextLine());
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

            return IDvalid;
        }

        IDvalid = university.ValidateID(person.GetID());

        if (!IDvalid)
        {
            System.out.println("\n Invalid ID. Must be a unique ID for this person.");
        }

        return IDvalid;
    }

    public static boolean ValidateGPA(Student student, Scanner scanner, int attempts)
    {
        boolean GPAvalid = false;

        try
        {
            GPAvalid = student.SetGPA(scanner.nextDouble());
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
        }

        return GPAvalid;
    }

    public static boolean ValidateCreditHours(Student student, Scanner scanner, int attempts)
    {
        boolean CreditsValid = false;

        try
        {
            CreditsValid = student.SetCreditHours(scanner.nextInt());
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
        }

        return CreditsValid;
    }

    public static boolean ValidateDepartment(Employee employee, Scanner scanner, int attempts)
    {
        boolean departmentValid = false;

        try
        {
            departmentValid = employee.SetDepartment(scanner.nextLine());
        }
        catch (InputMismatchException ex)
        {
            departmentValid = false;
        }

        if (!departmentValid)
        {
            System.out.println("\nInvalid Department Format. Must be Mathematics, Engineering, or English");

            if (attempts <= 3)
            {
                System.out.println("Try Again!");
            }
        }

        return departmentValid;
    }

    public static boolean ValidateRank(Faculty faculty, Scanner scanner, int attempts)
    {
        boolean rankValid = false;

        try
        {
            rankValid = faculty.SetRank(scanner.nextLine());
        }
        catch (InputMismatchException ex)
        {
            rankValid = false;
        }

        if (!rankValid)
        {
            System.out.println("\nInvalid Rank Format: Must be either Professor or Adjunct");

            if (attempts <= 3)
            {
                System.out.println("Try Again!");
            }
        }

        return rankValid;
    }

    public static boolean ValidateStatus(Staff staff, Scanner scanner, int attempts)
    {
        boolean statusValid = false;

        try
        {
            statusValid = staff.SetStatus(scanner.nextLine());
        }
        catch (InputMismatchException ex)
        {
            statusValid = false;
        }

        if (!statusValid)
        {
            System.out.println("\nInvalid Status Format: Must be either Part-Time or Full-Time");

            if (attempts <= 3)
            {
                System.out.println("Try Again!");
            }
        }

        return statusValid;
    }
}

class UniversityClass
{
    public enum PersonType
    {
        Student,
        Faculty,
        Staff,
        Any
    }

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

    public Person SearchForPerson(String ID, PersonType type)
    {
        if (ID == null || ID.isBlank()) return null;

        for (Person person : AllPeople)
        {
            if (person == null) continue;

            if (ID.equalsIgnoreCase(person.GetID()))
            {
                switch (type)
                {
                    case Student:
                        if (person instanceof Student)
                        {
                            return person;
                        }
                        return null;
                    case Faculty:
                        if (person instanceof Faculty)
                        {
                            return person;
                        }
                        return null;
                    case Staff:
                        if (person instanceof Staff)
                        {
                            return person;
                        }
                        return null;
                    case Any:
                        return person;
                    default:
                        return null;
                }
            }
        }

        return null;
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

    public void CreateReport(int sortType)
    {
        if (sortType == 1)
        {
            SortStudentsByGPA();
        }
        else
        {
            SortStudentsByName();
        }


    }

    private void SortStudentsByName()
    {

    }

    private void SortStudentsByGPA()
    {

    }
}

abstract class Person
{
    private String fullName;
    private String id;

    public Person ()
    {
        SetFullName(null);
        SetID(null);
    }

    public Person (String fullName, String id)
    {
        SetFullName(fullName);
        SetID(id);
    }

    public abstract void print();

    public String GetFullName()
    {
        return fullName;
    }

    public boolean SetFullName(String newName)
    {
        if (newName == null || newName.isBlank()) return false;

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
        if (newID == null || newID.length() != 6) return false;

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

    public Employee ()
    {
        super();
        SetDepartment(null);
    }

    public Employee(String fullName, String id, String department)
    {
        super(fullName, id);
        SetDepartment(department);
    }

    public String GetDepartment()
    {
        return department;
    }

    public boolean SetDepartment(String newDepartment)
    {
        if (newDepartment == null) return false;

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
        System.out.println("\nFaculty Info:");
        System.out.println("\tName: " + GetFullName());
        System.out.println("\tID: " + GetID());
        System.out.println("\tDepartment: " + GetDepartment());
        System.out.println("\tRank: " + GetRank());
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

    public Staff()
    {
        super();
        SetStatus(null);
    }

    public Staff (String fullName, String id, String department, String status)
    {
        super(fullName, id, department);
        SetStatus(status);
    }

    @Override
    public void print()
    {
        System.out.println("\nStaff Info:");
        System.out.println("\tName: " + GetFullName());
        System.out.println("\tID: " + GetID());
        System.out.println("\tDepartment: " + GetDepartment());
        System.out.println("\tStatus: " + GetStatus());
    }

    public String GetStatus()
    {
        return status;
    }

    public boolean SetStatus(String newStatus)
    {
        if (newStatus == null) return false;

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

    public Student()
    {
        super();
        SetGPA(0.0d);
        SetCreditHours(0);
    }

    public Student(String fullName, String id, double GPA, int creditHours)
    {
        super(fullName, id);
        SetGPA(GPA);
        SetCreditHours(creditHours);
    }

    @Override
    public void print()
    {
        System.out.println("\nStudent Info:");
        System.out.println("\tName: " + GetFullName());
        System.out.println("\tID: " + GetID());
        System.out.println("\tGPA: " + GetGPA());
        System.out.println("\tCredit Hours: " + GetCreditHours());
    }

    public void printInvoice()
    {
        System.out.println("\nTuition Invoice for " + GetFullName());
        System.out.println("---------------------------------------------");
        System.out.println(GetFullName() + "\t\t" + GetID());
        System.out.println("Credit Hours: " + GetCreditHours());
        System.out.println("Fees: $52");
        System.out.println("Total Payment (after discount): " + CalculateTuition());
        System.out.println("---------------------------------------------");
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
