//For Episodes in a sequence or Movies in a sequel
public interface Groupable<T> {
    /**
     * Set the next related element
     * @param pNext
     */
    public void setNext(T pNext);

    /**
     * Set the previous related element
     * @param pPrev
     */
    public void setPrev(T pPrev);

    /**
     * Check if the element has a related element that comes next
     */
    public boolean hasNext();

    /**
     * Check if the elemen has a related element that comes before
     */
    public boolean hasPrev();


}
