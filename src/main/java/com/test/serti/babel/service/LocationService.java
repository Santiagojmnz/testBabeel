package com.test.serti.babel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.serti.babel.models.dao.ILocationDao;
import com.test.serti.babel.models.entity.Location;

import jakarta.transaction.Transactional;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private ILocationDao locationDao;

    @Override
    @Transactional
    public void save(Location location) {

        locationDao.save(location);
    }

    @Override
    @Transactional
    public Location findOne(Long id) {
        return locationDao.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public void delete(Long id) {
       locationDao.deleteById(id); 
       
    }

}
