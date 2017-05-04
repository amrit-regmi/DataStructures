// dsaI_17_31.java SJ

import fi.joensuu.cs.tra.Set;
import java.util.Random;
import java.util.TreeSet;
import java.util.HashSet;

public class dsaI_17_31_skeleton {

	public static void main(String[] args) {

		int N = 10;
		if (args.length > 0)
			N = Integer.valueOf(args[0]);


		Set<Integer> S1 = new Set<Integer>();
		Set<Integer> S2 = new Set<Integer>();
		Set<Integer> S3 = new Set<Integer>();

		Random r = new Random(42);
		Integer x, y;
		for (int i = 0; i < N; i++) {
			x = r.nextInt(N*2);
			S1.add(x);
			y = r.nextInt(N*2);
			S2.add(x-y);
			S3.add(x+y);
		}
		
		S1.add(100);
		S2.add(100);
		S3.add(100);
		S3.add(10);
		

		System.out.println("S1:      " + S1);
		System.out.println("S2:      " + S2);
		System.out.println("S3:      " + S3);


		System.out.println("twoOutFromThreeTRA: " + twoOutFromThree(S1, S2, S3));

        TreeSet<Integer> TS1 = new TreeSet<Integer>(S1);
        TreeSet<Integer> TS2 = new TreeSet<Integer>(S2);
        TreeSet<Integer> TS3 = new TreeSet<Integer>(S3);

        System.out.println("twoOutFromThreeJava: " + twoOutFromThree(TS1, TS2, TS3));


	} // main()


    /**
      * 31. Write an algorithm that creates a â€ťtwo out of threeâ€ť intersection
      * of sets. Algorithm gets as pa- rameter three sets, and creates a new
      * set that contains those elements that are contained in ex- actly two
      * input sets (not all three, not less than two). You can use either
      * JavaAPI sets, or TRA- library set. Do not change the input sets. What
      * is the time complexity of your algorithm?
      * 
      * 
      * 5*O(nA+nB+nC) => O(n)
      **/

    // TRA version
	public static <E> Set<E> twoOutFromThree(Set<E> A, Set<E> B, Set<E> C) {
	
		return null;
	} // twoOutFromThree()
		
    // JavaAPI version
	public static <E> TreeSet<E> twoOutFromThree(TreeSet<E> A, TreeSet<E> B, TreeSet<E> C) {

        // TODO
		HashSet<E> hA=  new HashSet<E>(A); 
		HashSet<E> hB=  new HashSet<E>(B); 
		HashSet<E> hC=  new HashSet<E>(C);
		Set<E> returnSet = new Set<E>();
	
		hA.forEach(element ->{
			/*
			 * if is in B not in C or vice versa
			 */
			if(hB.contains(element) && !hC.contains(element) || hC.contains(element) && !hB.contains(element)){
				returnSet.add(element);
			}
			
		});
		hB.forEach(element ->{
			/*
			 * if is in C not in A or vice versa
			 */
			if(hA.contains(element) && !hC.contains(element) || hC.contains(element) && !hA.contains(element)){
				returnSet.add(element);
			}
			
		});
		
        // TODO

        return returnSet;
		

		
        
	} // twoOutFromThree()
		


} // class dsaI_17_3