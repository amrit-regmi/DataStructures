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
public class amritreg_set_45<E>
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
        public amritreg_set_45() {
            this(20);
        }


    /**
     * Constructor with m element hash table.
     * @param number of addresses in hash table
     */
    @SuppressWarnings("unchecked")
        public amritreg_set_45(int m) {
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
    /*	Generally the time complexity of the  operation will be amortized O(1) and depends upon how well 
     * 	the elements are distributed across the array, if the hashing function calculates many of the 
     * 	positions in the same spot given the input data (collision), then the O(1) starts to creep closer to O(N).
     * 
     * for example: if the hash of the given object is allocated toward beginning of the array and the next empty
     * space on the array is towards end of the array, so in order to check if the element exists on the array the algorithm 
     * has to go through the allocated index to next empty index which will be almost O(N)
     * 
     * With the good hash function the algorithm would not have to traverse through many elements as the empty indexes will 
     * be distributed relatively evenly thus the operation will be amortized O(1).
     * 
     */
    @Override
	public boolean contains(Object o) {
    	
    	if(o==null){
    		throw new NullPointerException("Checking Null element is not allowed");
    	}
    	
        // home address
        int h = o.hashCode() % M;
       
        if (h < 0)
            h = -h;

        // TODO X3
        /*
         * until we have gone through all the element or we reach the next empty index
         * we keep looking on the next index
         */
        int count=0; //to check if we iterated through all the elements 
        
        /*
         * exit the loop if the element is null or count is equal to number of elements
         */
        
        while(data[h]!=null && count!= n ){
        	
        	/*
        	 * if the element at the index is equal to given object 
        	 * return true
        	 */
        	if (data[h].equals(o)){
                return true;
        	}
        	
        	count++;
            h = (h + 1)%M; 
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
 
 	/*
	 *  we could have checked the set if it already contains the element using this.contains(e) before adding 
	 *  but with this approach I think then we iterate over the same elements twice 
	 *  once to check if it exist and next time to add if it doesn't exist.
	 *  
	 *  As mentioned earlier the time complexity of the operation will be amortized O(1) for the good hash function
	 *  and O(N) for the worst case if the hashing function is not efficient and the array is almost-full/full
	 *  
	 */
 
 @Override
public boolean add(E e) {
 	
 	if(e == null){
 		throw new NullPointerException("Inserting Null element is not allowed");
 	}
 	/*if(this.contains(e)){
 		return false;
 	}*/
 	
 	/*  here I did not call the contains method instead I checked it while adding it thought it will be 
  	 *  slightly efficient although i think the time complexity will remain the same.

  	*/
 	
 	
     // never let hash table to be too full
     if (n >= M*2/3){
     	 
         doubleSize();
        
     }

     int h = e.hashCode() % M;
     if (h < 0)
         h = -h;

     // TODO X3
     
     /*
      * until 
      * we find the empty slot or 
      * we find the place with unique object removed 
      * we keep on looking on next index.
      */
      
   
     while( data[h]!=null && !data[h].equals(removed) ){ 
    	 /*
    	  * checking if the current element is equal to given element 
    	  * if true then we return false and exit the loop
    	  */
     	if(data[h].equals(e)){
     		return false;
     	}
     	/*
     	 * checking the next index
     	 */
     	h = (h + 1)%M; 	//mod M will make sure that we will check through all the remaining indexes 
     }
     
     /*
      * If the next index is removed we check if the given element exists further in the array
      */
    
     if(data[h] != null && data[h].equals(removed)){
    	 int temp = h;
    	 
    	 h = (h+1)%M;
    	
    	 /*
    	  * check until we find the next null or the given element
    	  * if we find the next null we go back and add on the index removed 
    	  * or if we find the given element we return false 
    	  */
    	 
    	 while( data[h]!=null && !data[h].equals(e) ){
    		 h=(h+1)%M;
    	 }
    	 
    	 if(data[h]==null){
    		 data[temp]=e;
    		 return true;
    	 }
    	 if(data[h].equals(e)){
    		 return false;
    	 }
    	 
     }
     /*
      * we set the element at the current index which should be either empty or with object removed
      * we increase the modCount so that the iterator sees the change we have made 
      * after the add is successful we  increase the element count by 1 and return true
      */
     
     data[h]=e;
     modCount++; //doesn't make any difference for this task
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
    	
    	

        //throw new UnsupportedOperationException();

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
            h = (h + 1) % M; 	
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
       // throw new UnsupportedOperationException();
    }


    private class HashIter implements Iterator<E> {

        // iteration location
        int ind;
        int tempInd;

        // maintain information about original modCount
        // if change is made elsewhere, it will be detected here
        int origModCount;

        public HashIter() {
        	
        	tempInd = -1;
            ind = 0;
            origModCount = modCount;
        }

        @Override
		public boolean hasNext() {
            check();
           // System.out.println(" "+ data.length +" == "+ ind);
            // TODO T46
            
            int i = ind;
            
            while(i < data.length){
            	
            	//System.out.println(" "+ data.length +" == "+ ind);
            	
            	if(data[i]!=null && data[i]!=removed){
            		tempInd = i;
            		return true;
            	}
            	
            	i++;
            	
            }
           
            tempInd=-1;
            return false;
            /*
             * if we just want to return the empty elements as well
             */
            //return !(data.length == ind);
            
            	
        }

        @Override
		public E next() {
            check(); 
            	 
            if(hasNext()){
            	ind = tempInd+1;
            	return data[tempInd];
            	
            }else{
            	
            	throw new NoSuchElementException("No next element");
            }
            // TODO T46
           
        }

        // remove the element that was returned by the previous next()
        // no need to implement
        @Override
		public void remove() {
        	
        	check();
        	data[ind-1]=removed;
        	n--;
        	modCount++;
        	origModCount = modCount;
        	
            //throw new UnsupportedOperationException("Remove not implemented");
        }

        // check if modCount has changed elsewhere
        void check() {
        	//System.out.println("checked" + (modCount == origModCount));
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
