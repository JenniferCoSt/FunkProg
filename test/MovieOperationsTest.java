import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieOperationsTest {

    Movie m1 = new Movie("m1", "The Green Adventure", 1975, List.of("Action", "Adventure"), "Derek Tour", List.of("Gemma Giraff", "Tom Tiger", "Alina Antilop"), 8.5, List.of("English", "French"), 180);
    Movie m2 = new Movie("m2", "Cheesiest Love Story Ever", 1975, List.of("Romance", "Drama"), "Dede Tee", List.of("Tom Tiger", "Isak Igelkott"), 7.5, List.of("English"), 120);
    Movie m3 = new Movie("m3", "The House of Laban", 1975, List.of("Mystery", "Horror"), "Diana Torres", List.of("Aya Anka"), 6.8, List.of("English", "German"), 150);
    Movie m4 = new Movie("m4", "Everything is a joke", 1975, List.of("Comedy"), "Dylan Tivoli", List.of("Gemma Giraff", "Lionel Lejon", "Kicki Krokodil"), 7.2, List.of("English", "Spanish"), 90);
    Movie m5 = new Movie("m5", "Space Wars", 1975, List.of("Sci-fi", "Action"), "Daniel Thompson", List.of("Theo Tarantula", "Amaya Apa", "Bob Björn", "Tom Tiger"), 9.2, List.of("English", "Japanese"), 200);
    Movie m6 = new Movie("m6", "The Green Adventure: It's a lime!", 1976, List.of("Action", "Adventure"), "Derek Tour", List.of("Aya Anka", "Kicki Krokodil"), 8.0, List.of("English", "French"), 165);
    Movie m7 = new Movie("m7", "Western One", 1975, List.of("Western", "Drama"), "Tara Douglas", List.of("Turid Tvättbjörn", "Valdemar Velociraptor", "Tom Tiger"), 7.8, List.of("English"), 145);
    Movie m8 = new Movie("m8", "The Green Adventure", 1977, List.of("Action", "Adventure", "Tragedy"), "Derek Tour", List.of("Gemma Giraff", "Tom Tiger", "Frank Fisk"), 8.3, List.of("English", "French", "Spanish"), 170);
    Movie m9 = new Movie("m9", "Criminal Coconut", 1975, List.of("Crime", "Drama"), "Thomas Dean", List.of("Aya Anka", "Noah Noshörning"), 8.1, List.of("English", "Italian"), 160);
    Movie m10 = new Movie("m10", "Sounds like a musical", 1975, List.of("Musical", "Romance"), "Dayra Ekthorn", List.of("Lionel Lejon", "Tom Tiger", "Alina Antilop"), 7.6, List.of("English", "French"), 130);

    List<Movie> testList = List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10);
    MovieOperations mo = new MovieOperations(testList);

    @Test
    public void countMoviesFromYearInTest() {
        assertEquals(8, mo.countMoviesFromYearIn(1975));
        assertEquals(1, mo.countMoviesFromYearIn(1977));
        assertNotEquals(10, mo.countMoviesFromYearIn(1975));
    }

    @Test
    public void countMoviesFromYearOutTest() {
        assertEquals(8, mo.countMoviesFromYearOut(1975));
        assertNotEquals(10, mo.countMoviesFromYearOut(1975));
    }

    @Test
    public void findLongestMovieTest() {
        assertEquals(200, mo.findLongestMovie());
        assertNotEquals(210, mo.findLongestMovie());
    }

    @Test
    public void amountOfGenresYearTest() {
        assertEquals(11, mo.amountOfGenresYear(1975));
        assertEquals(3, mo.amountOfGenresYear(1977));
        assertNotEquals(12, mo.amountOfGenresYear(1975));
    }

    @Test
    public void findActorsInHighestRatedMovieTest() {
        List<String> actors = List.of("Theo Tarantula", "Amaya Apa", "Bob Björn", "Tom Tiger");
        assertEquals(actors, mo.findActorsInHighestRatedMovie());
        assertTrue(mo.findActorsInHighestRatedMovie().contains("Theo Tarantula"));
        assertTrue(mo.findActorsInHighestRatedMovie().contains("Amaya Apa"));
        assertTrue(mo.findActorsInHighestRatedMovie().contains("Bob Björn"));
        assertTrue(mo.findActorsInHighestRatedMovie().contains("Tom Tiger"));
        assertFalse(mo.findActorsInHighestRatedMovie().contains("Gemma Giraff"));
    }

    @Test
    public void movieTitleWithLeastActorsTest() {
        assertEquals("The House of Laban", mo.movieTitleWithLeastActors());
        assertNotEquals("Western One", mo.movieTitleWithLeastActors());
    }

    @Test
    public void amountOfActorsParticipatingInSeveralMoviesTest() {
        assertEquals(6, mo.amountOfActorsParticipatingInSeveralMovies());
        assertNotEquals(10, mo.amountOfActorsParticipatingInSeveralMovies());
    }

    @Test
    public void actorPlayingInMostMoviesTest() {
        assertEquals("Tom Tiger", mo.actorPlayingInMostMovies());
        assertNotEquals("Gemma Giraff", mo.actorPlayingInMostMovies());
    }

    @Test
    public void amountOfLanguagesTest() {
        assertEquals(6, mo.amountOfLanguages());
        assertNotEquals(5, mo.amountOfLanguages());
    }

    @Test
    public void areThereSeveralMoviesWithSameTitleTest() {
        assertTrue(mo.areThereSeveralMoviesWithSameTitle());
        assertFalse(!mo.areThereSeveralMoviesWithSameTitle());
    }
}
