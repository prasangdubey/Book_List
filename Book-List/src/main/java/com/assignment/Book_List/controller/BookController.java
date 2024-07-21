package com.assignment.Book_List.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Book_List.entity.Book;
import com.assignment.Book_List.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}
	
	@PostMapping
	public Book addNewBook(@Valid @RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@GetMapping
	public List<Book> retrieveAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public Book retrieveBookById(@PathVariable Long id) {
		Book book= bookService.getBookById(id);
		if(book==null)
			return null;
		else
			return book;
	}
	
	@GetMapping("/allBooksByAuthor/{author}")
	public List<Book> rerieveAllBooksByAuthor(@PathVariable String author){
		return bookService.getAllBooksByAuthor(author);
	}
	
	@PutMapping("changeBookPriceById/{id}/{newPrice}")
	public Book changeBookPriceById(@PathVariable Long id,@PathVariable Double newPrice) {
		return bookService.updateBookPriceById(id, newPrice);
	}
	
	@DeleteMapping("deleteBookById/{id}")
	public void removeBookById(@PathVariable Long id) {
		bookService.deleteBookById(id);
	}

}
