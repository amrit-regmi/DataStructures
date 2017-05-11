// helping class to measure time

import java.lang.Comparable;

public class DsaTimer implements Comparable<DsaTimer> {

	long start, end = 0L;
    String name = "";
    String fform = "%.3f";

    // create new clock and start it
    public DsaTimer() {
        start = System.nanoTime();
    }

    // give times also a name that is shown in toString()
    public DsaTimer(String n) {
        this();
        name = n;
    }

    // stop clock, return nanoseconds
    public long stop() {
        end = System.nanoTime();
        return (end - start);
    }

    // new start from zero
    public void start() {
        start = System.nanoTime();
    }

    // restart using old stopped timer
    public void restart() {
        if (end != 0L)
            start = System.nanoTime() - (end - start);
    }

    // elapsed time in nanos
    public long time() {
        long t = end - start;
        if (end == 0L)
            t += System.nanoTime();
        return t;
    }

    // elapsed time in nanos
    public long nanos() {
        long t = end - start;
        if (end == 0L)
            t += System.nanoTime();
        return t;
    }

    // elapsed time in micros
    public double micros() {
        long t = end - start;
        if (end == 0L)
            t += System.nanoTime();
        return t/1000.0;
    }

    // elapsed time in millis
    public double millis() {
        long t = end - start;
        if (end == 0L)
            t += System.nanoTime();
        return t/(1000.0*1000.0);
    }

    // elapsed time in seconds
    public double sek() {
        long t = end - start;
        if (end == 0L)
            t += System.nanoTime();
        return t/(1000.0*1000.0*1000.0);
    }

    // compare two value, null is larger than anything
    public int compareTo(DsaTimer b) {
        if (b == null)
            return -1;
        if (this.time() < b.time())
            return -1;
        else if (this.time() > b.time())
            return 1;
        else
            return 0;
    }

    // compare two value, null is larger than anything
    public boolean equals(DsaTimer b) {
        if (b == null)
            return false;
        if (this.time() == b.time())
            return true;
        else
            return false;
    }

    // string representation, automatic units
    public String toString() {
        long t = end - start;

        if (end == 0L)
            t += System.nanoTime();

        String ts = null;
        if (t < 1000)
            ts = t + " ns";

        else if (t < 100*1000)
            ts = String.format(fform, t/1000.0) + " us";

        else if (t < 100*1000*1000)
            ts = String.format(fform, t/(1000.0*1000.0)) + " ms";

        else 
            ts = String.format(fform, t/(1000.0*1000.0*1000.0)) + " s";


        return name + " " + ts;
    }

}
			
