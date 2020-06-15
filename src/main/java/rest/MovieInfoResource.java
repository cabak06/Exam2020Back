/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dto.MetacriticsDTO;
import dto.MovieInfoDTO;
import dto.MovieRatingDTO;
import dto.PosterDTO;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author cahit
 */
@Path("")
public class MovieInfoResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private final static Gson GSON = new Gson();

    public MovieInfoResource() {
    }

    /**
     * Retrieves representation of an instance of rest.MovieInfoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MovieInfoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }


 @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movie-info/{title}")  
    public MovieInfoDTO getMovieTitle(@PathParam("title") String title) throws IOException {
        
    String url = "https://ex2-0-2-0.mydemos.dk/movieinfo/" + title;
    String url2 = "https://ex2-0-2-0.mydemos.dk/moviePoster/" + title;
    String fetchMovieTitle = url;
    String fetchMovieTitle2 = url2;
    
    String data = HttpUtils.fetchData(fetchMovieTitle);
    String data2 = HttpUtils.fetchData(fetchMovieTitle2);
    
    MovieInfoDTO movie = GSON.fromJson(data, MovieInfoDTO.class);
    PosterDTO poster = GSON.fromJson(data2, PosterDTO.class);
    
    return new MovieInfoDTO(movie.getTitle(),movie.getYear(),movie.getPlot(),movie.getDirectors(),movie.getGenres()
          ,movie.getCast(),poster.getPoster());
   
   
    }
  
 
   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movie-info-imdb/{title}")
    @RolesAllowed("user")
    public MovieInfoDTO getMovieWithIMDB(@PathParam("title") String title) throws IOException {
        
    String url = "https://ex2-0-2-0.mydemos.dk/movieinfo/" + title;
    String url2 = "https://ex2-0-2-0.mydemos.dk/moviePoster/" + title;
    String url3 = "https://ex2-0-2-0.mydemos.dk/ratings/" + title + "/" + "i";
    String fetchMovieTitle = url;
    String fetchMovieTitlePoster = url2;
    String fetchMovieIMDB = url3;
    
    String data = HttpUtils.fetchData(fetchMovieTitle);
    String data2 = HttpUtils.fetchData(fetchMovieTitlePoster);
    String data3 = HttpUtils.fetchData(fetchMovieIMDB);
    
    MovieInfoDTO movie = GSON.fromJson(data, MovieInfoDTO.class);
    PosterDTO poster = GSON.fromJson(data2, PosterDTO.class);
    MovieRatingDTO movieWithRating = GSON.fromJson(data3, MovieRatingDTO.class);

    return new MovieInfoDTO(movie.getTitle(),movie.getYear(),movie.getPlot(),movie.getDirectors(),movie.getGenres()
          ,movie.getCast(),poster.getPoster(), movieWithRating.getImdb());

    
    }
  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movie-info-all-ratings/{title}")
    @RolesAllowed("user")
    public MovieInfoDTO getMovieWithAllRatings(@PathParam("title") String title) throws IOException {
        
    String url = "https://ex2-0-2-0.mydemos.dk/movieinfo/" + title;
    String url2 = "https://ex2-0-2-0.mydemos.dk/moviePoster/" + title;
    String url3 = "https://ex2-0-2-0.mydemos.dk/ratings/" + title + "/" + "imt";
      
    String fetchMovieTitle = url;
    String fetchMovieTitlePoster = url2;
    String fetchMovieIMDB = url3;
      
    String data = HttpUtils.fetchData(fetchMovieTitle);
    String data2 = HttpUtils.fetchData(fetchMovieTitlePoster);
    String data3 = HttpUtils.fetchData(fetchMovieIMDB);
    
    MovieInfoDTO movie = GSON.fromJson(data, MovieInfoDTO.class);
    PosterDTO poster = GSON.fromJson(data2, PosterDTO.class);
    MovieRatingDTO movieWithRating = GSON.fromJson(data3, MovieRatingDTO.class);
   
    return new MovieInfoDTO(movie.getTitle(),movie.getYear(),movie.getPlot(),movie.getDirectors(),movie.getGenres()
          ,movie.getCast(),poster.getPoster(),movieWithRating.getImdb(),movieWithRating.getMetacritics(), movieWithRating.getTomatoes());
   
   
    }
    
    
    
   

}
