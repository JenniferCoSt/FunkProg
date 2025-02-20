import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MongoDBAtlasDownloadExample {
    MovieOperations movieOperations;

    public MongoDBAtlasDownloadExample() {

        //Skriv in rätt url!
        String uri = "mongodb+srv://tempUser:SecretPassword@cluster0.bqhzs.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            //Skriver ut alla filmer
            //for (Movie movie : movieList) {
            //System.out.println(movie);
            //}

            movieOperations = new MovieOperations(movieList);
            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften
            System.out.println();
            System.out.println("Amount of movies made 1975: " + movieOperations.countMoviesFromYearIn(1975));
            //System.out.println("Amount of movies made 1975: " + movieOperations.countMoviesFromYearOut(1975));
            System.out.println("Runtime of longest movie: " + movieOperations.findLongestMovie());
            System.out.println("Amount of unique genres 1975: " + movieOperations.amountOfGenresYear(1975));
            System.out.println("Cast of highest rated movie: " +
                    movieOperations.findActorsInHighestRatedMovie().stream().collect(Collectors.joining(", ")));
            System.out.println("Movie with the smallest cast: " + movieOperations.movieTitleWithLeastActors());
            System.out.println("Amount of actors in several movies: " + movieOperations.amountOfActorsParticipatingInSeveralMovies());
            System.out.println("Actor in most movies: " + movieOperations.actorPlayingInMostMovies());
            System.out.println("Amount of unique languages: " + movieOperations.amountOfLanguages());
            System.out.println("Are there several movies with same title: " +
                    returnAnswer(movieOperations.areThereSeveralMoviesWithSameTitle()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String returnAnswer(boolean movieBool) {
        if (movieBool) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
