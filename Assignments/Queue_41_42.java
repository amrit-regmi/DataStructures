// skeleton for exercises 37-42 SJ

// copied&modified from JavaSE 7 API source.
// COPYRIGHT Oracle / JCP
// GPL 2.0 licence


import java.util.Queue;
import java.util.AbstractSequentialList;
import java.util.NoSuchElementException;
import java.util.ListIterator;



/**
 * Implementation of interface {@code Queue}.
 * Storage differs for different tasks.
 *
 * @param <E> the type of elements held in this collection
 **/

public class Queue_41_42<E>
            extends AbstractSequentialList<E>
            implements Queue<E>
{

    // different storage structure for each task:
    Node<E> first, last; // for 41-42
    int size;


    /**
     * Constructs an empty queue.
     */
    public Queue_41_42() {
        // TODO
    	first = null;
    	last = null;
    	size = 0;
    }
    
    public class Node<E>
    {
    	 Node<E> next;
    	 E data;
    	
    	public Node(E e){
    		next = null;
    		data = e;
    	}
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions. Only on task 38-39.
     */
    @Override
	public boolean add(E e) {
        // TODO
    	
		Node<E> n = new Node<E>(e);
    	
    	if(this.isEmpty()){
    		
    		this.first = n;
    		this.last = n;
    		
    	}
    	else{
    		
    		this.last.next = n;
    		n.next = this.first;
    		this.last = n;
    		
    		}
    	size++;
    	return true;
    }


    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     */
    @Override
	public boolean offer(E e) {
        // TODO
        // write again, or use:
        try {
            return this.add(e);
        } catch (IllegalStateException ex) {
            return false;
        }
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
	public E remove() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        else{
        	
        	
        	E data = this.first.data;
        	
        	this.first = this.first.next;
        	
        	this.last.next = this.first;
        	size--;
        	return data;
        }
             // TODO
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
	public E poll() {
        // TODO
    	if (this.isEmpty())
    		return null;
    	else
    		return remove();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
	public E element() {
        // TODO
    	if (this.isEmpty())
            throw new NoSuchElementException();
        else
        	return this.first.data;
    }


    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
	public E peek() {
        // TODO
    	if (this.isEmpty())
            return null;
        else
        	return this.first.data;
    }


    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    @Override
	public int size() {
        // TODO
        return size;
    }



    // iteration not needed, possibly next week
    @Override
	public ListIterator<E> 	listIterator(int index) {
        throw new java.lang.UnsupportedOperationException("Iteration not implemented");
    }
}
