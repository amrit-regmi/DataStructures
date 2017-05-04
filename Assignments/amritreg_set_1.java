// skeleton for week 8

// comments and headers edited from java.util.Set
// copyright Sun/Oracle

import java.util.Set;
import java.util.AbstractSet;
import java.util.Iterator;
import java.lang.StringBuffer;
import java.lang.UnsupportedOperationException;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

// most of functionality is inherited from AbstractSet and AbstractCollection

// >>>>>>> CHANGE THE CLASS NAME TO YOUR USERNAME BEFORE SUBMISSION <<<<<<<<<<
public class amritreg_set_1<E>
        extends AbstractSet<E>
        implements Set<E>
{

    E[] data = null;    // storage array
    int n = 0;  // number of elements in set
    int M = 20; // always same as data.length, you can do without

    // modCount, needed in task 46
    int modCount = 0;

    // special removed value, needed in task 45
    E removed = null;

    /**
     * Default constructor. 
     */
    @SuppressWarnings("unchecked")
        public amritreg_set_1() {
            this(20);
        }


    /**
     * Constructor with m element hash table.
     * @param number of addresses in hash table
     */
    @SuppressWarnings("unchecked")
        public amritreg_set_1(int m) {
            data = (E[]) (new Object[m]);   // hash table
            // empty hash table is full of nulls
            n = 0;
            M = m;
            removed = (E)(new Object());    // any unique object
        }


    // Query Operations

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cardinality)
     */
    @Override
	public int size() {
        return n;
    }


    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     *
     * @param o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contains the specified element
     * @throws NullPointerException if the specified element is null.
     */

    @Override
	public boolean contains(Object o) {
    	
        // home address
        int h = o.hashCode() % M;
       
        if (h < 0)
            h = -h;

        // TODO X3
        
        while( h < data.length && data[h]!=null){
        	
        	if (data[h].equals(o)){
                return true;
        	}
        	  	
            h = (h + 1); 
        }

        return false;
    }



    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param e element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     *         element
     * @throws NullPointerException if the specified element is null.
     */
 
 @Override
public boolean add(E e) {
	 if(e == null){
	 		throw new NullPointerException("Inserting Null element is not allowed");
	 	}
	 
 	if(this.contains(e)){
 		return false;
 	}
     // never let hash table to be too full
     if (n >= M*2/3){
         doubleSize();   
     }

     int h = e.hashCode() % M;
     if (h < 0)
         h = -h;
     // TODO X3
     
     while( h < data.length && data[h]!=null){ 	
     	h = (h + 1);
     }
     
     /*
      * if we are out of bound then double the size and retry adding
      */
     if( h==data.length){
    	 doubleSize();
    	 add(e);
     }
     
     
     
     data[h]=e;
     n++;
     return true;
     
 }

    /**
     * Doubles the hash table.
     * @throws UnsupportedOperationException if not yet implemented.
     **/
    @SuppressWarnings("unchecked")
	private void doubleSize() {

        // TODO 47
        // allocate new larger array
        // traverse old array and hash all elements to new array
        // using new M
    	
    	M = 2*M;
    	E[] oldData = data;
    	data = (E[]) (new Object[M]);
    	for(E e: oldData){
    		if(e!=null && !e.equals(removed)){
    			this.add(e);
    		}
    		
    	}

    }


    /**
     * Removes the specified element from this set if it is present.
     *
     * @param o object to be removed from this set, if present
     * @return <tt>true</tt> if this set contained the specified element
     * @throws NullPointerException if the specified element is null.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this set
     */
    @Override
	public boolean remove(Object o) {
        // TODO 45

        // replace element with special element removed

    	int h = o.hashCode() % M;
        if (h < 0)
            h = -h;
   	
   	while(data[h]!=null){
       	if (data[h].equals(o)){
       		data[h] = removed;
       		n--;
       		modCount++;
               return true;
       	}
           h = (h + 1); 	
       }

       return false;

    }


    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    @Override
	public Iterator<E> iterator() {
        // after implementing HashIter, use:
        return new HashIter();

        // meanwhile:
       //throw new UnsupportedOperationException();
    }


    private class HashIter implements Iterator<E> {

        // iteration location
        int ind;

        // maintain information about original modCount
        // if change is m ade elsewhere, it will be detected here
        int origModCount;

        public HashIter() {
            ind = 0;
            origModCount = modCount;
        }

        @Override
		public boolean hasNext() {
            check();
            
            // TODO T46
            return !(data.length == ind);
            	
        }

        @Override
		public E next() {
            check();
            if(hasNext()){
            	return data[ind++];
            }else{
            	throw new NoSuchElementException("No next element");
            }
            // TODO T46
           
        }

        // remove the element that was returned by the previous next()
        // no need to implement
        @Override
		public void remove() {
        	if(data[ind-1] != null ){
        		(data[ind-1])=removed;
        	}
        	else{
        		throw new NullPointerException("Empty element cannot be removed");
        	}
            //throw new UnsupportedOperationException("Remove not implemented");
        }

        // check if modCount has changed elsewhere
        void check() {
            if (modCount != origModCount)
                throw new ConcurrentModificationException("Set modified during iteration");
        }

    }   // class HashIter



    /**
     * String representation of Set, works without iterations.
     * Here for debug purposes. Normal toString() is implemented in AbstractCollection
     * but it relies working iterator, thus you can use it finally.
     * @param hashTable if true, returns the whole hashTable, otherwise just elements
     * @return String of contents of Set
     **/
    public String toString_debug(boolean hashTable) {
        StringBuffer s = new StringBuffer("[");
        for (int i = 0; i < data.length; i++) {
            if (hashTable) {
                s.append(i);
                s.append(":");
                if (data[i] == null)
                    s.append("N");
                if (data[i] == removed)
                    s.append("N");
            }
            if (data[i] != null && data[i] != removed) {
                s.append(data[i]);
                s.append(" ");
            } else if (hashTable)
                s.append(" ");
        }
        s.setCharAt(s.length()-1, ']');
        return s.toString();
    }


}   // class
