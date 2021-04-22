package main;

import java.util.ArrayList;

public interface FilmInterface {
	public Film getFilm(int FilmID);

	public void insertFilm(Film finfo);

	public void updateFilm(Film finfo);

	public void deleteFilm(int FilmID);

	public ArrayList<Film> listFilm();

	public ArrayList<Film> retrieveFilm(String searchStr);

	int getNextId();

}

