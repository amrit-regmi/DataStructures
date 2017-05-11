
import java.util.*;

public class Coins {

    public static void main(String[] args) {

        // defaults
        int sum = 82;

        if (args.length > 0)
            sum = Integer.valueOf(args[0]);

        Vector<Integer> coins = new Vector<Integer>();

        for (int i = 1; i < args.length; i++)
            coins.add(Integer.valueOf(args[i]));

        if (coins.size() == 0) {
            coins.add(1);
            coins.add(2);
            coins.add(5);
            coins.add(10);
            coins.add(20);
            coins.add(50);
        }

        DsaTimer a;
        int n;

        // initial runs before time measurements
        n = greedyCoins(sum, coins);
        n = greedyCoins(sum, coins);
        n = dynCoins(sum, coins);
        n = dynCoins(sum, coins);


        a = new DsaTimer("Greedy_" + sum);
        n = greedyCoins(sum, coins);
        a.stop();
        System.out.println("Greedy: " + a.micros() + "us coins=" + n);

        if (sum < 35) {
            a = new DsaTimer("Divide_and_conquer_" + sum);
            n = dacCoins(sum, coins);
            a.stop();
            System.out.println("Divide_and_conquer: " + a.micros() + "us coins=" + n);
        }

        a = new DsaTimer("Dynamic_" + sum);
        n = dynCoins(sum, coins);
        a.stop();
        System.out.println("Dynamic: " + a.micros() + "us coins=" + n);

        System.out.println("Coins" + dynCoinsList(sum, coins));
    }

    static int greedyCoins(int sum, Collection<Integer> coins) {
        int[] coin = new int[coins.size()];
        int n = 0;
        for (int c : coins)
            coin[n++] = c;
        java.util.Arrays.sort(coin);

        int r = 0;
        while (sum > 0) {
            for (int j = n-1; j >= 0; j--) {
                if (coin[j] <= sum) {
                    sum -= coin[j];
                    r++;
                    break;
                }
            }
        }
        return r;
    }

    static int dacCoins(int sum, Iterable<Integer> coins) {
        if (sum == 0)
            return 0;
        int result = sum;		// upper limit
        for(Integer c : coins)	// iterate over coins
            if (c <= sum) {
                int r = dacCoins(sum - c, coins) + 1;
                if (r < result)
                    result = r;
            }
        return result;
    }

   static int dynCoins(int sum, Iterable<Integer> coins) {
        int[] results = new int[sum+1];
        
        results[0] = 0;
        for (int i = 1; i <= sum; i++) {
            int min = i+1;
            for(Integer c : coins) {
                if (c <= i) {
                    int r = results[i-c]+1;
                    if (r < min)
                        min = r;
                }
            }
            results[i] = min;
        }
        return results[sum];
    }
    static LinkedList<Integer> dynCoinsList(int sum, Iterable<Integer> coins) {
        int[] results = new int[sum+1];
        int[] lastCoin = new int[sum+1];
        LinkedList<Integer> coin = new LinkedList<Integer>();
        results[0] = 0;
        
        for (int i = 1; i <= sum; i++) {
            int min = i+1;
            for(Integer c : coins) {
                if (c <= i) {
                    int r = results[i-c]+1;
                    if (r < min)
                        min = r;
                    lastCoin[i]=c;
                }
            }
            results[i] = min;
            
        }
        
        while(sum != 0){
        	coin.add(lastCoin[sum]); 
        	sum = sum - lastCoin[sum];
        		
        }
        
        return coin;
    }
}