package com.library.libmanagementsystem.Controller;

import com.library.libmanagementsystem.Service.IBookService;
import com.library.libmanagementsystem.exception.BookException;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/create")
public class AdminBookController {
    private final IBookService bookService;

    @PostMapping()// those user who have role admin can create books
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO books =bookService.createBook(bookDTO);
        return new ResponseEntity<>(books, HttpStatus.OK);
//        return ResponseEntity.ok(books);

    }
}
