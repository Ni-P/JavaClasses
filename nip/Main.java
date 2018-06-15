package nip;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	// write your code here

        FileParser fp = new FileParser("C:\\Users\\Niko Pinnis\\IdeaProjects\\development\\src\\nip"+"\\test.txt");
//        FileParser fp = new FileParser(null);

//        System.out.println(fp.getClass().getTypeName());

        System.out.println(fp.readAll().toString());
        System.out.println(fp.readAll().getClass().getSimpleName());

//        System.out.println("qwe".getClass().getTypeName());
        System.out.println("qwerty".getClass().getSimpleName());

//        System.out.println(fp.readNext().getClass().getSimpleName());
//        System.out.println(fp.readNext());
//        System.out.println(fp.readNext());

    }
}
