public class Exercise {
    public static String Calculate(String cidr) {
        String[] parts = cidr.split("/");
        String ip = parts[0];
        int prefix = Integer.parseInt(parts[1]);

        int mask = 0xffffffff << (32 - prefix);
        String maskStr = formatIp((mask >> 24) & 0xff, (mask >> 16) & 0xff, (mask >> 8) & 0xff, mask & 0xff);

        String[] octets = ip.split("\\.");
        int ipNum = (Integer.parseInt(octets[0]) << 24) | (Integer.parseInt(octets[1]) << 16) |
                    (Integer.parseInt(octets[2]) << 8) | Integer.parseInt(octets[3]);
        
        int network = ipNum & mask;
        int broadcast = network | ~mask;

        return "Mask: " + maskStr + " | Range: " + longToIp(network) + " - " + longToIp(broadcast);
    }

    private static String longToIp(int i) {
        return ((i >> 24) & 0xff) + "." + ((i >> 16) & 0xff) + "." + ((i >> 8) & 0xff) + "." + (i & 0xff);
    }

    private static String formatIp(int a, int b, int c, int d) {
        return a + "." + b + "." + c + "." + d;
    }
}