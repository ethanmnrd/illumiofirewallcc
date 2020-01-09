/**
 * Rule class to hold the attributes of each Firewall rule
 *
 * @author Ethan Maynard
 */
public class Rule {
    private String direction;
    private String protocol;
    private Range port;
    private Range ip_address;

    /**
     * Constructor takes string values direction, protocol, port, and ip_address. It sets
     * these to proper format (e.g. port to Range object and ip_address to Range object containing
     * IpAddress objects for the low and high).
     *
     * @param direction:  "inbound" or "outbound"
     * @param protocol:   “tcp” or “udp”
     * @param port:       range of values within [1, 65535]
     * @param ip_address: range of IP values within [0.0.0.0, 255.255.255.255]
     */
    public Rule(String direction, String protocol, String port, String ip_address) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = makeRange(port, "port");
        this.ip_address = makeRange(ip_address, "ip");
    }

    /**
     * makeRange creates a range with low and high arguments
     *
     * @param strRange: temporary string formatted version of the range to be made
     * @param type:     switch argument for port range or ip range types (added for reuse of code)
     * @return The newly created range with two attributes: low and high.
     */
    public Range makeRange(String strRange, String type) {
        String[] allowed = strRange.split("-");
        Range range = null;

        // Similar logic for both port and ip; difference is which Range constructor is called.
        if (type == "port") {
            int lowIndex = toInt(allowed[0]);

            if (allowed.length == 1) {
                range = new Range(lowIndex, lowIndex);
            } else {
                int highIndex = toInt(allowed[1]);
                range = new Range(lowIndex, highIndex);
            }
        } else if (type == "ip") {
            IpAddress lowIp = new IpAddress(allowed[0]);

            if (allowed.length == 1) {
                range = new Range(lowIp, lowIp);
            } else {
                IpAddress highIp = new IpAddress(allowed[1]);
                range = new Range(lowIp, highIp);
            }
        }
        return range;
    }

    public int toInt(String intString) {
        return Integer.parseInt(intString);
    }

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }

    public Range getPort() {
        return port;
    }

    public Range getIP() {
        return ip_address;
    }

    // Make rules look good when calling their toString
    @Override
    public String toString() {
        return "Rule [direction= " + direction + ", protocol= " + protocol + ", port= " + port.toString() + ", ip_address= "
                + ip_address.ipToString() + "]";
    }
}
