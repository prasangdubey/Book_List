package com.assignment.Book_List.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Book_List.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	List<Book> findAlBooksByAuthor(String author);
}
