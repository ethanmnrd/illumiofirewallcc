/**
 * Class inspired by StackOverflow post: https://stackoverflow.com/a/7721381
 * Range Class to hold a low and high value. Used for port and IP Address
 *
 * @author Ethan Maynard
 */
public class Range {
    private int low;
    private int high;

    private IpAddress lowIp;
    private IpAddress highIp;

    /**
     * Constructor for port Ranges
     *
     * @param low (int) low end of the range
     * @param high (int) high end of range
     */
    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Constructor for IP Ranges
     *
     * @param lowIp (IpAddress) low end for range
     * @param highIp (IpAddress) high end for range
     */
    public Range(IpAddress lowIp, IpAddress highIp) {
        this.lowIp = lowIp;
        this.highIp = highIp;
    }

    public IpAddress getLowIp() {
        return lowIp;
    }

    public IpAddress getHighIp() {
        return highIp;
    }

    // Only works for port ranges.
    // Wanted to attempt to combine logic for both types of ranges (port and IP) but ran out of time
    public boolean contains(int number) {
        return number >= low && number <= high;
    }

    // toString for port ranges (default override)
    @Override
    public String toString() {
        return low + "-" + high;
    }

    // toString For IP Ranges (custom method)
    public String ipToString() {
        return lowIp.toString() + "-" + highIp.toString();
    }
}
