import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {

        // # of tests
		int N = 10;
		if (args.length > 0)
			N = Integer.valueOf(args[0]);

        // test different implementations by instantiating
        // different classes implementing the same interface
		// Queue<Integer> Q = new Queue_37<Integer>();
		 Queue<Integer> Q = new Queue_38_39<Integer>(N);
		//Queue<Integer> Q = new Queue_40<Integer>();
		//Queue<Integer> Q = new Queue_41_42<Integer>();

        System.out.println("isEmpty: " + Q.isEmpty() + ", size: " + Q.size());

        // N elements to queue
        System.out.print("To Queue: ");
		for (int i = 0; i < N; i++) {
            System.out.print(" " + i);
			Q.offer(i);
        }
        System.out.println();

        System.out.println("isEmpty: " + Q.isEmpty() + ", size: " + Q.size());

        System.out.print("To/From Queue: ");
        // 2N elements to queue and off
		for (int i = N; i < N*3; i++) {
            System.out.print(" T:" + i);
			Q.offer(i);
			int f = Q.poll();
            System.out.print(" F:" + f);
		}
        System.out.println();

        System.out.println("isEmpty: " + Q.isEmpty() + ", size: " + Q.size());

        // 2 pois, 1 tilalle, tulostetaan joka vaiheessa
		while (! Q.isEmpty()) {
			Integer f = Q.poll();
            System.out.print(" F:" + f);
			Q.offer(1);
            System.out.print(" T:" + 1);
			Q.poll();
			f = Q.poll();
            System.out.print(" F:" + f);
            
		}
        System.out.println();

        System.out.println("isEmpty: " + Q.isEmpty() + ", size: " + Q.size());

	} // main() 
}