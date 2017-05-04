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

public class Queue_40<E>
            extends AbstractSequentialList<E>
            implements Queue<E>
{

    // different storage structure for each task:
    // LinkedList<E> data; // for T37
    E[] data;
    int headindex;
    int nextindex;
    int size;
    
    // for T38-40
    // Node<E> first, last; // for 41-42


    /**
     * Constructs an empty queue.
     */
	@SuppressWarnings("unchecked")
	public Queue_40(int n) {
    	data = (E[]) new Integer[n];
    	headindex =0;
    	nextindex = 0;
    	size=0;
    	
    	
    	//System.out.println("ARGS = >"+n);	
    	
        // TODO
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
    	
    	if(size == data.length){
    		System.out.println();
    		System.out.println(" Capacity restrictions exceeded, Size = " +this.size() +" Capacity = "+ data.length);
    		expand();
    		System.out.println(" Doubling the capacity ,New capacity = "+ data.length);
    		
    	}
    	
    	this.data[nextindex]=e;
    
    	
    	nextindex=(nextindex+1) % data.length;
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
    	
    	if (size == 0){
    		System.out.println("Queue is empty");
    		throw new NoSuchElementException("Queue is empty");
    	}

        E val = data[headindex];                 
    	headindex = (headindex+1)%(data.length);
    	size--;
    	return val;
    }

    /**
     * Retrieves and removes the head of this queue, 
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
	public E poll() {
    	
    	if (this.isEmpty()){
    		
    		System.out.println();
    		System.out.println("Queue is empty");
            return null;
            }
    	
    	else{
    		return this.remove();}
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
    	if (this.isEmpty()){	
    		throw new NoSuchElementException("Queue is empty");
    	}
    	
    		return this.data[headindex];
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
    		return this.data[headindex];
    
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
    
    public void expand(){
    	 @SuppressWarnings("unchecked")
		E[] Ddata = (E[]) new Integer[2*data.length];
    	 for(int i=0; i < data.length; i++)
    	    {
    		 Ddata[i] = data[headindex];
    	      headindex=(headindex+1) % data.length;
    	    }
    	 headindex = 0;
    	 nextindex = size();
    	 data = Ddata;
    }



    // iteration not needed, possibly next week
    @Override
	public ListIterator<E> 	listIterator(int index) {
        throw new java.lang.UnsupportedOperationException("Iteration not implemented");
    }
}
