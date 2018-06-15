package nip;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        ImageTaggerTest();

    }

    public static void ImageTaggerTest() {


        ImageTagger tagger = initImageTaggerTest();
        System.out.println("-------------------------------------------");
        System.out.println(tagger.getByTags("tag1").toString());
        System.out.println("-------------------------------------------");

//        tagger = initImageTaggerTest();
        System.out.println("-------------------------------------------");
        System.out.println(tagger.getByTags("tag2","tag1").toString());
        System.out.println("-------------------------------------------");

//        tagger = initImageTaggerTest();
        System.out.println("-------------------------------------------");
        System.out.println(tagger.getByTags("tag3").toString());
        System.out.println("-------------------------------------------");

        System.out.println("-------------------------------------------");
        System.out.println(tagger.getByTags("tag1","tag5","tag2").toString());
        System.out.println("-------------------------------------------");

    }

    private static ImageTagger initImageTaggerTest() {
        ArrayList<ArrayList<String>> testSet = new ArrayList<ArrayList<String>>();
        ArrayList<String> first = new ArrayList<String>();
        first.add("path1");
        first.add("tag1");
        first.add("tag4");
        ArrayList<String> second = new ArrayList<String>();
        second.add("path2");
        second.add("tag1");
        second.add("tag2");
        second.add("tag5");
        ArrayList<String> third= new ArrayList<String>();
        third.add("path3");
        third.add("tag1");
        third.add("tag2");
        third.add("tag3");
        third.add("tag4");

        testSet.add(first);
        testSet.add(second);
        testSet.add(third);

        return new ImageTagger(testSet);
    }

    public static void FileParserTest() {

        FileParser fp = new FileParser("C:\\Users\\Niko Pinnis\\IdeaProjects\\development\\src\\nip"+"\\test.txt");
//        FileParser fp = new FileParser(null);

//        System.out.println(fp.getClass().getTypeName());

        System.out.println(fp.readAll().toString());
//        System.out.println(fp.readAll().getClass().getSimpleName());

//        System.out.println("qwe".getClass().getTypeName());
//        System.out.println("qwerty".getClass().getSimpleName());

//        System.out.println(fp.readNext().getClass().getSimpleName());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());
        System.out.println(fp.readNext());

    }
}
