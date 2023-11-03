package org.example.platzi.repository;

import org.example.platzi.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> findAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void update(Integer id, Employee employee) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}

