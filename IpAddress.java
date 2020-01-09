/**
 * IpAddress class that would have attributes for each separate octet in the IPV4 Address
 *
 * @author Ethan Maynard
 */
public class IpAddress {
    private int firstOct;
    private int secondOct;
    private int thirdOct;
    private int fourthOct;

    /**
     * Splits the string IP Address into four integer octets. Given more time, I would have likely error-checked
     * the formatting on each array index, such as verifying the number was between 0 and 255.
     *
     * @param ipString The temporary string to be split into an array of four octets from 0 to 255.
     */
    public IpAddress(String ipString) {
        String[] octects = ipString.split("\\.");

        this.firstOct = Integer.parseInt(octects[0]);
        this.secondOct = Integer.parseInt(octects[1]);
        this.thirdOct = Integer.parseInt(octects[2]);
        this.fourthOct = Integer.parseInt(octects[3]);
    }

    // Getters for when necessary. Setters were not extremely necessary in this use case.
    public int getFirstOct() {
        return firstOct;
    }

    public int getSecondOct() {
        return secondOct;
    }

    public int getThirdOct() {
        return thirdOct;
    }

    public int getFourthOct() {
        return fourthOct;
    }

    // Formatted the IpAddress object's octets into address form (XXX.XXX.XXX.XXX)
    @Override
    public String toString() {
        return firstOct + "." + secondOct + "." + thirdOct + "." + fourthOct;
    }

    /**
     * Compares this IP Address octets to another given IP Address' octets.
     *
     * @param other Given IP Address to compare
     * @return 1 if this IP is > other IP, 0 if equal, -1 if this IP < other IP
     */
    public int compare(IpAddress other) {
        // Nested ifs are ugly. Want to find better method to compare four numbers.
        if (this.getFirstOct() == other.getFirstOct()) {
            if (this.getSecondOct() == other.getSecondOct()) {
                if (this.getThirdOct() == other.getThirdOct()) {
                    // If IP Address matches, should return 0 for last octet match
                    return Integer.compare(this.getFourthOct(), other.getFourthOct()); // Integer compare on last octet
                } else if (this.getThirdOct() > other.getThirdOct()) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (this.getSecondOct() > other.getSecondOct()) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.getFirstOct() > other.getFirstOct()) {
            return 1;
        } else {
            return -1;
        }
    }
}
