import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Driver {

    public static void main(String[] args){
        Library myLibrary = new Library();
//-------------------------------------------- Movies ------------------------------------------------------------------
        File titanicFile = new File("a/first/path.mp4");
        File djangoFile = new File("a/second/path.mov");
        File oss117File = new File("a/third/path.wmv");
        File pulpFictionFile = new File("an/other/path.mkv");

        myLibrary.addMovie(new Movie(titanicFile,"Titanic","English","Paramount"));
        myLibrary.addMovie(new Movie(djangoFile,"Django","English","Columbia"));
        myLibrary.addMovie(new Movie(oss117File,"OSS117", "French", "Gaumont"));
        myLibrary.addMovie(new Movie(pulpFictionFile,"Pulp Fiction", "English", "Columbia"));

//---------------------------------------------- TV Show: The Office ---------------------------------------------------
        File theOfficeS1E1File = new File("some/first/path.mp4");
        File theOfficeS1E2File = new File("some/second/path.mov");

        Episode theOfficeS1E1 = new Episode(theOfficeS1E1File,"The Office S1E1");
        Episode theOfficeS1E2 = new Episode(theOfficeS1E2File,"The Office S1E2");

        TvShow theOffice = new TvShow("The Office");

        theOffice.addEpisode(theOfficeS1E1);
        theOffice.addEpisode(theOfficeS1E2);

        theOfficeS1E1.setNext(theOfficeS1E2);

//---------------------------------------------- TV Show: Friends ------------------------------------------------------

        File friendsS1E1File = new File("some/fourth/path.wmv");
        File friendsS1E2File = new File("some/fifth/path.avi");
        File friendsS1E3File = new File("some/fourth/path.wmv");
        File friendsS1E4File = new File("some/fifth/path.avi");
        File friendsS1E5File = new File("some/sixth/path.flv");
        File friendsS1E6File = new File("some/seventh/path.mkv");
        File friendsS1E7File = new File("some/eighth/path.wmv");
        File friendsS1E8File = new File("some/ninth/path.mkv");
        File friendsS1E9File = new File("some/tenth/path.mov");
        File friendsS1E10File = new File("some/eleventh/path.mp4");
        File friendsS1E11File = new File("some/twelvth/path.flv");

        Episode friendsS1E1 = new Episode(friendsS1E1File,"Friends S1E1");
        Episode friendsS1E2 = new Episode(friendsS1E2File,"Friends S1E2");
        Episode friendsS1E3 = new Episode(friendsS1E3File,"Friends S1E3");
        Episode friendsS1E4 = new Episode(friendsS1E4File,"Friends S1E4");
        Episode friendsS1E5 = new Episode(friendsS1E5File,"Friends S1E5");
        Episode friendsS1E6 = new Episode(friendsS1E6File,"Friends S1E6");
        Episode friendsS1E7 = new Episode(friendsS1E7File,"Friends S1E7");
        Episode friendsS1E8 = new Episode(friendsS1E8File,"Friends S1E8");
        Episode friendsS1E9 = new Episode(friendsS1E9File,"Friends S1E9");
        Episode friendsS1E10 = new Episode(friendsS1E10File,"Friends S1E10");
        Episode friendsS1E11 = new Episode(friendsS1E11File,"Friends S1E11");

        friendsS1E1.setNext(friendsS1E2);
        friendsS1E2.setNext(friendsS1E3);
        friendsS1E3.setNext(friendsS1E4);
        friendsS1E4.setNext(friendsS1E5);
        friendsS1E5.setNext(friendsS1E6);
        friendsS1E6.setNext(friendsS1E7);
        friendsS1E7.setNext(friendsS1E8);
        friendsS1E8.setNext(friendsS1E9);
        friendsS1E9.setNext(friendsS1E10);


        TvShow friends = new TvShow("Friends");
        friends.addEpisode(friendsS1E1);
        friends.addEpisode(friendsS1E2);
        friends.addEpisode(friendsS1E3);
        friends.addEpisode(friendsS1E4);
        friends.addEpisode(friendsS1E5);
        friends.addEpisode(friendsS1E6);
        friends.addEpisode(friendsS1E7);
        friends.addEpisode(friendsS1E8);
        friends.addEpisode(friendsS1E9);
        friends.addEpisode(friendsS1E10);
        friends.addEpisode(friendsS1E11);

        friendsS1E1.setNext(friendsS1E2);

        myLibrary.addTvShow(friends);

//----------------------------------------------------------------------------------------------------------------------


        /**
         * Question 2
         * Example of accessing the elements of a bingeable object using good design principle ()
         * Example with a TV show: The office
         */

        System.out.println("Accessing the elements of bingeable object:");
        Iterator<Episode> theOfficeIterator = friends.iterator();
        while (theOfficeIterator.hasNext()){
            System.out.println("Next Episode: " + theOfficeIterator.next().getTitle());
        }
        //or
        for(Episode episode: friends){
            System.out.println("Next Episode: " + episode.getTitle());
        }

        System.out.println("\n\n");

        /**
         * Question 3
         * Generating two watchlists
         */

        //Prepare the set of studios I want to gather English movies from
        Set<String> someStudios = new HashSet<>();
        someStudios.add("Paramount");
        someStudios.add("Columbia");

        //Generate the watchlist from this set and add it to the library
        WatchList englishMovies = myLibrary.englishFromStudios(someStudios, "English");
        myLibrary.addWatchList(englishMovies);

        System.out.println("English movies from the selected studios :");
        for(Movie movie: englishMovies){
            System.out.println(movie.getTitle());
        }
        System.out.println("\n\n");

        WatchList randomEpisodes = myLibrary.episodesFromShow(friends);
        System.out.println("10 random episodes from Friends: ");

        for(Episode episode : randomEpisodes.getEpisodes()){
            System.out.println(episode.getTitle());
        }

    }

}
