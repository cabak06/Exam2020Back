
package dto;


public class MovieRatingDTO {

private String title;
private MovieImdbDTO imdb;
private MetacriticsDTO metacritics;
private RottenTomatoesDTO tomatoes;

   

    public MovieRatingDTO() {}

    public MovieRatingDTO(String title, MovieImdbDTO imdb) {
        this.title = title;
        this.imdb = imdb;
    }

    public MovieRatingDTO(MovieImdbDTO imdb, MetacriticsDTO metacritics, RottenTomatoesDTO tomatoes) {
        this.imdb = imdb;
        this.metacritics = metacritics;
        this.tomatoes = tomatoes;
    }


    
     public MetacriticsDTO getMetacritics() {
        return metacritics;
    }

    public RottenTomatoesDTO getTomatoes() {
        return tomatoes;
    }
    
     

    public String getTitle() {
        return title;
    }

    public MovieImdbDTO getImdb() {
        return imdb;
    }




    
}
