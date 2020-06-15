
package dto;

import java.util.List;

/**
 *
 * @author cahit
 */
public class MovieInfoDTO {
    
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private MovieImdbDTO imdb;
    private MetacriticsDTO metacritic;
    private RottenTomatoesDTO tomatoes;
    
   

   
    public MovieInfoDTO() {}

    public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
    }
    
    
        public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
    }
        
        public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast, String poster, MovieImdbDTO imdb) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
        this.imdb = imdb;
    }

    public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast, String poster, MovieImdbDTO imdb, MetacriticsDTO metacritic, RottenTomatoesDTO tomatoes) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
        this.imdb = imdb;
        this.metacritic = metacritic;
        this.tomatoes = tomatoes;
    }  

    public MovieImdbDTO getImdb() {
        return imdb;
    }

    public MetacriticsDTO getMetacritic() {
        return metacritic;
    }

    public RottenTomatoesDTO getTomatoes() {
        return tomatoes;
    }
 


    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirectors() {
        return directors;
    }

    public String getGenres() {
        return genres;
    }

      public String getCast() {
        return cast;
    }
   

}

