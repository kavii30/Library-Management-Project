package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Service.IBookService;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public List<BookDTO> createBooksInBulk(List<BookDTO> bookDTO) {
        return List.of();
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        return null;
    }

    @Override
    public BookDTO getBookByISBN(String isbn) {
        return null;
    }

    @Override
    public BookDTO updateBook() {
        return null;
    }

    @Override
    public void softDeleteBook() {

    }

    @Override
    public void hardDeleteBook() {

    }

    @Override
    public PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest) {
        return null;
    }

    @Override
    public long getTotalActiveBooks() {
        return 0;
    }

    @Override
    public long getTotalAvailableBooks() {
        return 0;
    }
}
