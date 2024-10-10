package com.example.service;

import com.example.starwarsapp.model.Film;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FilmService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveFilmAsJson(Film film) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(film.getTitle() + ".json"), film);
    }

    public Film parseFilm(String jsonResponse) throws IOException {
        return objectMapper.readValue(jsonResponse, Film.class);
    }
}
