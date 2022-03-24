package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.InvalidArgumentException;

public class Interval<T extends Comparable<T>> {

    private T begin;
    private T end;

    public Interval(T begin, T end) throws InvalidArgumentException {
        if (begin.compareTo(end) > 0) {
            throw new InvalidArgumentException("Invalid operation. The Interval begin is after the Interval end!");
        }

        this.begin = begin;
        this.end = end;
    }

    public T getBegin() {
        return begin;
    }

    public T getEnd() {
        return end;
    }

    public void setBegin(T begin) throws InvalidArgumentException {
        if (begin.compareTo(getEnd()) > 0) {
            throw new InvalidArgumentException("Invalid operation. The begin is after the Interval end!");
        }

        this.begin = begin;
    }

    public void setEnd(T end) throws InvalidArgumentException {
        if (getBegin().compareTo(end) > 0) {
            throw new InvalidArgumentException("Invalid operation. The end is before the Interval begin!");
        }

        this.end = end;
    }

    public boolean isEndedAfterItsBegin(Interval<T> other) {
        return getEnd().compareTo(other.getBegin()) > 0;
    }

}