package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.BooksRepository;
import com.library.libmanagementsystem.Service.IBookService;
import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.mapper.BookMapper;
import com.library.libmanagementsystem.model.Book;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final BooksRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    @Transactional(rollbackFor = BookException.class)
    public BookDTO createBook(BookDTO bookDTO) throws BookException {
        if(bookRepository.existsByIsbn(bookDTO.getIsbn())){
            throw new BookException("Book already present with ISBN "+ bookDTO.getIsbn()+ " please enter another book with unique ISBN ");
        }
       Book book =bookMapper.toEntity(bookDTO);
// user can give more available book number then total number we have to restrict that
        book.isAvailableCopiesValid();
         Book newBook =bookRepository.save(book);


        return bookMapper.toDTO(newBook);
    }

    @Override
    public List<BookDTO> createBooksInBulk(List<BookDTO> bookDTOs) throws BookException {
        if(bookDTOs ==null){
            return null;
        }
        List<BookDTO> createdBookslist =new ArrayList<>();
       for(BookDTO bookDTO: bookDTOs){
           BookDTO listbookDTO=createBook(bookDTO);
           createdBookslist.add(listbookDTO);
       }
       return createdBookslist;
    }

    @Override
    public BookDTO getBookById(Long bookId) throws BookException {
          Book book= bookRepository.findById(bookId)
                 .orElseThrow(()-> new BookException("The given BookId "+ bookId + " is not valid. Please give valid BookId") );
           return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getBookByISBN(String isbn) throws BookException {
        Book book= bookRepository.findByIsbn(isbn)
                .orElseThrow(()-> new BookException("The given ISBN "+ isbn + " is not valid. Please give valid isbn ") );
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
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
