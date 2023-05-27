package com.test.serti.babel.service;

import com.test.serti.babel.models.entity.Location;

public interface ILocationService {

    public void save(Location location);
    public Location  findOne(Long id);
    public void delete(Long id);
    
}
