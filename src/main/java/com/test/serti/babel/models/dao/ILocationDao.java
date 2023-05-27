package com.test.serti.babel.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.test.serti.babel.models.entity.Location;

public interface ILocationDao extends CrudRepository<Location,Long> {
    
}
