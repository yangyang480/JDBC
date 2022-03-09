public class SqlQueries {
    public final static String GetEmployeeByID = "select * from employees where employeeNumber = ?";
    public final static String GetEmployeeBySalary = "select * from employees where salary = ?";
    public final static String UpdateEmployeeByNumber = "update employees set lastName = ?, firstName = ? where employeeNumber = ?";
}
