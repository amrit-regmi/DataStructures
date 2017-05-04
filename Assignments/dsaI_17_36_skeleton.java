// dsaI_17_36.java SJ

import java.util.Comparator;
import java.util.PriorityQueue;

public class dsaI_17_36_skeleton {

    public static void main(String[] args) {

    /*// # of elements
        int N = 10;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        // how many non-sorted elements
        int K = N/10 + 1;
        if (args.length > 1)
            K = Integer.valueOf(args[1]);
        if (K > N)
            K = N;

        // print or not
        boolean print = false;
        if (N < 15)
            print = true;
        if (args.length > 2 && (Integer.valueOf(args[2]) > 0))
            print = true;


        // create input
        System.out.println("Sort36 k=" + K);
        String[] array = randomArray(N, K, N);

        if (print)
            for (int i = 0; i < N; i++)
                System.out.print(array[i] + " ");
        System.out.println();

        sort36(array, N, K);

        if (print)
            for (int i = 0; i < N; i++)
                System.out.print(array[i] + " ");
        System.out.println();

    } // main() 

    // creates a random N-element array with K unsorted elements at end
    public static String[] randomArray(int N, int K, int seed) {
        String[] array = new String[N];

        Random r = new Random(seed);
        for (int i = 0; i < N; i++) {
            array[i] = randomString(r, 4);
        }

        java.util.Arrays.sort(array, 0, N-K);

        return array;
    } // randomArray()

    // creates a random string of length len
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char)(r.nextInt(26) + 'a');
        return new String(C); */
    	
   String I[] =	{"c","e", "f","h","j","m","n","w","z","a","c","d","x","p","k","q","r","s","v","b","o","t","r"};
    
    System.out.println("K = "+16+" n ="+I.length);
    
    for(int i=0; i < I.length; i++){
    	System.out.print(I[i]+ "  ");
    	
    }
    System.out.println();
    sort36(I, I.length, 14);
    
    for(int i=0; i < I.length; i++){
    	System.out.print(I[i]+ "  ");
    	
    }
    
    }



    /**
      * 36. Write an algorithm that sorts efficiently an array (or
      * Vector/ArrayList) that is in almost ascending order. Here "almost"
      * means that most of the array is in correct ascending order, but at most
      * k last elements are out of order and can contain also small elements.
      * Use a priority queue as helper by putting the k last elements to
      * priority queue. Then merge the contents of the priority queue and the
      * ascending majority of the array to the array (from end to start). Time
      * complexity should be O(n+klogk).  
      * @param A array to sort
      * @param n number of elements to sort
      * @param k number of unsorted elements (out of n)
      **/
    public static <E extends Comparable<? super E>>
                void sort36(E A[], int n, int k) {

        // create priority queue which will give "largest" elements first
        PriorityQueue<E> P = new PriorityQueue<E>(k+1, new ReverseComparator<E>());
        // TODO
        
        //unsorted part to priority queue
        for(int i= n-k; i<n;i++ ){ // t= O(k log k)  same as int i=n-1; i>=n-k;i-- 
        	P.add(A[i]);
        }
         
        
        E item = P.poll();  
        
        int count= n-k-1;    //pointer location(initially last item of the sorted part)
        
        while(item != null){ //until there is item in queue	  
        					//t= O(n) as we visit every element only once 
        	
            // merge from end
          		
        		/*
        		 * if its not the first element and the last sorted item is bigger than item from queue then
        		 * shift the element towards end of array by k indexes and move towards beginning of array
        		 */
        		
        	if(count >= 0  && item.compareTo(A[count])<0 ){
        			//System.out.println(A[count+k]+" ===> "+A[count]);	
        		A[count+k]=A[count];
        		count--; //gradually moving towards beginning of the array      		
        	}
        		
        		/*
        		 *else  if the last sorted item is less than item from queue then
        		 * set from the queue to n+k position  and decrease the element shifting by 1
        		 *  
        		 */	
        	else 
        	{
	      		//System.out.println(A[count+k]+" => "+item);
	        	A[count+k]= item;	
	        	k=k-1;
	        	item=P.poll();	//getting the head from the queue        			
        	}
       }
    }
} // class dsaI_17_36

// Comparator class that returns the reverse of natural order of elements
class ReverseComparator<T extends Comparable<? super T>> implements Comparator<T>{

    @Override
	public int compare(T a1, T a2) {
        return - a1.compareTo(a2);
    }
} // class ReverseComparator

