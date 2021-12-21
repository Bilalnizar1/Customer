import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final Logger loggger=Logger.getLogger((Menu.class.getName()));

    public static void main(String[] args) throws ClassNotFoundException{
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl="jdbc:sqlite:Customer.db";

            try(Connection connection= DriverManager.getConnection(jdbcUrl)) {
                if (connection != null) {
                    DatabaseMetaData meta = connection.getMetaData();
                    System.out.println("The driver name is {0}" + meta.getDriverName());
                    System.out.println("Database Has been Created");
                }
           /* catch (Exception e){
                System.out.println(e.getMessage());
            }*/

                try (Statement stmt = connection.createStatement()) {
                    System.out.println("Database opened sucessfully");

                    String sql = " CREATE TABLE IF NOT EXISTS CUSTOMERSDATA " +
                            " (CustomerID INT PRIMARY KEY ," +
                            " CustomerName TEXT(30)) ";
                    System.out.println("Table Created");

                    stmt.executeUpdate(sql);


                }
                Scanner sc = new Scanner(System.in);
                int option = 0;
                while (option != 4) {
                    displayMenu();
                    option = sc.nextInt();
                    if (option == 1) {
                        System.out.print("Enter the Customer's id: ");

                        int CustomerID = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter the Customer's name: ");
                        String CustomerName = sc.nextLine();

                        String sql = " INSERT INTO CUSTOMERSDATA" + " (CustomerID, " + " CustomerName)" +
                                "VALUES (?,?)";
                        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                            stmt.setInt(1, CustomerID);
                            stmt.setString(2, CustomerName);
                            boolean executed = stmt.execute();
                                loggger.log(Level.INFO, (executed ? "Executed Sucessfully" : "failed to execute"));
                        }
                    } else if (option == 2) {
                        try (Statement stmt = connection.createStatement()) {
                            Class.forName("org.sqlite.JDBC");
                            String sql = "SELECT * FROM CUSTOMERSDATA";
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                //System.out.println(rs.getInt(CustomerID) + "\t" + rs.getString(CustomerName));
                                System.out.println(rs.getInt(1) + "-" + rs.getString(2));
                            }

                        }
                    } else if (option == 3) {
                        System.out.println("Enter the Customer id: ");
                        int id = sc.nextInt();

                    }
                }
            }
            catch(Exception e){
                    e.printStackTrace();
                }
            }


                    public static void displayMenu () {
                        System.out.println("***-----Menu-----***:" +
                                "\n [1] Add new Customer"
                                + "\n [2] Display All Customers"
                                + "\n [3] Retrieve a Customer's Data"
                                + "\n [4] Exit"
                                + "\n \n Enter your choice: ");
                    }


                }

