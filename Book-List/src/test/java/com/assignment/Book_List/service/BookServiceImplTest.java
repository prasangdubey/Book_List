package com.assignment.Book_List.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.Book_List.entity.Book;
import com.assignment.Book_List.exception.BookNotFoundException;
import com.assignment.Book_List.repository.BookRepository;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	
	@Mock
	private BookRepository bookRepository;
	
	private Book book1;
	private Book book2;
	
	@BeforeEach
	public void setup() throws ParseException {
		book1=new Book(1L,"Prasang's Book","Prasang",new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-21"),"9780123456789",99.99);
		book2=new Book(2L,"Omkar Book","Omkar",new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-21"),"9780123456789",99.99);
	}
	
	@Test
	public void addBook() {
		when(bookRepository.save(any(Book.class))).thenReturn(book1);
		
		Book savedBook=bookServiceImpl.addBook(book1);
		assertEquals(book1.getTitle(),savedBook.getTitle());
		assertEquals(book1.getAuthor(), savedBook.getAuthor());
		assertEquals(book1.getIsbn(), savedBook.getIsbn());
		assertEquals(book1.getPrice(), savedBook.getPrice());

	}
	
	@Test
    public void getAllBooks() {
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> retrievedBooks = bookServiceImpl.getAllBooks();

        assertEquals(2, retrievedBooks.size());
    }

    @Test
    public void getBookById() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book1));

        Book retrievedBook = bookServiceImpl.getBookById(1L);

        assertNotNull(retrievedBook);
        assertEquals(book1.getTitle(), retrievedBook.getTitle());
        
    }

    @Test
    public void updateBookPriceById() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        Book updatedBook = bookServiceImpl.updateBookPriceById(1L, 9.99);

        assertNotNull(updatedBook);
        assertEquals(9.99, updatedBook.getPrice());
    }
    
    @Test
    public void getBookById_NotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> {
            bookServiceImpl.getBookById(1L);
        });
    }

    @Test
    public void deleteBookById() {
        doNothing().when(bookRepository).deleteById(anyLong());

        bookServiceImpl.deleteBookById(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
    
    
}

