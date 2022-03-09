package JdbcHandsOn;

public class SQLQueries {
    public final static String GetEmployeeOfficeCode = "select * from employees where officeCode in (?, ?)";
    public final static String GetOrderDetails = "SELECT * " +
            "FROM orderdetails od JOIN orders o ON od.orderNumber = o.orderNumber " +
            "JOIN customers c ON c.customerNumber = o.customerNumber " +
            "JOIN employees e ON e.employeeNumber = c.salesRepEmployeeNumber " +
            "WHERE e.officeCode IN (?, ?, ?)";
    public final static String UpdateExtensionNUmber = "update employees set extension = ? where officeCode = ?";
    public final static String GetEmployLasname = "select * from employees where LENGTH(lastName) < ? ";
}
