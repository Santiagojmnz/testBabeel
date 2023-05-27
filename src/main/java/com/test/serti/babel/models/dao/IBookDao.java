package com.test.serti.babel.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.serti.babel.models.entity.Book;

public interface IBookDao extends CrudRepository<Book,Long> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.volumeNumber = :volumeNumber")
    boolean existsByVolumeNumber(@Param("volumeNumber") Long volumeNumber);

}
