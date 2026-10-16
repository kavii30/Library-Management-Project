package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.PageResponse;

import java.util.List;

public interface IBookService {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> createBooksInBulk(List<BookDTO> bookDTO);

    BookDTO getBookById(Long bookId);

    BookDTO getBookByISBN(String isbn);

    BookDTO updateBook();

    void softDeleteBook();

    void hardDeleteBook();

    PageResponse<BookDTO> searchBooksWithFilters(
            BookSearchRequest searchRequest );
    // ye filter hai jaise samaan kharid te wakt flipkart mai filter lgate hain waise hi book search krte wakt ka filter

    long getTotalActiveBooks();

    long getTotalAvailableBooks();
}
