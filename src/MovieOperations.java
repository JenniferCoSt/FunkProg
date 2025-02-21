import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MovieOperations {

    private final List<Movie> movies;
    private final FilterByYear getYear = (f, y) -> f.getYear() == y;
    private final MovieAttribute getCast = f -> f.getCast().stream();
    private final MovieAttribute getLanguages = f -> f.getLanguages().stream();
    private final MovieAttribute getGenres = f -> f.getGenres().stream();



    public MovieOperations(List<Movie> movies) {
        this.movies = List.copyOf(movies);
    }

    //Högre ordningens funktion
    public boolean filterbyYearIn(Movie movie, int year, FilterByYear fby) {
        return fby.yearFilter(movie, year);
    }

    public FilterByYear filterby1975() {
        return (m, y) -> m.getYear() == 1975;
    }

    public Stream<String> getMovieAttribute(Movie movie, MovieAttribute ma) {
        return ma.getStream(movie);
    }

    //Metoder med svar på frågorna
    public int countMoviesFromYearIn(int inputYear) {
        return (int) movies.stream()
                .filter(m -> filterbyYearIn(m, inputYear, getYear))
                .count();
    }

    public int countMoviesFromYearOut(int inputYear) {
        return (int) movies.stream()
                .filter(m -> filterby1975().yearFilter(m, inputYear))
                .count();
    }

    public int findLongestMovie() {
        return movies.stream()
                .mapToInt(Movie::getRuntime)
                .summaryStatistics()
                .getMax();
    }

    public int amountOfGenresYear(int inputYear) {
        return (int) movies.stream().filter(m -> filterbyYearIn(m, inputYear, getYear))
                .flatMap(m -> getMovieAttribute(m, getGenres))
                .distinct()
                .count();
    }

    public List<String> findActorsInHighestRatedMovie() {
        return movies.stream()
                .max(Comparator.comparing(Movie::getImdbRating))
                .map(Movie::getCast)
                .orElse(List.of("Couldn't find an actor."));
    }

    public String movieTitleWithLeastActors() {
        return movies.stream()
                .min(Comparator.comparing(m -> m.getCast().size()))
                .map(Movie::getTitle)
                .orElse("Couldn't find a movie title.");
    }

    public int amountOfActorsParticipatingInSeveralMovies() {
        Map<String, Long> actorsCountMap = mapActorsWithAmountOfMovies();

        return (int) actorsCountMap.values().stream()
                .filter(v -> v > 1)
                .count();
    }

    public String actorPlayingInMostMovies() {
        Map<String, Long> actorsCountMap = mapActorsWithAmountOfMovies();

        int mostMovies = actorsCountMap.values().stream()
                .mapToInt(v -> v.intValue())
                .summaryStatistics()
                .getMax();

        return actorsCountMap.keySet().stream()
                .filter(k -> actorsCountMap.get(k) == mostMovies)
                .findAny()
                .orElse("Couldn't find an actor");
    }

    public int amountOfLanguages() {
        return (int) movies.stream()
                .flatMap(m -> getMovieAttribute(m, getLanguages))
                .distinct()
                .count();
    }

    public boolean areThereSeveralMoviesWithSameTitle() {
        Map<String, Long> titleCount = movies.stream()
                .collect(groupingBy(Movie::getTitle, counting()));

        return titleCount.values().stream()
                .anyMatch(v -> v > 1);
    }

    public Map<String, Long> mapActorsWithAmountOfMovies() {
        return movies.stream()
                .flatMap(m -> getMovieAttribute(m, getCast))
                .collect(groupingBy(a -> a, counting()));
    }
}
