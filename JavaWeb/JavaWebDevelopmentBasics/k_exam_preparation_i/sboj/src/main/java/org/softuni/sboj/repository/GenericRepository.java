package org.softuni.sboj.repository;

import java.util.List;

public interface GenericRepository <Entity, Id> {

    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(Id id);
}
