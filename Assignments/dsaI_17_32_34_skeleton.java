// dsaI_17_32_34_skeleton.java SJ
// Main program and skeleton for week 1 tasks 5 and 6

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class dsaI_17_32_34_skeleton {


    // Main program usage:
    // java dsaI_17_32_34_skeleton [N] [N2] [S]
    // where N is the number of elements, N2 is the number of elements of the second array
    // and S is the seed for random number generation
    public static void main(String[] args) {

        // input sizes
        int N1 = 10;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);

        int N2 = N1;
        if (args.length > 1)
            N2 = Integer.valueOf(args[1]);

        // random number seed
        int seed = 2017;
        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        // create input arrays 
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];

        // fill with elements
        java.util.Random r = new java.util.Random(seed);
        for (int i = 0; i < N1; i++) {
            T1[i] = r.nextInt(N1);
        }

        for (int i = 0; i < N2; i++) {
            T2[i] = r.nextInt(N2*2);
        }

        // print arrays (unless there are a lot of elements)
        if (N1 <= 20 && N2 <= 20) {
            System.out.print("T1: ");
            for (int i = 0; i < N1; i++)
                System.out.print(" " + T1[i]);
            System.out.println();

            System.out.print("T2: ");
            for (int i = 0; i < N2; i++)
                System.out.print(" " + T2[i]);
            System.out.println();
        }

        // Convert array into LinkedList

        LinkedList<Integer> LL1 = new LinkedList<Integer>();
        LinkedList<Integer> LL2 = new LinkedList<Integer>();
        for (Integer x : T1)
            LL1.add(x);

        for (Integer x : T2)
            LL2.add(x);

        // call task 32
        retainAll(LL1, LL2);

        System.out.print("Task 32, remaining elements = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : LL1)
                System.out.print(" " + i);
            System.out.println();
        }
        System.out.println(LL1.size() + " elements");

        // Convert array into ArrayList

        ArrayList<Integer> AL1 = new ArrayList<Integer>(T1.length);
        ArrayList<Integer> AL2 = new ArrayList<Integer>(T2.length);
        for (Integer x : T1)
            AL1.add(x);

        for (Integer x : T2)
            AL2.add(x);

        // call task 33
        retainAll(AL1, AL2);

        System.out.print("Task 33, remaining elements = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : AL1)
                System.out.print(" " + i);
            System.out.println();
        }
        System.out.println(AL1.size() + " elements");


        // task 34

        LinkedList<Double> DL = new LinkedList<Double>();
        for (int i = 0; i < N1; i++) {
            DL.add(100.0*r.nextDouble());
        }

        System.out.println("Input: " + DL);

        List<Double> np = nearestPair(DL);

        System.out.println("Nearest pair" + np);

            

    } // main()




    /**
     * 32. Write a linear time algorithm retainAll(LinkedList A, Collection B)
     * which removes from list A all those elements that are not in in set B.
     * Hint: create a set (HashSet) of elements of collection B. Then it is
     * easy to decide which of the elements of A should be removed. Do not use
     * ready retainAll() operation.
     * @param A List in which elements are removed
     * @param B List of which elements are retained in A
     **/
    	public static <E> void retainAll(LinkedList<E> A, LinkedList<E> B) {
    	HashSet<E> hB = new HashSet<E>(B);
    	Iterator<E> I = A.iterator();
    	while(I.hasNext())
    		
    	{
    		if(!hB.contains(I.next())){
    			I.remove();
    		}
    	}

        // TODO

    } // retainAll()


    /**
     * 33. Write a linear time algorithm retainAll(ArrayList A, Collection B)
     * which removes from array-based list A all those elements that are not in
     * in set B. Hint: create a set (HashSet) of elements of collection B. Then
     * it is easy to decide which of the elements of A should be removed.
     * Compared to task 32 you cannot, however, call remove() for each element
     * to remove, as it would destroy the linear time complexity. Do not use
     * ready retainAll() operation.  
     * 
     * @param A List in which elements are removed
     * @param B List of which elements are retained in A
     * 
     * t= 3*O(n) => O(n)
     **/
    
    public static <E> void retainAll(ArrayList<E> A, ArrayList<E> B) {
    	
    	HashSet<E> hB = new HashSet<E>(B); //O(n)
    	Iterator<E> I = A.iterator();
    	ArrayList<E> temp = new ArrayList<E>();
    	
    	E temp2 = null;
    	
    	while(I.hasNext())	// O(n)
    	{	
    		temp2 = I.next();
    		if(!hB.contains(temp2)){
    			temp.add(temp2); //O(1)
    		}
    	}
    	
    	A.removeAll(temp); // O(n)

        // TODO

    } // retainAll()


    /**
     * 34. Write an algorithm that finds from a collection of floating point
     * numbers those two different numbers that have the lowest (mutual)
     * difference. Return the two values as a list of two elements or null if
     * there are less than two values in the input collection. Hint: put all
     * values to a sorted set and iterate that. What is the time complexity of
     * your algorithm?
     *
     * @param L The input list
     * @return List of two nearest values (or null)
     * 
     * 
     * 
     * t= O(log n)+ O(n)
     * 
     **/

    public static List<Double> nearestPair(Collection<Double> L) {

        if (L.size() < 2)
            return null;

        LinkedList<Double> result = new LinkedList<Double>();
        
        TreeSet<Double> s= new TreeSet<Double>(L); //O(log n)
        Iterator<Double> I  = s.iterator();
        
        
        //System.out.println(s);
        
        
        Double temp1 = I.next();
        Double temp2 = I.next();
 
        Double p1 = temp1;
        Double p2= temp2;
        
        Double diff = p2-p1;
       
        while(I.hasNext()){ //O(n)
        	temp1 = temp2;
        	temp2 = I.next();
        	if((temp2 -temp1) < diff){
        		
        		//System.out.println((temp2-temp1)+ "<"+ diff);
        		p1 = temp1;
        		p2 = temp2;	
        		diff = p2-p1;
        		
        	}
       }
        
        result.add(p1);
        result.add(p2);

        // TODO

        return result;

    } // nearestPair()


} // class


