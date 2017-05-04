
 // dsaI_17_27_28_skeleton_amrit.java SJ

import java.util.LinkedList;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;


public class dsaI_17_27_28_skeleton_amrit  {


    // Main program use
    // java dsaI_17_27_28_skeleton [N] [N2] [S] [diff]
    // where N is # of elements, N2 is # of elements on second list
    // S is random number seed, and diff to ensure that there won't be
    // common elements in lists

    // please test with diverse inputs
    // you may also yourself generate different types of input

    public static void main(String[] args) {

        // array size
        int N1 = 15;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);

        int N2 = N1+3;
        if (args.length > 0)
            N2 = Integer.valueOf(args[1]);

        // random seed
        int seed = 42;
        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        // same elements or not
        int diff = 0;
        if (args.length > 3)
            diff = 1;


        // create input arrays -> lists
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];

        java.util.Random r = new java.util.Random(seed);

        for (int i = 0; i < N1; i++) {
            T1[i] = r.nextInt(N1/2);
        }

        for (int i = 0; i < N2; i++) {
            T2[i] = r.nextInt(N2/2) + diff * N1;
        }

        // print if there are not too many elements
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

        // create lists

        LinkedList<Integer> L1 = new LinkedList<Integer>();
        LinkedList<Integer> L2 = new LinkedList<Integer>();

        for (Integer x : T1) {
            L1.add(x);
        }

        for (Integer x : T2) {
            L2.add(x);
        }

        // call task 28
        // either of these suffices!!!
        LinkedList<Integer> res = twoCommons28(L1, L2);

        System.out.print("Task 28, twoCommons = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : res)
                System.out.print(" " + i);
            System.out.println();
        } else {
            System.out.println(res.size() + " elements");
        }


    } // main()


    /**
     * 27. Write an algorithm that gets as a parameter a collection
     * (Collection<E>) and which creates and returns a mapping (function)
     * (Map<E, Integer>) having as keys all different elements of the
     * collection and as a value the number of each elements found in the
     * collection. Hint: all collections provide foreach iteration. What is the
     * time complexity of your algorithm?
     **/
    //Time complexity O(n)
    public static <E> Map<E, Integer> occurences(Collection<E> C) {
        Map<E, Integer> result = new LinkedHashMap<E, Integer>(); // TODO
        
        // TODO
        for (E s:C){
    		if(result.containsKey(s)){
                result.put(s,result.get(s)+1);
            }
            else{
                result.put(s,1);
            }
           }

        return result;

    }

    
    /**
     * 28. Double intersection of lists.
     * Creates a new list of those elements that occur in both
     * input lists at least twice.
     * return list elements in order of list L1
     * @param L1 input list
     * @param L2 input list
     * @return list of elements that are in both lists at least twice
     * 
     * Time complexity O(n)
     */
    public static <E> LinkedList<E> twoCommons28(LinkedList<E> L1, LinkedList<E> L2) {
    	Map<E, Integer> L1map = occurences(L1);
    	Map<E, Integer> L2map = occurences(L2);
        
    	// result list
        LinkedList<E> twoCommons = new LinkedList<E>();
        // TODO
        L1map.forEach( (k,v) ->{
    		if(v.compareTo(2)>=0){
    			//System.out.println(k + "=>" + v );
    			if(L2map.get(k).compareTo(2)>=0){
    				twoCommons.add(k);
    			}
    		}
    		
    	});
    	

        // please make a plan with pen and paper _before_ writing anything here...

        return twoCommons;
    } // twoCommons28()

    

} // class

