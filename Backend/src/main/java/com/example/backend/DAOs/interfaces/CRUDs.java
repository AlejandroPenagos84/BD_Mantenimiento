package com.example.backend.DAOs.interfaces;
import java.util.List;

public interface CRUDs <T, ID> {
    T findById(ID id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void deleteById(ID id);
}
