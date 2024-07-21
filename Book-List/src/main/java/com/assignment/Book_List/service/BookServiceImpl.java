package com.assignment.Book_List.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Book_List.entity.Book;
import com.assignment.Book_List.repository.BookRepository;
import com.assignment.Book_List.exception.*;

@Service
public class BookServiceImpl implements BookService{

	private final BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository=bookRepository;
	}
	
	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	@Override
	public Book getBookById(Long id) {
		Optional<Book> book= bookRepository.findById(id);
		if(book.isPresent())
			return book.get();
		else
			throw new BookNotFoundException("Book with id:"+id+" not found.") ;
	}
	
	@Override
	public List<Book> getAllBooksByAuthor(String author){
		return bookRepository.findAlBooksByAuthor(author);
	}
	
	@Override
	public Book updateBookPriceById(Long id,Double newPrice){
		Book book=getBookById(id);
		book.setPrice(newPrice);
		return bookRepository.save(book);
	}
	
	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
}
