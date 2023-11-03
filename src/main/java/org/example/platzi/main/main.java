package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {

        System.out.println("---Listando todos---");
        Repository<Employee> repository = new EmployeeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("---Buscando por ID---");
        System.out.println(repository.getById(2));

        /*try(Connection myConn = DatabaseConnection.getInstance()){
            if (myConn.getAutoCommit()){
                myConn.setAutoCommit(false);
            }
            try {
                Repository<Employee> repository = new EmployeeRepository(myConn);
                System.out.println("---Insertar un nuevo cliente---");
                Employee employee = new Employee();
                /*employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname("Villa");
                employee.setEmail("ame2@mail.com");
                employee.setSalary((float)9500);
                employee.setCurp("AMEC234Y9152478TOU");
                repository.save(employee);
                myConn.commit();

                employee.setFirst_name("David");
                employee.setPa_surname("Gutierrez");
                employee.setMa_surname("Olvera");
                employee.setEmail("olv1@mail.com");
                employee.setSalary((float)9500);
                employee.setCurp("AMEC234Y9152478TOU");
                repository.save(employee);
                myConn.commit();

            } catch (SQLException e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }
        }*/

        //SwingApp app = new SwingApp();
        //app.setVisible(true);

        //Try-with resources - Try con recursos
        //Los recursos declarados dentro del bloque try se cierran automaticamente al finalizar el bloque
        //Ya sea que se haya producido una excepcion o no, se evita el finally despues del catch
        /*try( Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("---findAll()---");
            repository.findAll().forEach(System.out::println);

            System.out.println("---getById(Integer id)---");
            System.out.println(repository.getById(1));

            System.out.println("---save(Employee employee) insertando empleado---");
            Employee employee = new Employee();
            employee.setFirst_name("Diego");
            employee.setPa_surname("Pimentel");
            employee.setMa_surname("Gutierrez");
            employee.setEmail("diego@mail.com");
            employee.setSalary((float)7000000);
            repository.save(employee);

            System.out.println("---findAll() después de insertar empleado Diego---");
            repository.findAll().forEach(System.out::println);

            System.out.println("---update(Integer id, Employee employee) insertando empleado---Reto de la clase---");
            System.out.println("---Reto actualizar empleado si existe, si no existe, crear el empleado---");
            Integer idEmployee = 10; //Empleado id 10 no existe crea el usuario //Si colocamos un empleado que existe se actualiza
            Employee employeeU = new Employee();
            employeeU.setFirst_name("Juan");
            employeeU.setPa_surname("Perez");
            employeeU.setMa_surname("Sarmiento");
            employeeU.setEmail("jperez@mail.com");
            employeeU.setSalary((float)80000);
            repository.update(idEmployee, employeeU);

            System.out.println("---delete(Integer id)---");
            repository.delete(8);

            System.out.println("---findAll() después de actualizar o insertar empleado---");
            repository.findAll().forEach(System.out::println);
        }*/
    }

}
