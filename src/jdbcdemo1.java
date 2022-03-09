import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;    //we need to import sql*, DriverManger, SQLException at least to run the register.

import static com.example.constants.Constants.JDBC_MYSQL_LOCALHOST_CLASSICMODELS;
import static com.example.constants.Constants.MYSQL_CJ_JDBC_DRIVER;

public class jdbcdemo1 {
    public static void main(String[] args) throws Exception, SQLException {
        //1.import jar
        //2. register
        Class.forName(MYSQL_CJ_JDBC_DRIVER); //we create constants class to contain the name and url. we can use"". or just this. we need to handle exception
        String url = JDBC_MYSQL_LOCALHOST_CLASSICMODELS; //since we are using the content from the other package then we need to import the package
        final String USER = "root"; //initializing
        final String PASS = "password";
        Connection conn = DriverManager.getConnection(url, USER, PASS); //connect to the database


         System.out.println("=====sending SQL to the database=========");
         String SelectSQL = "Select * FROM employees";
         Statement stmt = conn.createStatement();
         ResultSet result =  stmt.executeQuery(SelectSQL); //this is using SELECT, so we need to use this method.

        while(result.next())
        {
            String name = result.getString("firstName"); //get the result by using get()
            String email  = result.getString("email");
            System.out.println(name +" | " + email);
        }


        //DriverManager, Connection, Statement, ResuletSet are API.
        //1. Driver Manager，we need to register the driver manager by using the first method of register.
                //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                //but what I prefer is using Class.forName(MYSQL_CJ_JDBC_DRIVER); to use the Driver. every time get used, the static block will execute.
        //2. Connection.（Interface）
                //获取执行者对象： 获取普通执行者对象： Statement createStatement();
                //获取预编译执行者对象： PreparedStatement prepareStatement(String sql);
                    //Prepared Statement makes it easier to set SQL parameter values; Accepts query in constructor; Can safety insert variable into SQL; Improves application performance; Pre-compiles SQL statement.
                    //use PreparedStatement.set[Type] to set values; replace ? with data; Accepts position and data.
                    //have a lot methods: setInt(). setString(). setFloat(). setDouble(). executeUpdate(). executeQuery().
                //开启事务： setAutoCommit(boolean autoCommit); if the parameter is false then will start the AutoCommit
                //提交事务： commit();
                //回滚事务： rollback();
                //释放资源： void close();
        //3. Statement 执行SQL
                //DML，增删改 insert, update, delete：then we need to use this method:  int executeUpdate(String aql); int 是返回影响的行数
                //DQL，when we want to use SELECT, Then we need to use ResultSet executeQuery(String sql); resultset 返回值是封装查询的结果
                // void close() to close
        //4. ResultSet
                //if has next, if does then return true, otherwise false. So we can use boolean next() method to read. looping through data. ResultSet.next()
                //datatype getDatatype("name"); such as: int getInt("age"); ResultSet.get[Type]()

        System.out.println("=========preparedStatement===========");
        String sql = "Select * FROM employees WHERE employeeNumber = ?"; //? can be replaced with data. ? like a placeholder, so we don't need to hard code the value here.
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1002); // we set the employeenumber 1002 at last.
        ResultSet result2 = ps.executeQuery();

        while(result2.next()) //try making a for loop to iterate through table rows
        {
            String name = result2.getString("firstName");
            String email = result2.getString("email");
            String officecode = result2.getString("officeCode");
            System.out.println(name + " | " + email + " | " + officecode);
        }

        System.out.println("=======Deletes======================");
        //Can also use prepared statements for Insert, update and deletes.
        Connection myConn = null; //I get myConn error.
        PreparedStatement deletee = myConn.prepareStatement("delete from employees" + "where salary > ? and department = ?");
        deletee.setDouble(1, 80000); //set parameters
        deletee.setString(2,"Legal");
        int rowsAffected = deletee.executeUpdate(); //execute statement


        System.out.println("=============Update==================");
        PreparedStatement myp = conn.prepareStatement(SqlQueries.UpdateEmployeeByNumber); //we can go to the sqlqueries class.
        myp.setString(1, "Gary");
        myp.setString(2, "Larson");
        myp.setLong(3, 1002);
        myp.executeUpdate();


        System.out.println("=========separate Class for queries==============");
        //store all the sql queries in a separate class.
        //that class usually contains public static final string fields that store the queries in a variable
        //Then we will call the variables later in the prepared statement in the class that performs the functionality.
        PreparedStatement myseparate = conn.prepareStatement(SqlQueries.GetEmployeeByID);
        myseparate.setInt(1, 1002);
        ResultSet result3 = myseparate.executeQuery();


    }
}
