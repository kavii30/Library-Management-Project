package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.GenreRepository;
import com.library.libmanagementsystem.Service.IGenreService;
import com.library.libmanagementsystem.exception.GenreException;
import com.library.libmanagementsystem.mapper.GenreMapper;
import com.library.libmanagementsystem.model.Genre;
import com.library.libmanagementsystem.payload.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService implements IGenreService {


    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {

        Genre genre=genreMapper.toEntity(genreDTO);

        Genre savedGenre= genreRepository.save(genre);

        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Long genreId) throws GenreException {
        Genre genre= genreRepository.findById(genreId)
                .orElseThrow(()->new GenreException("genre not found"));
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO dto) throws GenreException {


       Genre existingGenre = genreRepository.findById(genreId)
               .orElseThrow(()->new GenreException("Genre with this id not found"));
       genreMapper.updateEntityFromDTO(dto,existingGenre);
      Genre updatedGenre=genreRepository.save(existingGenre);
      return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException{

        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(()->new GenreException("Genre with this id not found"));

        existingGenre.setActive(false);
        genreRepository.save(existingGenre);

    }

    @Override
    public void hardDelete(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(()->new GenreException("Genre with this id not found"));

        genreRepository.delete(existingGenre);

    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubGenres() {
        List<Genre> topLevelGenres=genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);

    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<Genre> topLevelGenres=genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public long getTotalActiveGenres() {
        return genreRepository.countByActiveTrue();
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return 0;
    }
}
