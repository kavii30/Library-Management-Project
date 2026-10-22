package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.BooksRepository;
import com.library.libmanagementsystem.Service.IBookService;
import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.mapper.BookMapper;
import com.library.libmanagementsystem.mapper.GenreMapper;
import com.library.libmanagementsystem.model.Book;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final BooksRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;

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
    public BookDTO updateBook(Long bookId, BookDTO updatedbookDTO) throws BookException {
       Book existingbook=bookRepository.findById(bookId)
               .orElseThrow(()->new BookException("Kindly add valid BookId"));

        bookMapper.updateEntityFromDTO(updatedbookDTO,existingbook);
        existingbook.isAvailableCopiesValid();
          Book savedBook =bookRepository.save(existingbook);
          return bookMapper.toDTO(savedBook);

    }

    @Override
    public void softDeleteBook(Long bookId) throws BookException {
        Book book=bookRepository.findById(bookId)
                .orElseThrow(()->new BookException("Kindly add valid BookId"));

        book.setActive(false);
        bookRepository.save(book);

    }

    @Override
    public void hardDeleteBook(Long bookId) throws BookException {
        Book existingbook=bookRepository.findById(bookId)
                .orElseThrow(()->new BookException("Please enter valid bookId"));

        bookRepository.delete(existingbook);

    }

    @Override
    public PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest) {
        Pageable pageable=createPageable(searchRequest.getPage(),
                searchRequest.getSize(),
                searchRequest.getSortBy(),
                searchRequest.getSortDirection());
        Page<Book> bookPage = bookRepository.searchBooksWithFilter(
                searchRequest.getSearchTerm(),
                searchRequest.getGenreId(),
                searchRequest.getAvailableOnly(),
                pageable
        );

        return convertToPageResponse(bookPage);
    }

    @Override
    public long getTotalActiveBooks() {
        return bookRepository.countByActiveTrue();
    }

    @Override
    public long getTotalAvailableBooks() {
        return bookRepository.countAvailableBooks();
    }

    private Pageable createPageable(int page, int size,String sortBy,String sortDirection){
        size=Math.min(size,10);
        size=Math.max(size,1);

        Sort sort=sortDirection.equalsIgnoreCase("ASC")
                ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        return PageRequest.of(page,size,sort);
    }

    private PageResponse<BookDTO> convertToPageResponse(Page<Book> books){
        List<BookDTO> bookDTOs=books.getContent()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(bookDTOs,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst(),
                books.isEmpty()
        );

    }
}
