package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.exception.GenreException;
import com.library.libmanagementsystem.model.Genre;
import com.library.libmanagementsystem.payload.dto.GenreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenreService {
    GenreDTO createGenre (GenreDTO genre);

    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Long genreId) throws GenreException;

    GenreDTO updateGenre(Long genreId, GenreDTO dto) throws GenreException;

    void deleteGenre(Long genreId) throws GenreException;

    void hardDelete(Long genreId) throws GenreException;

    List<GenreDTO> getAllActiveGenresWithSubGenres();

    List<GenreDTO> getTopLevelGenres();

   // Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);

    long getTotalActiveGenres();

    long getBookCountByGenre(Long genreId);
}
