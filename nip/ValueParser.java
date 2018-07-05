// ValueParser v0.1
// Author Niko Pinnis
// License MIT

package nip;

public class ValueParser {

    public static int parseInt(String s) {
        try {
            return parseInteger(s);
        } catch (NullPointerException ex) {
            return Integer.MIN_VALUE;
        }
    }

    private static Integer parseInteger(String s) {
        try {
            s=s.trim();
//            s.replaceAll("","");
            int v = Integer.parseInt(s);
            return v;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}