// ClosureParser
// Author: Niko Pinnis
// version 0.1
// License MIT

package nip;

//import com.sun.istack.internal.NotNull;

public class ClosureParser {

    public static int countDepth(String string, String[] closures) {
        return countDepth(string, closures[0], closures[1]);
    }


    public static int countDepth(String string, String closureOpening, String closureClosing) {
        if (string == null || string.length() < 2)
            return 0;

        int firstOpening = string.indexOf(closureOpening);
        int lastClosing = string.lastIndexOf(closureClosing);
        if (firstOpening == -1 || lastClosing == -1) return 0;
        if (firstOpening >= lastClosing) return 0;

        StringBuffer s = new StringBuffer(string);
        int level = 0;
        while (parseShallow(String.valueOf(s), closureOpening, closureClosing).length() > 0) {
            s = new StringBuffer(parseShallow(String.valueOf(s), closureOpening, closureClosing));
            level++;
        }

        return level;
    }

    public static String parseDeep(String string, String[] closures) {
        return parseDeep(string, closures[0], closures[1]);
    }

    public static String parseDeep(String string, String closureOpening, String closureClosing) {
        if (closureOpening == null || closureClosing == null)
            return "";
//        if (string.equals(closureOpening + closureClosing)) return "";
        StringBuilder s = new StringBuilder(string.trim());
        int start = s.lastIndexOf(closureOpening);
        int end = s.indexOf(closureClosing);
        if (start == -1 || end == -1) return "";
        if (start >= end) return "";
        return s.substring(start + 1, end);
    }

    public static String parseShallow(String string, String[] closures) {
        return parseShallow(string, closures[0], closures[1]);
    }

    public static String parseShallow(String string, String closureOpening, String closureClosing) {
        if (closureOpening == null || closureClosing == null)
            return "";
//        if(string.equals(closureOpening+closureClosing)) return "";
        StringBuilder s = new StringBuilder(string.trim());
        int start = s.indexOf(closureOpening);
        int end = s.lastIndexOf(closureClosing);
        if (start == -1 || end == -1) return "";
        if(start >= end) return "";
        return string.substring(start + 1, end);
//        return "";
    }

    public static void main(String[] args) {
        System.out.println(parseShallow("{oneleveldeep}", "{", "}"));
        System.out.println(parseShallow("{two{level}deep}", "{", "}"));
        System.out.println(parseShallow("{three{le{v}el}deep}", "{", "}"));
        System.out.println("----------------------------------------------------------------------");
        System.out.println(parseDeep("{oneleveldeep}", "{", "}"));
        System.out.println(parseDeep("{two{level}deep}", "{", "}"));
        System.out.println(parseDeep("{three{le{v}el}deep}", "{", "}"));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(countDepth("{oneleveldeep}", "{", "}"));
        System.out.println(countDepth("{ }", "{", "}"));
        System.out.println("---------------------------------------------------------------------");
        System.out.println(countDepth("{two{level}deep}", "{", "}"));
        System.out.println(countDepth("{{level}}", "{", "}"));
        System.out.println(countDepth("{two{level}two}", "{", "}"));
        System.out.println("---------------------------------------------------------------------");
        System.out.println(countDepth("{three{le{v}el}deep}", "{", "}"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println(countDepth("{a{b{c{D}e}f}g}", "{", "}"));
        System.out.println("----------------------------------------------------------------------");
        System.out.println(countDepth("{{{{{o7}}}}}", "{", "}"));

    }
}

