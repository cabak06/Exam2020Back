/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.MovieInfoDTO;
import entities.RenameMe;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author cahit
 */
public class MovieInfoResourceTest {
    
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static RenameMe r1, r2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }
    
  
    
    @Test
    public void testMovieInfoTitle(){
        String parameter = "alien";
        MovieInfoDTO result = given()
        .contentType("application/json")
            .accept(ContentType.JSON)
            .when()
            .get("/movie-info/"+ parameter).then()
            .statusCode(200)   
            .extract().body().as(MovieInfoDTO.class);
             assertTrue(result.getTitle().equals("Alien")); 
             assertTrue(result.getDirectors().equals("Ridley Scott"));
             assertTrue(result.getGenres().contains("Horror"));
             assertEquals(result.getYear(),1979);
    }
    
    
      //This is how we hold on to the token after login, similar to that a client must store the token somewhere
     private static String securityToken;
   
     private static void login(String role, String password) {
    String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
    securityToken = given()
            .contentType("application/json")
            .body(json)
            .when().post("/login")
            .then()
            .extract().path("token");
      System.out.println("TOKEN ---> "+securityToken);
  }
    
  
    @Test
    public void testMovieInfoIMDB(){
    login("user", "test");
    String parameter = "Forrest Gump";
        MovieInfoDTO result = given()
        .contentType("application/json")
            .accept(ContentType.JSON)
            .header("x-access-token", securityToken)    
            .when()
            .get("/movie-info-imdb/"+parameter)
            .then()
            .statusCode(200)
            .extract().body().as(MovieInfoDTO.class);
             assertTrue(result.getTitle().equals("Forrest Gump")); 
             assertTrue(result.getDirectors().equals("Robert Zemeckis"));
             assertTrue(result.getGenres().contains("Romance"));
             assertEquals(result.getYear(),1994);
             assertTrue(result.getImdb().getImdbRating().equals("8.8"));
             assertTrue(result.getImdb().getImdbVotes().equals("1087227"));
            
    }
   
    
    @Test
     public void test_Negative_Not_Authorized_To_See_MovieInfoIMDB(){
       String parameter = "Forrest Gump";
       MovieInfoDTO result = given()
            .contentType("application/json")
            .accept(ContentType.JSON)  
            .when()
            .get("/movie-info-imdb/"+parameter)
            .then()
            .statusCode(403)
            .extract().body().as(MovieInfoDTO.class);
    }
    
    
     
     @Test
     public void testGetMovieWithAllRatings(){
          login("user", "test");
    String parameter = "superman";
        MovieInfoDTO result = given()
        .contentType("application/json")
            .accept(ContentType.JSON)
            .header("x-access-token", securityToken)    
            .when()
            .get("/movie-info-imdb/"+ parameter)
            .then()
            .statusCode(200)
            .extract().body().as(MovieInfoDTO.class);
             assertTrue(result.getTitle().equals("Superman")); 
             assertTrue(result.getDirectors().equals("Richard Donner"));
             assertTrue(result.getGenres().contains("Action"));
             assertEquals(result.getYear(),1978);
             assertTrue(result.getImdb().getImdbRating().equals("7.3"));
             assertTrue(result.getImdb().getImdbVotes().equals("115208"));
             
     }
     
     @Test
     public void test_Negative_Not_Authorized_To_See_MovieWithAllRatings(){
       String parameter = "superman";
       MovieInfoDTO result = given()
            .contentType("application/json")
            .accept(ContentType.JSON)  
            .when()
            .get("/movie-info-imdb/"+ parameter)
            .then()
            .statusCode(403)
            .extract().body().as(MovieInfoDTO.class);
    }
     
     
     
     
}
