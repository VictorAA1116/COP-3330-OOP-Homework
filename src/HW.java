import java.util.ArrayList;

// Victor Avila
public class HW {
    public static void main(String[] args) {
        String fullName = "Erika T. Jones";
        String employeeNumber = "ej789";
        double payRate = 100.0, hoursWorked = 1.0;
        // TA will change the pay rate and the hours worked to test your code
        Employee e;
        e = new Employee(fullName, employeeNumber, payRate, hoursWorked);
        System.out.println(e); // To Test your toString method
        e.printCheck(); // This prints the check of Erika T. Jones
        Company company = new Company();
        company.hire ( new Employee ("Saeed Happy", "sh895" , 2 , 200) );
        company.hire (e);
        Company.printCompanyInfo();
        company.hire( new Employee("Enrico Torres" , "et897" , 3 , 150) );
        // You may add as many employees to company as you want.
        // The TAs will add their own employees
        // Make sure that each employee of company has a unique employeeNumber
        company.hire(new Employee("Victor Avila", "va123", 5, 100));
        Employee johnDoe = new Employee("John Doe", "jd123", 1, 1);
        company.hire(johnDoe);

        company.printCheck("ab784");

        // test company.printCheck
        company.printCheck("va123");

        company.deleteEmployeesBySalary(256.36);
        company.reverseEmployees();
        System.out.println( company.SearchByName("WaLiD WiLLiAms") );

        company.printEmployees();

        // Change John Doe's info
        johnDoe.setFullName("Jane Doe");
        johnDoe.setEmployeeNumber("jd345");
        johnDoe.setHoursWorked(2);
        johnDoe.setPayRate(2);
        company.printEmployees();

        // Test Deleting, targeted at Jane Doe
        company.deleteEmployeesBySalary(4 * 0.94f);
        company.printEmployees();

        System.out.println("Count employees paid less than 500: " + company.countEmployees(500));
        System.out.println("Bye!");
    }
}

//____________________________
class Employee {
    // Add the private attributes and the methods as mentioned above…
    private String fullName;
    private String employeeNumber;
    private double payRate;
    private double hoursWorked;

    Employee(String fullName, String employeeNumber, double payRate, double hoursWorked) {
        this.fullName = fullName;
        this.employeeNumber = employeeNumber;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newName) {
        fullName = newName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String newEmployeeNumber) {
        employeeNumber = newEmployeeNumber;
    }

    public double getPayRate () {
        return payRate;
    }

    public void setPayRate (double newPayRate) {
        payRate = newPayRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double newHoursWorked) {
        hoursWorked = newHoursWorked;
    }

    @Override
    public String toString() {
        return employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour";
    }

    private double grossPay() {
        return hoursWorked * payRate;
    }

    private double taxDeduction() {
        return grossPay() * 0.06f;
    }

    private double netPay () {
        return grossPay() - taxDeduction();
    }

    public void printCheck() {
        System.out.println("\nEmployee's Name: " + fullName);
        System.out.println("Employee's Number: " + employeeNumber);
        System.out.printf("Hourly rate of pay: %.2f\n", payRate);
        System.out.printf("Hours worked: %.2f\n\n", hoursWorked);

        System.out.printf("Total Gross Pay: $%.2f\n\n", grossPay());

        System.out.println("Deductions");
        System.out.print("Tax (6%):");
        System.out.printf("&%.2f\n\n", taxDeduction());

        System.out.printf("Net Pay: %.2f Dollars\n\n", netPay());
    }
}

//____________________________
class Company {
    private ArrayList<Employee> employeeList;
    private static String companyName;
    private static String companyTaxId;

    // Add static Setters and Getters for companyName and companyTaxId
    // No need to add a Setter and Getter for employeeList
    public static String getCompanyName() {
        return companyName;
    }

    public static String getCompanyTaxId() {
        return companyTaxId;
    }

    public Company() {
        employeeList = new ArrayList<>();
        companyName = "People's Place";
        companyTaxId = "v1rtua7C0mpan1";
    }

    public boolean hire ( Employee employee ) {
        // Add employee to employeeList
        // Note well that we can't add an employee whose employeeNumber already
        // assigned to another employee. In that case, this method returns false.
        // This method returns true otherwise
        for (Employee value : employeeList) {
            if (value.getEmployeeNumber().equals(employee.getEmployeeNumber())) {
                return false;
            }
        }

        employeeList.add(employee);

        return true;
    }

    public static void printCompanyInfo() {
        // This method prints the company name and its tax id
        // You may choose to print that any way you like!
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Tax ID: " + companyTaxId);
    }

    public void printEmployees() {
        // This method prints all employees (One employee per line)
        // Note that you already have toString in Employee
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public int countEmployees( double maxSalary ) {
        // This method returns the number of employees paid less than maxSalary
        int count = 0;
        for (Employee employee : employeeList) {
            if (employee.getPayRate() * employee.getHoursWorked() < maxSalary) {
                count++;
            }
        }
        return count;
    }

    public boolean SearchByName (String fullName ) {
        // This method returns true if fullName exists as an employee.
        // It returns false otherwise
        // this is a not a case-sensitive search.
        for (Employee employee : employeeList) {
            if (employee.getFullName().equals(fullName))
            {
                return true;
            }
        }

        return false;
    }

    public void reverseEmployees () {
        // This method reverses the order in which the employees were added to
        // the list. The last employee is swapped with the first employee, the
        // second last with the second and so on...
        ArrayList<Employee> reversedList = new ArrayList<Employee>();

        for (int i = employeeList.size() - 1; i >= 0; i--) {
            reversedList.add(employeeList.get(i));
        }

        employeeList = reversedList;
    }

    public void deleteEmployeesBySalary (double targetSalary ) {
        // This method deletes all employees who are paid targetSalary as a net
        // salary
        for (Employee employee : employeeList) {
            if (employee.getPayRate() * employee.getHoursWorked() * 0.94f == targetSalary) {
                employeeList.remove(employee);
                return;
            }
        }

        System.out.println("THERE WERE NO EMPLOYEES TO DELETE");
    }

    public void printCheck ( String employeeNumber) {
        // This method prints the check of the employee whose employee number is
        // employeeNumber. It prints NO SUCH EMPLOYEE EXISTS if employeeNumber is
        // not a registered employee number.
        for (Employee employee : employeeList) {
            if (employee.getEmployeeNumber().equals(employeeNumber))
            {
                employee.printCheck();
                return;
            }
        }

        System.out.println("NO SUCH EMPLOYEE EXISTS");
    }
}//end of class Company

