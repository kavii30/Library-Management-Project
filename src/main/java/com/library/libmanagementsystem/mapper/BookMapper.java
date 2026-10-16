package com.library.libmanagementsystem.mapper;

import com.library.libmanagementsystem.model.Book;
import com.library.libmanagementsystem.payload.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    public BookDTO toDTO(Book book){
        if(book==null){
            return null;
        }
        BookDTO dot=BookDTO.builder()

                .build();
        return null;
    }
}
