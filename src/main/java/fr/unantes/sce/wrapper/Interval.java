package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.InconsistentArgumentException;

import javax.annotation.Nonnull;

/**
 * Interval is an interval wrapper that can be empty where the beginning must precede the end
 */
public class Interval<T extends Comparable<T>> {

    /**
     * Fields
     */
    private T begin;
    private T end;

    /**
     * Creates a new interval wrapper
     * @param begin the beginning value
     * @param end the end value
     * @throws InconsistentArgumentException if the beginning does not precede the end
     */
    public Interval(@Nonnull T begin, @Nonnull T end) throws InconsistentArgumentException {
        if (begin.compareTo(end) > 0) {
            throw new InconsistentArgumentException("Invalid operation. The Interval begin is after the Interval end!");
        }

        this.begin = begin;
        this.end = end;
    }

    /**
     * Gets the interval begin
     * @return the interval begin
     */
    @Nonnull
    public T getBegin() {
        return begin;
    }

    /**
     * Gets the interval end
     * @return the interval end
     */
    @Nonnull
    public T getEnd() {
        return end;
    }

    /**
     * Sets the new interval begin
     * @param begin the new interval begin
     * @throws InconsistentArgumentException if the new beginning does not precede the end
     */
    public void setBegin(@Nonnull T begin) throws InconsistentArgumentException {
        if (begin.compareTo(getEnd()) > 0) {
            throw new InconsistentArgumentException("Invalid operation. The begin is after the Interval end!");
        }

        this.begin = begin;
    }

    /**
     * Sets the new interval end
     * @param end the new interval end
     * @throws InconsistentArgumentException if the beginning does not precede the new end
     */
    public void setEnd(@Nonnull T end) throws InconsistentArgumentException {
        if (getBegin().compareTo(end) > 0) {
            throw new InconsistentArgumentException("Invalid operation. The end is before the Interval begin!");
        }

        this.end = end;
    }

    /**
     * Checks if the beginning of the second interval precedes the beginning of the first
     * @param other the other interval
     * @return true if the beginning of the second interval precedes the beginning of the first, false otherwise
     */
    public boolean isEndedAfterItsBegin(@Nonnull Interval<T> other) {
        return getEnd().compareTo(other.getBegin()) > 0;
    }

}