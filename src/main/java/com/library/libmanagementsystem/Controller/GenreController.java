package com.library.libmanagementsystem.Controller;

import com.library.libmanagementsystem.Service.IGenreService;
import com.library.libmanagementsystem.exception.GenreException;
import com.library.libmanagementsystem.model.Genre;
import com.library.libmanagementsystem.payload.dto.GenreDTO;
import com.library.libmanagementsystem.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final IGenreService genreService;
    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
    }

    @GetMapping()
    public ResponseEntity<?> getAllGenre() {
       List<GenreDTO > genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("genreId") Long genreId)
            throws GenreException {
        GenreDTO genres = genreService.getGenreById( genreId);
        return ResponseEntity.ok(genres);
    }
    @PutMapping("/{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("genreId") Long genreId,@RequestBody GenreDTO dto)
            throws GenreException {
        GenreDTO genres = genreService.updateGenre( genreId,dto);
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<ApiResponse> softDeleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
         genreService.deleteGenre(genreId);
         ApiResponse response=new ApiResponse("The genre is soft deleted from the database",true);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{genreId}/hard")
    public ResponseEntity<ApiResponse> hardDeleteGenre(@PathVariable("genreId") Long genreId)
            throws GenreException {
        genreService.hardDelete(genreId);
        ApiResponse response = new ApiResponse("The genre is hard deleted from the database", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<List<GenreDTO>> getTopLevelGenre() {
        List<GenreDTO> genreDTO=genreService.getTopLevelGenres();
        return ResponseEntity.ok(genreDTO);

    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalActiveGenre() {
        Long count=genreService.getTotalActiveGenres();
        return ResponseEntity.ok(count);

    }

    @GetMapping("/{id}/book-count")
    public ResponseEntity<Long> getBookCountByGenre(@PathVariable("id") Long id) {
        Long count=genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(count);

    }

}
