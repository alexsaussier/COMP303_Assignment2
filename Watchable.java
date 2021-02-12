import java.util.List;

//For elements that can be watched (Movie, TvShow and Episode)
public interface Watchable<T> {

    /**
     * Determine if the element has been viewed
     */
    public void setViewedStatus(boolean pViewed);

    /**
     * indicates if object has been watched entirely
     *
     */
    public boolean isWatched();

    /**
     *
     * Set the watch time of an element
     * @param pWatchTime - The element length, in minutes
     */
    public void setWatchTime(int pWatchTime);

    /**
     * Indicates the total watch time of the element
     * @return the number of minutes
     */
    public int getWatchTime();

}
