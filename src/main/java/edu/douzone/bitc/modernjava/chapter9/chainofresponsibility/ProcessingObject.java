package edu.douzone.bitc.modernjava.chapter9.chainofresponsibility;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);

}
