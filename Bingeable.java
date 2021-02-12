import java.util.Iterator;
import java.util.List;

//For elements that can be binged (WatchList, TvShow)
public interface Bingeable<T> extends Watchable<T>, Iterable<T> {
    /**
     * Bingeable extends the iterable interface to provide a way to access the elements in any bingeable object.
     * This will be implemented through the iterator() method (see TvShow.iterator() and WatchList.iterator())
     */


    /**
    * Indicates which elements haven't been watched
    * @return a list of elements that haven't been watched
    */
    public List<T> remaining();

    /**
     *     Get the number of subElements within the bingeable element
     */
    public int size();

}
