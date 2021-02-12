import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Represents a single movie, with at least a title, language, and publishing studio. Each movie is identified by its
 * path on the file system.
 */
public class Movie implements Watchable<Movie>, Groupable<Movie> {
	enum MovieFormat{
		MP4, MOV, WMV, AVI, FLV, MKV
	}
	private final File aPath;
	private final MovieFormat format;

	private String aTitle;
	private String aLanguage;
	private String aStudio;

	private Map<String, String> aTags = new HashMap<>();

	/**
	 * Creates a movie from the file path. Callers must also provide required metadata about the movie.
	 *
	 * @param pPath
	 *            location of the movie on the file system.
	 * @param pTitle
	 *            official title of the movie in its original language
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	public Movie(File pPath, String pTitle, String pLanguage, String pStudio) {
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;

		//Get 3 last chars of path and set as Movie format
		String pathString = aPath.toString();

		String extension = pathString.substring(pathString.length() - 3).toUpperCase(Locale.ROOT);
		this.format = MovieFormat.valueOf(extension);
	}

	/**
	 * Indicates whether this Movie object represents a valid movie that can be played.
	 * 
	 * @return true if the underlying video file exists and is a file (not a directory)
	 */
	public boolean isValid() {
		return aPath.isFile();
	}

	public String getTitle() {
		return aTitle;
	}

	public String getLanguage() {
		return aLanguage;
	}

	public String getStudio() {
		return aStudio;
	}

	/**
	 * Sets the value of a custom tag.
	 *
	 * @param pKey
	 *            the key used to retrieve the tag.
	 * @param pValue
	 *            the value of the tag to insert. Use null to remove the key.
	 */
	public void setTag(String pKey, String pValue) {
		if (pValue == null) {
			aTags.remove(pKey);
		}
		else {
			aTags.put(pKey, pValue);
		}
	}

	/**
	 * Retrieves the value of a custom tag.
	 *
	 * @param pKey
	 *            the tag key, as it was inserted
	 * @return the associated value
	 */
	public String getTag(String pKey) {
		return aTags.get(pKey);
	}

	//Variables to determine if a Movie has been watched
	private boolean viewed = false;
	//Variable to determine the watch time of a movie in minutes (invented for the sake of providing a method in the interface)
	private int aWatchTime;


	//Watchable interface methods
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

	//Variables to determine if a movie has a related previous or next movie (as part of a sequel)
	private Movie aNext;
	private Movie aPrev;
	//Groupable interface methods
	public void setNext(Movie pNext){
		aNext = pNext;
	}
	public void setPrev(Movie pPrev){
		aPrev = pPrev;
	}
	public boolean hasNext(){
		if(aNext != null){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean hasPrev(){
		if(aPrev != null){
			return true;
		}
		else {
			return false;
		}
	}
}