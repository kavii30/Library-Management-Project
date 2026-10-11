package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.payload.dto.BookDTO;

import java.util.List;

public interface IBookService {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> createBooksInBulk(List<BookDTO> bookDTO);

    BookDTO getBookById(Long bookId);

    BookDTO getBookByISBN(String isbn);

    BookDTO updateBook();

    void softDeleteBook();

    void hardDeleteBook();
}
