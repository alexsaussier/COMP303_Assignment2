import java.util.*;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library implements WatchListGenerator{
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<TvShow> aTvShow = new HashSet<>();
	
	// Optional
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aMovies.add(pMovie);
	}
	
	// Optional
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 */
	public void addWatchList(WatchList pList) {
		aWatchLists.add(pList);
		for (Movie movie : pList.getMovies()) {
			addMovie(movie);
		}
	}
	public void addTvShow(TvShow pTvShow){
		aTvShow.add(pTvShow);
	}

	//Retrieves movies that aren't in a watchlist
	public Set<Movie> getIndividualMovies(){
		return Collections.unmodifiableSet(aMovies);
	}

	//Retrieves all the movies
	public Set<Movie> getMovies(){
		Set<Movie> aSet = new HashSet<>();
		aSet.addAll(aMovies);
		for(WatchList watchlist : aWatchLists){
			for(Movie movie : watchlist){
				if(!aSet.contains(movie)){
					aSet.add(movie);
				}
			}
		}
		return aSet;
	}

	/**
	 * 2 algorithms from WatchListGenerator interface
	 */
	public WatchList englishFromStudios(Set<String> studios, String language){
		WatchList english = new WatchList("All the English movies from " + studios.toString());
		//Check for the movies that don"t belong to a watchlist.
		for(Movie movie : aMovies){
			if(movie.getLanguage() == language && studios.contains(movie.getStudio())){
				english.addMovie(movie);
			}
		}
		//Now check for all the movies that belong to a watchlist. Avoids duplicates.
		for(WatchList watchList : aWatchLists){
			for(Movie movie : watchList){
				if(movie.getLanguage() == language && studios.contains(movie.getStudio()) && !english.contains(movie)){
					english.addMovie(movie);
				}
			}
		}
		return english;
	}

	public WatchList episodesFromShow(TvShow aShow){
		WatchList random10 = new WatchList("10 random episodes from " + aShow.getName());
		List<Episode> tvShowEpisodes = aShow.getEpisodes();

		//if there are 10 episodes or less, add all the episodes in the watchlist.
		if (aShow.size() <= 10){
			for(Episode episode : aShow){
				random10.addEpisode(episode);
			}

		}
		for(int i = 0; i < 10;){
			//Generates a random number and retrieves the episode at this rank of the List
			int randNum = (int)(Math.round(Math.random() * (aShow.size() - 1)));
			Episode episodeToAdd = tvShowEpisodes.get(randNum);
			if(!random10.contains(episodeToAdd)){
				random10.addEpisode(tvShowEpisodes.get(randNum));
				i++;
			}
		}
		return random10;
	}
}
