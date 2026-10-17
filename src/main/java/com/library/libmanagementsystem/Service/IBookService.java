package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.PageResponse;

import java.util.List;

public interface IBookService {

    BookDTO createBook(BookDTO bookDTO) throws BookException;

    List<BookDTO> createBooksInBulk(List<BookDTO> bookDTO) throws BookException;

    BookDTO getBookById(Long bookId) throws BookException;

    BookDTO getBookByISBN(String isbn) throws BookException;

    BookDTO updateBook(Long bookId , BookDTO bookDTO);

    void softDeleteBook();

    void hardDeleteBook();

    PageResponse<BookDTO> searchBooksWithFilters(
            BookSearchRequest searchRequest );
    // ye filter hai jaise samaan kharid te wakt flipkart mai filter lgate hain waise hi book search krte wakt ka filter

    long getTotalActiveBooks();

    long getTotalAvailableBooks();
}
