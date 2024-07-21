package com.assignment.Book_List.service;

import java.util.List;

import com.assignment.Book_List.entity.Book;

public interface BookService {
	Book addBook(Book book);
	List<Book> getAllBooks();
	Book getBookById(Long id);
	List<Book> getAllBooksByAuthor(String author);
	Book updateBookPriceById(Long id,Double newPrice);
	void deleteBookById(Long id);
}
