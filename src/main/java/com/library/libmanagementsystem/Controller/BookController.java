package com.library.libmanagementsystem.Controller;

import com.library.libmanagementsystem.Service.IBookService;
import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import com.library.libmanagementsystem.payload.request.BookSearchRequest;
import com.library.libmanagementsystem.payload.response.ApiResponse;
import com.library.libmanagementsystem.payload.response.BookStatsResponse;
import com.library.libmanagementsystem.payload.response.PageResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final IBookService bookService;


    @PostMapping("/bulk")
    public ResponseEntity<List<BookDTO>>createBooksInBulks( @Valid @RequestBody List<BookDTO> bookDTOs) throws BookException {
         List<BookDTO> createdBooks =bookService.createBooksInBulk(bookDTOs);
        return new ResponseEntity<>(createdBooks, HttpStatus.OK);
//        return ResponseEntity.ok(books);

    }
    @GetMapping("/{bookId}")
    public ResponseEntity<?> searchBookById( @PathVariable("bookId") Long bookId) throws BookException {
        BookDTO searchedBook=bookService.getBookById(bookId);
        return new ResponseEntity<>(searchedBook,HttpStatus.OK);
    }

    @GetMapping("/isbn/{iSBN}")
    public ResponseEntity<BookDTO> searchBookByIsbn( @PathVariable("iSBN") String iSBN) throws BookException {
        BookDTO searchedBook=bookService.getBookByISBN(iSBN);
        return new ResponseEntity<>(searchedBook,HttpStatus.OK);
    }

    // now most important Part Update Book
    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) throws BookException {
            BookDTO updatedBook=bookService.updateBook(id,bookDTO);
                return ResponseEntity.ok(updatedBook);
    }
    // this is soft delete
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> softDeleteBook(@PathVariable("id") Long bookId) throws BookException {
        bookService.softDeleteBook(bookId);
        ApiResponse apiResponse=new ApiResponse("This book is soft deleted from the database",true);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("{id}/permanent")
    public ResponseEntity<ApiResponse> hardDeleteBook(@PathVariable("id") Long bookId) throws BookException {
        bookService.hardDeleteBook(bookId);
        ApiResponse apiResponse=new ApiResponse("This book is permanently deleted from the database",true);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/search")
    public ResponseEntity<PageResponse<BookDTO>> advancedSearch(@RequestBody BookSearchRequest searchRequest){
        PageResponse<BookDTO> books=bookService.searchBooksWithFilters(searchRequest);
        return ResponseEntity.ok(books);
    }

    @GetMapping("stats")
    public ResponseEntity<BookStatsResponse> getBookStats(){
        long totalActive=bookService.getTotalActiveBooks();
        long totalAvailable=bookService.getTotalAvailableBooks();

        BookStatsResponse stats=new BookStatsResponse(totalActive,totalAvailable);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> books =bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/AllActive")
    public ResponseEntity<List<BookDTO>> getAllActiveBooks(){
        List<BookDTO> books =bookService.getAllActiveBooks();
        return ResponseEntity.ok(books);
    }

}
