package com.example.seminar322.Repository;


import com.example.seminar322.Domain.Entity;
import com.example.seminar322.DuplicateObjectException;
import com.example.seminar322.Repository.AbstractRepository;
import com.example.seminar322.RepositoryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class MemoryRepository<T extends Entity> extends AbstractRepository<T> {
    @Override
    public void add(T o) throws RepositoryException {
        if (o == null) {
            // este RuntimeException
            throw new IllegalArgumentException();
        }
        if (find(o.getId()) != null) {
            // checked Exception
            throw new DuplicateObjectException("Cannot duplicate repository objects!");
        }

        this.data.add(o);
    }

    @Override
    public void remove(int id) {
        T entity = find(id);
        if (entity != null)
            data.remove(entity);
        else {
            throw new NoSuchElementException("Nu exista element cu id ul acesta");
        }
    }

    @Override
    public T find(int id) {
        for (T o : data) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void update(int id, T entity) {
        T existingEntity = find(id); // Find the existing entity by its ID.

        if (existingEntity != null) { // If the existing entity is found.
            // Remove the existing entity.
            remove(id);

            // Add or update the new entity.
            try {
                add(entity);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        } else {
            // If the entity with the given ID does not exist, throw an exception.
            throw new NoSuchElementException("Element with ID " + id + " does not exist in the repository.");
        }
    }

    @Override
    public Collection<T> getAll() {
        // returnam o copie
        return new ArrayList<>(data);
    }
}
