package com.library.libmanagementsystem.mapper;

import com.library.libmanagementsystem.Repository.BooksRepository;
import com.library.libmanagementsystem.Repository.GenreRepository;
import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.model.Book;
import com.library.libmanagementsystem.model.Genre;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final BooksRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookDTO toDTO(Book book){
        if(book==null){
            return null;
        }
        return BookDTO.builder()
               .id(book.getId())
               .title(book.getTitle())
               .isbn((book.getIsbn()))
               .genreId(book.getGenre().getId())
               .genreName(book.getGenre().getName())
               .genreCode(book.getGenre().getCode())
               .author(book.getAuthor())
               .publisher(book.getPublisher())
               .language(book.getLanguage())
               .pages(book.getPages())
               .description(book.getDescription())
               .active(book.getActive())
               .totalCopies(book.getTotalCopies())
               .availableCopies(book.getAvailableCopies())
               .price(book.getPrice())
               .coverImageUrl(book.getCoverImageUrl())
               .createdAt(book.getCreatedAt())
               .updatedAt(book.getUpdatedAt())
               .build();

    }

    public Book toEntity(BookDTO dto) throws BookException {
        if(dto==null){
            return null;
        }
        Book book = new Book();
        book.setId(dto.getId());
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        if(dto.getGenreId()!=null){
            Genre genre= genreRepository.findById(dto.getGenreId())
                    .orElseThrow(()->new BookException("Genre with Id "+dto.getGenreId() + "not found "));
            book.setGenre(genre);
        }
        book.setPublisher(dto.getPublisher());
        book.setPublishedDate(dto.getPublicationDate());
        book.setLanguage(dto.getLanguage());
        book.setPages(dto.getPages());
        book.setDescription(dto.getDescription());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPrice(dto.getPrice());
        book.setCoverImageUrl(dto.getCoverImageUrl());
        book.setActive(true); // default to active

        return book;

    }

    public void updateBook(BookDTO dto,Book existingbook) throws BookException {
        if(existingbook==null || dto==null) {
            return ;
        }
        existingbook.setTitle(dto.getTitle());
        existingbook.setAuthor(dto.getAuthor());
        if(dto.getGenreId()!=null){
            Genre genre=genreRepository.findById(dto.getGenreId())
                    .orElseThrow(()->new BookException("Genre with this id " +dto.getGenreId()+ " not available"));
            existingbook.setGenre(genre);
        }
        existingbook.setPublisher(dto.getPublisher());
        existingbook.setPublishedDate(dto.getPublicationDate());
        existingbook.setLanguage(dto.getLanguage());
        existingbook.setPages(dto.getPages());
        existingbook.setDescription(dto.getDescription());
        existingbook.setTotalCopies(dto.getTotalCopies());
        existingbook.setAvailableCopies(dto.getAvailableCopies());
        existingbook.setPrice(dto.getPrice());
        existingbook.setCoverImageUrl(dto.getCoverImageUrl());
        if(dto.getActive()!=null){
            existingbook.setActive(dto.getActive());
        }

    }
}
