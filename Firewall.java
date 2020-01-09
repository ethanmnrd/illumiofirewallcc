import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Firewall Class  read CSV file in Java. In this program we will read
 * list of books stored in CSV file as comma separated values.
 * <p>
 * CSV File reading logic inspired by: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
 *
 * @author Ethan Maynard
 */
public class Firewall {
    private List<Rule> rules;

    Firewall(String rulesPath) {
        this.rules = getRules(rulesPath);
    }

    public List<Rule> getRules() {
        return rules;
    }

    // Overrides default toString to print formatted rules. Likely a faster way to do so than an iterative loop.
    @Override
    public String toString() {
        String ruleString = "";
        for (Rule r : rules) {
            ruleString += r.toString() + ", ";
        }
        return ruleString;
    }

    /**
     * accept_packet tests the given packet input by sequentially checking each part of each rule.
     * If at any point the nested ifs fail, the code would short-circuit, which saves time for each rule.
     * Given the possibility of the input falling into any one rule, accept_packet has to search all rules within
     * the firewall.
     * <p>
     * The hardest two to figure out were the port and IP address inputs. I used a Range class to hold the low and high
     * ends of the rules for these two, and compared them with custom methods for a single input check.
     *
     * @param direction  "inbound" or "outbound" to look for in rules
     * @param protocol   "tcp" or "udp" to look for in rules
     * @param port       1 to 65535 number to look for in the range of allowed ports
     * @param ip_address IPV4 address to look for in the range of allowed IP Addresses
     * @return boolean "true" if packet is accepted (in rules), "false" otherwise
     */
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
        IpAddress ip = new IpAddress(ip_address);
        boolean accept = false;
        for (Rule r : rules) {
            // Packet will match rule if its direction and protocol are within a rule, and within those rules, the port
            // and IP either match or fall within the range of ports and IP addresses for those rules.
            // Otherwise, packet will not be accepted
            if (r.getDirection().equals(direction)) {
                if (r.getProtocol().equals(protocol)) {
                    if (r.getPort().contains(port)) {
                        if (r.getIP().getLowIp().compare(ip) <= 0 && r.getIP().getHighIp().compare(ip) >= 0) {
                            accept = true;
                        }
                    }
                }
            }
        }
        return accept;
    }

    /**
     * getRules would parse the given CSV file for all the rules to be initialized for the Firewall.
     * Unfortunately, this is likely not time efficient, and if I had more time to optimize this, I would have.
     *
     * @param fileName the path to the csv file
     * @return returns a created list of rules (the firewall)
     */
    public static List<Rule> getRules(String fileName) {
        List<Rule> rules = new ArrayList<Rule>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            // This section needs validation. currently no way to verify proper formatting, edge cases, etc.
            while (line != null) {
                String[] inputs = line.split(",");

                Rule rule = new Rule(inputs[0], inputs[1], inputs[2], inputs[3]);

                rules.add(rule);

                line = br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rules;
    }

    // I used the main class to test my code. Given more time, I would have implemented a small JUnit test suite
    // to assert the results.
    public static void main(String[] args) {
        Firewall fw = new Firewall("C:\\Users\\Ethan\\Desktop\\illumio coding\\src\\sample.csv");
        System.out.println(fw.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
        System.out.println(fw.accept_packet("inbound", "udp", 53, "192.168.2.1"));
        System.out.println(fw.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
        System.out.println(fw.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
        System.out.println(fw.accept_packet("inbound", "udp", 24, "52.12.48.92"));
    }
}
