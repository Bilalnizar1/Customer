import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Scanner;
class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Customer() {
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
    private static int CustomerID;
    private static String CustomerName;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl="jdbc:sqlite:Customer.db";

            try {
                Connection connection= DriverManager.getConnection(jdbcUrl);
                if(connection!=null){
                    DatabaseMetaData meta=connection.getMetaData();
                    System.out.println("Tha driver name is" +meta.getDriverName());
                    System.out.println("Database Has been Created");
                }}
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(jdbcUrl);
            try {
                System.out.println("Database opened sucessfully");
                Statement stmt = conn.createStatement();
                String sql=" CREATE TABLE CUSTOMERSDATA " +
                            " (CustomerID INT(20) PRIMARY KEY ," +
                        " CustomerName TEXT(30)) ";
                System.out.println("Table Created");

                stmt.executeUpdate(sql);


            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                String sql = " INSERT INTO CUSTOMERSDATA" + " (CustomerID, " + " CustomerName)" +
                        "VALUES (?,?)";
                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setInt(1,CustomerID);
                stmt.setString(2,CustomerName);
                stmt.executeUpdate(sql);

            }
            catch (Exception e){
                System.out.println(e.getMessage());

            }
            try{
                Statement stmt=conn.createStatement();
                Class.forName("org.sqlite.JDBC");
                String sql="SELECT* FROM CUSTOMERSDATA";
                ResultSet rs=stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getInt(CustomerID) + "\t" + rs.getString(CustomerName));
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            try {
                Statement stmt=conn.createStatement();

                }
             catch (SQLException e){
                System.out.println("Error Connecting Database");
                e.printStackTrace();
            }










            Customer app=new Customer();
            Scanner sc = new Scanner(System.in);
            Customer[] customer = {};
            int option = 0;
            while(option != 6) {
                displayMenu();
                option = sc.nextInt();
                if(option == 1) {
                    System.out.print("Enter the Customer's id: ");

                    int CustomerID = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter the Customer's name: ");
                    String CustomerName = sc.nextLine();
                    Customer newCustomer = new Customer(CustomerName, CustomerID);

                    Customer[] temp = new Customer[customer.length + 1];
                    for(int i=0; i<temp.length-1; i++) {
                        temp[i] = customer[i];
                    }
                    temp[temp.length-1] = newCustomer;
                    customer = temp.clone();
                }else if(option == 2) {
                    for(Customer emp: customer) {
                        System.out.println(emp.toString());
                    }
                }else if(option == 3) {
                    System.out.print("Enter the Customer's id: ");
                    int id = sc.nextInt();
                    int i = 0;
                    boolean exists = false;
                    for(i=0; i<customer.length; i++) {
                        if(customer[i].getID() == id) {
                            exists = true;
                            break;
                        }
                    }
                    if(exists) {
                        System.out.println("Customer's Data: " + customer[i].toString());
                    }else {
                        System.out.println("Customer not found.");
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

