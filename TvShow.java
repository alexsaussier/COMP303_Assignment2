import java.util.*;

public class TvShow implements Bingeable<Episode> {
    private List<Episode> aEpisodes = new LinkedList<>();

    private String aName;

    //Empty TvShow constructor.
    public TvShow(String pName){
        aName = pName;
    }

    //Second constructor that sets it with a list of episodes
    public TvShow(String pName, List<Episode> pEpisodes){
        aName = pName;
        aEpisodes = pEpisodes;
    }

    public String getName(){
        return aName;
    }

    public List<Episode> getEpisodes(){
        return Collections.unmodifiableList(aEpisodes);
    }

    public void addEpisode(Episode pEpisode){
        aEpisodes.add(pEpisode);
    }

    //Remove an episode if it has been added by accident
    public void removeEpisode(Episode pEpisode){
       aEpisodes.remove(pEpisode);
    }

    //Variables to determine if a TvShow has been watched
    private boolean viewed = false;
    //Variable to determine the watch time of a TvShow in minutes (invented for the sake of providing a method in the interface)
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

    //Bingeable interface methods
    public List<Episode> remaining(){
        List<Episode> notWatched = new ArrayList<>();

        for(Episode element : aEpisodes){
            if(!element.isWatched()){
                notWatched.add(element);
            }
        }
        return notWatched;
    }
    public int size(){
        int count = 0;
        for(Episode element : aEpisodes){
            count++;
        }
        return count;
    }

    //Iterable interface methods - provides access to the Episode elements of a TvShow object
    @Override
    public Iterator<Episode> iterator(){
        return aEpisodes.iterator();
    }
}
