import java.util.*;

/**
 * Represents a sequence of movies to watch in FIFO order.
 */
public class WatchList implements Bingeable<Movie> {
	
	private final List<Movie> aMovies = new LinkedList<>();
	// Added the feature to enable a Watchlist of Episodes to Part 3
	private final List<Episode> aEpisodes = new LinkedList<>();
	private String aName;


	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 */
	 //            the name of the list
	public WatchList(String pName) {
		aName = pName;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 */
	public void setName(String pName) {
		aName = pName;
	}
	
	/**
	 * Adds a movie at the end of this watchlist.
	 * Same version for Episodes
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aMovies.add(pMovie);
	}
	public void addEpisode(Episode pEpisode){ aEpisodes.add(pEpisode);}
	
	/**
	 * Retrieves and removes the next movie/episode to watch from this watchlist. Retrieved in FIFO order.
	 */
	public Movie removeNextMovie() {
		return aMovies.remove(0);
	}
	public Episode removeNextEpisode(){
		return aEpisodes.remove(0);
	}

	/**
	 * Retrieves the list of movies (valid and invalid) in this watchlist.
	 * 
	 * @return an unmodifiable list of movies, backed by this watchlist
	 */
	public List<Movie> getMovies() {
		return Collections.unmodifiableList(aMovies);
	}
	public List<Episode> getEpisodes(){
		return Collections.unmodifiableList(aEpisodes);
	}
	/**
	 * Counts and returns the number of valid movies in this watchlist.
	 * 
	 * @return the number of valid movies
	 */
	public int getNumberMovies() {
		int count = 0;
		for (Movie movie : aMovies) {
			if (movie.isValid()) {
				count++;
			}
		}
		return count;
	}
	/**
	 * Method to check if a WatchList contains a movie/episode
	 */
	public boolean contains(Movie pMovie){
		return aMovies.contains(pMovie);
	}
	public boolean contains(Episode pEpisode){
		return aEpisodes.contains(pEpisode);
	}

	/**
	 * Retrieves the list of all publishing studios, without duplicates, but including studios of invalid movies.
	 * 
	 * @return a set of studios
	 */
	public Set<String> getStudios() {
		Set<String> studios = new HashSet<>();
		for (Movie movie : aMovies) {
			studios.add(movie.getStudio());
		}
		return studios;
	}
	
	/**
	 * Retrieves the list of all languages, without duplicates, but including languages of invalid movies.
	 * 
	 * @return a set of languages
	 */
	public Set<String> getLanguages() {
		Set<String> languages = new HashSet<>();
		for (Movie movie : aMovies) {
			languages.add(movie.getLanguage());
		}
		return languages;
	}

	//Variables to determine if a watchlist has been watched
	private boolean viewed = false;
	//Variable to determine the watch time of a watchlist in minutes (invented for the sake of providing a method in the interface)
	private int aWatchTime;


	/**
	 * Watchable interface methods
	 */
	public void setWatchTime(int pWatchTime){
		aWatchTime = pWatchTime;
	}
	public int getWatchTime(){
		if (aWatchTime == 0) {
			throw new IllegalArgumentException("Watch time information not available");
		}
		return aWatchTime;
	}
	public void setViewedStatus(boolean pViewed){
		viewed = pViewed;
	}
	public boolean isWatched(){
		return viewed;
	}

	/**
	 * Bingeable interface methods
	 */
	public List<Movie> remaining(){
		List<Movie> notWatched = new ArrayList<>();

		for(Movie element : aMovies){
			if(!element.isWatched()){
				notWatched.add(element);
			}
		}
		return notWatched;
	}
	public int size(){
		int count = 0;
		for(Movie element : aMovies){
			count++;
		}
		return count;
	}

	/**
	 * Iterable interface methods
	 * provides access to the Movie elements of a WatchList object
	 */
	@Override
	public Iterator<Movie> iterator(){
		return aMovies.iterator();
	}
}
