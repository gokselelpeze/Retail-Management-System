package sample.Interfaces;

import sample.company.Employee;

public interface StaffManager {
    /**
     * Adds a new employee
     */
    void addEmployee(Employee employee);

    /**
     * Removes an employee
     */
    void removeEmployee(Employee employee);

    /**
     * Edits an employee
     */
    void editEmployee(Employee employee);

    /**
     * Finds and returns employee with specified ID
     */
    Employee searchEmployee(int employeeID);
}
