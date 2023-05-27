package com.test.serti.babel.service;

import java.util.List;

import com.test.serti.babel.models.entity.Book;

public interface IBookService {
    
    public List<Book> findAll();
    public void save(Book book);
    public Book findOne(Long id);
    public void delete(Long id);
   public boolean existsByVolumeNumber(Long volumeNumber);
}

