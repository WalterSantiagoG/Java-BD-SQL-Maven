package org.example.platzi.repository;

import org.example.platzi.model.Employee;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Connection myConn = getConnection();
            Statement myStant = myConn.createStatement();
            ResultSet myRes = myStant.executeQuery("SELECT * FROM employees");){
            while (myRes.next()){
                employees.add(createEmployee(myRes));
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(Connection myConn = getConnection();
            PreparedStatement myStant =  myConn.prepareStatement("SELECT * FROM employees WHERE id = ?")){
            myStant.setInt(1,id);
            try(ResultSet myRes = myStant.executeQuery();){
                if (myRes.next()){
                    employee = createEmployee(myRes);
                }
            }

        }
        return employee;
    }

    @Override
    public void update(Integer id, Employee employee) throws SQLException {
        //Solución al Reto, actualizar empleado si existe, si no existe, crear el empleado automáticamente
        if(getById(id) != null){
            String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ?, curp = ?  WHERE id = "+id;
            try(Connection myConn = getConnection();
                PreparedStatement myStamt = myConn.prepareStatement(sql);){
                myStamt.setString(1,employee.getFirst_name());
                myStamt.setString(2,employee.getPa_surname());
                myStamt.setString(3,employee.getMa_surname());
                myStamt.setString(4,employee.getEmail());
                myStamt.setFloat(5,employee.getSalary());
                myStamt.setString(6,employee.getCurp());
                myStamt.executeUpdate();
            }
            System.out.println("Empleado existe, actualización realizada");
        }else{
            //En el punto, se puede indicar con un mensaje que no existe el empleado y tiene la opción de crearlo
            //o en su defecto seguir intentando colocar el id correcto, para no crear el empleado automáticamente
            save(employee);
            System.out.println("Empleado no existe, nuevo usuario creado");
        }
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection myConn = getConnection();
            PreparedStatement myStamt = myConn.prepareStatement(sql);){
            myStamt.setString(1,employee.getFirst_name());
            myStamt.setString(2,employee.getPa_surname());
            myStamt.setString(3,employee.getMa_surname());
            myStamt.setString(4,employee.getEmail());
            myStamt.setFloat(5,employee.getSalary());
            myStamt.setString(6,employee.getCurp());
            myStamt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        if(getById(id) != null){
            try(Connection myConn = getConnection();
                PreparedStatement myStamt = myConn.prepareStatement("DELETE FROM employees WHERE id= ?")){
                myStamt.setInt(1,id);
                myStamt.executeUpdate();
                System.out.println("Empleado eliminado");
            }
        }else{
            System.out.println("Empleado a eliminar no existe");
        }
    }

    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        e.setCurp(myRes.getString("curp"));
        return e;
    }
}
