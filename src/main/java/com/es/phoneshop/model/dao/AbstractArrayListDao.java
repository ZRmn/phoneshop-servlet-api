package com.es.phoneshop.model.dao;

import java.util.*;

public abstract class AbstractArrayListDao<T> implements CrudDao<T> {

    protected List<T> items;

    protected AbstractArrayListDao() {
        items = new ArrayList<>();
    }

    protected abstract Long getId(T item);

    protected abstract void setId(T item, Long id);

    protected abstract void updateItem(T item, T update);

    @Override
    public void save(T item) {
        setId(item, findId());
        items.add(item);
    }

    @Override
    public T get(Long id) {
        return items.stream()
                .filter(t -> getId(t).equals(id))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void update(T item) {
        items.stream()
                .filter(t -> getId(t).equals(getId(item)))
                .findFirst()
                .ifPresent(t -> updateItem(t, item));
    }

    @Override
    public void delete(Long id) {
        items.removeIf(t -> getId(t).equals(id));
    }

    protected Long findId() {
        if (items.size() == 0) {
            return 1L;
        }

        Long[] ids = items.stream()
                .map(this::getId)
                .sorted()
                .toArray(Long[]::new);

        for (int i = 0; i < ids.length - 1; i++) {
            if (ids[i + 1] - ids[i] > 1) {
                return ids[i] + 1;
            }
        }

        return ids[ids.length - 1] + 1;
    }
}
