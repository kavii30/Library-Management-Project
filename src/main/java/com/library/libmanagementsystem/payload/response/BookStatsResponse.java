package com.library.libmanagementsystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStatsResponse {
     private long totalActive;

     private long totalAvailable;


}
