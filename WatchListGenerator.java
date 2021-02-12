import java.util.List;
import java.util.Set;

public interface WatchListGenerator {
    /**
     * Method that creates a watchlist made of all the English movies of an inputed studio.
     * @param studios
     * @return a WatchList object
     */
    public WatchList englishFromStudios(Set<String> studios, String language);

    /**
     * Method that creates a watchlist with 10 random episodes of an inputed TV show.
     * @param aShow - a TV show
     * @return a WatchList object
     */

    public WatchList episodesFromShow(TvShow aShow);
}
