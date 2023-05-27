package com.test.serti.babel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.serti.babel.models.dao.IBookDao;
import com.test.serti.babel.models.entity.Book;

import jakarta.transaction.Transactional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional
    public List<Book> findAll() {
        return (List<Book>) bookDao.findAll();

    }

    @Override
    @Transactional
    public void save(Book book) {
       bookDao.save(book);
     }

    @Override
@Transactional
    public Book findOne(Long id) {
        return bookDao.findById(id).orElse(null);
     }

    @Override
    @Transactional
    public void delete(Long id) {
        
    bookDao.deleteById(id);
    }

    @Override
    public boolean existsByVolumeNumber(Long volumeNumber) {
    
        return bookDao.existsByVolumeNumber(volumeNumber);
    }


   

    

}
