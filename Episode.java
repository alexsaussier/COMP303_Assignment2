import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Episode implements Watchable<Episode>, Groupable<Episode> {
    private final File aPath;
    private final String aTitle;
    private final int sequentialNumber;

    private static int instanceCounter; //For informative purposes, to keep track of number of instances

    //For storing custom information.
    private Map<String, String> aTags = new HashMap<>();


    /**
     * Creates a movie from the file path. Callers must also provide required metadata about the movie.
     *
     * @param pPath location of the movie on the file system
     * @throws IllegalArgumentException if the path doesn't point to a file (e.g., it denotes a directory)
     */

    public Episode(File pPath, String pTitle) {
        assert pPath != null;
        if (pPath.exists() && !pPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        aPath = pPath;
        aTitle = pTitle;
        //Assigns a sequential number = to the total number of instances at the time of creation.
        instanceCounter++;
        this.sequentialNumber = instanceCounter;

    }

    /**
     * Sets the value of a custom tag.
     *
     * @param pKey   the key used to retrieve the tag.
     * @param pValue the value of the tag to insert. Use null to remove the key.
     */
    public void setTag(String pKey, String pValue) {
        if (pValue == null) {
            aTags.remove(pKey);
        } else {
            aTags.put(pKey, pValue);
        }
    }

    /**
     * Retrieves the value of a custom tag.
     *
     * @param pKey the tag key, as it was inserted
     * @return the associated value
     */
    public String getTag(String pKey) {
        return aTags.get(pKey);
    }

    /**
     * Check if path is valid
     */
    public boolean isValid() {
        return aPath.isFile();
    }

    /**
     * Getter method to retrieve title
     */
    public String getTitle(){
        return aTitle;
    }


    /**
     * Getter method for retrieving the sequential number of an Episode object
     */
    public int getSequentialNumber() {
        return this.sequentialNumber;
    }


    //Variables to determine if an eipsode has been watched
    private boolean viewed = false;
    //Variable to determine the watch time of a episode in minutes (invented for the sake of providing a method in the interface)
    private int aWatchTime;


    //Watchable interface methods
    public void setWatchTime(int pWatchTime) {
        aWatchTime = pWatchTime;
    }

    public int getWatchTime() {
        if (aWatchTime == 0) {
            throw new IllegalArgumentException("Watch time information not available");
        }
        return aWatchTime;
    }

    public void setViewedStatus(boolean pViewed) {
        viewed = pViewed;
    }

    public boolean isWatched() {
        return viewed;
    }

    //Variables to determine if an episode has a related previous or next episode (as part of a sequel)
    private Episode aNext;
    private Episode aPrev;

    //Groupable interface methods


    public void setNext(Episode pNext) {
        aNext = pNext;
        aNext.setPrev(this);
    }

    public void setPrev(Episode pPrev) {
        aPrev = pPrev;
    }

    public boolean hasNext() {
        if (aNext != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasPrev() {
        if (aPrev != null) {
            return true;
        } else {
            return false;
        }
    }
}