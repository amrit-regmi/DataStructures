// Set8_test.java SJ

import java.util.Random;
import java.util.HashSet;
import java.util.Iterator;

public class Set8_test {

	public static void main(String[] args) {


		int N = 10;
		if (args.length > 0)
			N = Integer.valueOf(args[0]);

        // compare our own set and java.util.HashSet

        // we can't use plain Set if we want to use toString_debug()
		// Set<String> mySet = new Set8<String>(N*2);
        // change class name according your own class
		amritreg_set_45<String> mySet = new amritreg_set_45<String>(N*2);

        HashSet<String> reference = new HashSet<String>(N*2);

		System.out.println("Add:");
		Random r = new Random(42);
		for (int i = 0; i < N; i++) {
			String x = randomString(r, 2);
			System.out.print(x + " ");
            // add same elements to both
			mySet.add(x);
			reference.add(x);
		}
		System.out.println();

        // print both
        // you can use standard toString() after you have implemented iterator
		System.out.println("\nContents:");
		//System.out.println(mySet.toString_debug(false));
	    System.out.println(mySet.toString_debug(true));    // use true to show hash table contents
        System.out.println("Reference:");
		System.out.println(reference);


        // random element probably not found, unless we are lucky
        System.out.println("\nSearch random elements:");
		for (int i = 0; i < 10; i++) {
            String x = randomString(r, 2);
            System.out.println(x + " " + mySet.contains(x) + " " +
                             reference.contains(x));
            if (mySet.contains(x) != reference.contains(x))
                System.out.println("WRONG");
		}

        // We should find the elements of the reference set
        System.out.println("\nSearch elements of reference:");
        for (String x : reference) {
            System.out.println(x + " " + mySet.contains(x));
            if (!mySet.contains(x))
                System.out.println("WRONG");
        }

        // try to add elements of reference again
        // these should not success as they should be in the set already
        System.out.println("\nAdd same elements again, should give false every time:");
        for (String x : reference) {
            System.out.println(x + " " + mySet.add(x));
        }

        // print sets
		System.out.println("\nContents:");
		System.out.println(mySet.toString_debug(false));

        // TODO: test tasks 45-47

        // if test 45
        if (45 == 45) {

            // remove every other element
            int i = 0;
            Iterator<String> refIter = reference.iterator();
            while (refIter.hasNext()) {
                String x = refIter.next();
                if ((i++) % 2 == 1) {
                    System.out.println("Remove:" + x + " " +
                             mySet.remove(x));
                    refIter.remove();
                }
            }

            // print sets
            System.out.println("\nContents 45:");
            System.out.println(mySet.toString_debug(false));
            System.out.println("Reference:");
            System.out.println(reference);
        }

        // test 46
        if (46 == 46) {
            System.out.println("\nContents 46:");
            System.out.println(mySet);
        }

        // test 47

        if (47 == 47) {
            System.out.println("\nAdd 47\n");
            try {
                // add 4N elements

                for (int i = 0; i < 4*N; i++) {
                    String x = randomString(r, 3);
                    System.out.print(x + " ");
                    // add same elements to both
                    mySet.add(x);
                    reference.add(x);
                }
                System.out.println();

                // print both
                // you can use standard toString() after you have implemented iterator
                System.out.println("\nContents:");
                //System.out.println(mySet.toString_debug(false));
                 System.out.println(mySet.toString_debug(true));    // use true to show hash table contents
                System.out.println("Reference:");
                System.out.println(reference);



            } catch (UnsupportedOperationException uoe) {
                System.out.println("47 test catch: " + uoe);
            }



        }

		
	} // main()

        // return random string of length len
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char)(r.nextInt(26) + 'a');
        return new String(C);
    }



	
} // class Set8_test 
