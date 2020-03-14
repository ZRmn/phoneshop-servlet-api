package com.es.phoneshop.model.dao;

import java.util.List;

public interface CrudDao<T> {

    void save(T item);

    T get(Long id);

    List<T> getAll();

    void update(T item);

    void delete(Long id);
}
