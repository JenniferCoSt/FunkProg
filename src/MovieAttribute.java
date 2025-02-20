import java.util.stream.Stream;

@FunctionalInterface
public interface MovieAttribute {
    Stream<String> getStream(Movie movie);
}
