
package dto;

/**
 *
 * @author cahit
 */
public class CriticsDTO {
    
       private int rating;
    private int numReviews;
    private int meter;

    public CriticsDTO() {}

    public CriticsDTO(int rating, int numReviews, int meter) {
        this.rating = rating;
        this.numReviews = numReviews;
        this.meter = meter;
    }

    

    public int getRating() {
        return rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public int getMeter() {
        return meter;
    }
    
    
}
