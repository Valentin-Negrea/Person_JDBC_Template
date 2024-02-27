package com.example.seminar322.Repository;


import com.example.seminar322.Domain.Entity;
import com.example.seminar322.RepositoryException;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    public void add(T o) throws RepositoryException;

    public void remove(int id) throws RepositoryException;

    public T find(int id);

    public void update(int id, T entity);

    // program to an interface, not an implementation
    public Collection<T> getAll();
}
