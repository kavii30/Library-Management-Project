package com.library.libmanagementsystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequest {
    private String searchTerm;
    private long genreId;
    private Boolean availableOnly;
    private Integer page=0;
    private Integer size=20;
    private String sortBy="createdAt";
    private String sortDirection="DESC";


}
