import java.sql.*;
import java.util.Scanner;
class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String toString() {
        return id + ": " + name;
    }
}
public class Menu {
        public static void main(String[] args) {
            String jdbcUrl="jdbc:sqlite:/C:\\Users\\bilal\\OneDrive\\Desktop\\SQL\\sqlite-tools-win32-x86-3370000\\Customer.db";

            try {
                Connection connection= DriverManager.getConnection(jdbcUrl);
                String sql="SELECT * FROM Customer";
                Statement statement=connection.createStatement();
                ResultSet result=statement.executeQuery(sql);
                while (result.next()){
                    int customerid= result.getInt("Customerid");
                    String customername= result.getString("CustomerName");
                    System.out.println(customerid + "|" + customername);



                }
            } catch (SQLException e){
                System.out.println("Error Connecting Database");
                e.printStackTrace();
            }









            Scanner sc = new Scanner(System.in);
            Customer[] customer = {};
            int option = 0;
            while(option != 6) {
                displayMenu();
                option = sc.nextInt();
                if(option == 1) {
                    System.out.print("Enter the Customer's id: "); //prompt for an input
                    int id = sc.nextInt(); //stores an employee's id
                    sc.nextLine();
                    System.out.print("Enter the Customer's name: "); //prompt user for input
                    String name = sc.nextLine(); //stores an employee's name
                    Customer newCustomer = new Customer(name, id); //creating an Employee object with id name salary

                    Customer[] temp = new Customer[customer.length + 1];
                    for(int i=0; i<temp.length-1; i++) {
                        temp[i] = customer[i]; //pass each element in employees array to the temp array
                    }
                    temp[temp.length-1] = newCustomer; //add the created employee object to the array
                    customer = temp.clone(); //pass the temp array to the employees array
                }else if(option == 2) {
                    for(Customer emp: customer) {
                        System.out.println(emp.toString());
                    }
                }else if(option == 3) {
                    System.out.print("Enter the employee's id: "); //prompt for input
                    int id = sc.nextInt(); //gets an employee's id
                    int i = 0;
                    boolean exists = false;
                    for(i=0; i<customer.length; i++) {
                        if(customer[i].getID() == id) {
                            exists = true;
                            break;
                        }
                    }
                    if(exists) {
                        System.out.println("Employee's Data: " + customer[i].toString());
                    }else {
                        System.out.println("Employee not found.");
                    }
                }
                else if(option == 4) {
                }
            }
        }
        public static void displayMenu() {
            System.out.println("***-----Menu-----***:" +
                    "\n [1] Add new Customer"
                    + "\n [2] Display All Customers"
                    + "\n [3] Retrieve a Customer's Data"
                    + "\n [4] Exit"
                    + "\n \n Enter your choice: ");
        }



    }

