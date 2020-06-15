
package dto;

/**
 *
 * @author cahit
 */
public class MovieImdbDTO {
    
    private String imdbRating;
    private String imdbVotes;
    
    public MovieImdbDTO() {}

    public MovieImdbDTO(String imdbRating, String imdbVotes) {
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }
    
    
    
    
    
    
}
