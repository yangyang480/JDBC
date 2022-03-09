package JdbcHandsOn;

import java.sql.*;

import static JdbcHandsOn.SQLQueries.GetEmployeeOfficeCode;
import static com.example.constants.Constants.JDBC_MYSQL_LOCALHOST_CLASSICMODELS;
import static com.example.constants.Constants.MYSQL_CJ_JDBC_DRIVER;

public class Main {
    public static void main(String[] args) throws Exception, SQLException {
        Class.forName(MYSQL_CJ_JDBC_DRIVER); //we create constants class to contain the name and url. we can use"". or just this. we need to handle exception
        String url = JDBC_MYSQL_LOCALHOST_CLASSICMODELS; //since we are using the content from the other package then we need to import the package
        final String USER = "root"; //initializing
        final String PASS = "password";
        Connection conn = DriverManager.getConnection(url, USER, PASS);

        System.out.println("========1=============");
        PreparedStatement myp4 = conn.prepareStatement(GetEmployeeOfficeCode);
        myp4.setInt(1,1);
        myp4.setInt(2, 4);
        ResultSet result4 = myp4.executeQuery();

        while(result4.next())
        {
            String name = result4.getString("firstName");
            String name1 = result4.getString("lastName");
            System.out.println(name + " | " + name1);
        }




        System.out.println("========2=============");
        PreparedStatement myp3 = conn.prepareStatement(SQLQueries.GetOrderDetails);
        myp3.setInt(1,1);
        myp3.setInt(2, 4);
        myp3.setInt(3, 6);
        ResultSet result3 = myp3.executeQuery();

        while(result3.next())
        {
            String orderNumber = result3.getString("orderNumber");
            String productCode = result3.getString("productCode");
            String quantityOrdered = result3.getString("quantityOrdered");
            String priceEach = result3.getString("priceEach");
            String orderLineNumber = result3.getString("orderLineNumber");
            System.out.println(orderNumber + " | " + productCode + " | " + quantityOrdered + " | " + priceEach  + " | " + orderLineNumber);

        }



        System.out.println("========3=============");
        PreparedStatement myp2 = conn.prepareStatement(SQLQueries.UpdateExtensionNUmber);
        myp2.setInt(1, 5698);
        myp2.setInt(2, 2);
        myp2.executeUpdate();



        System.out.println("========4=============");
        PreparedStatement myp1 = conn.prepareStatement(SQLQueries.GetEmployLasname);
        myp1.setInt(1,5);
        ResultSet result1 = myp1.executeQuery();
        while (result1.next())
        {
            String employeeNumber = result1.getString("employeeNumber");
            String lastName = result1.getString("lastName");
            String firstName = result1.getString("firstName");
            String extension = result1.getString("extension");
            String email = result1.getString("email");
            String officeCode = result1.getString("officeCode");
            String reportsTo = result1.getString("reportsTo");
            String jobTitle = result1.getString("jobTitle");
            System.out.println(employeeNumber + " | " + lastName + " | " + firstName + " | " + extension  + " | " + email
                    + " | " + officeCode + " | " + reportsTo + " | " + jobTitle);

        }




    }
}
