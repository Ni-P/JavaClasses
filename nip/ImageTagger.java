/*  ImageTagger v0.2
    Author Niko Pinnis
    Licence: MIT
*/

package nip;

import java.util.*;

public class ImageTagger {

//    private ArrayList<ArrayList<String>> _images;
    private Hashtable<String,ArrayList<String>> _images;

    private Hashtable<String,ArrayList<String>> _parsedImages;
//    private ArrayList<ArrayList<String>> _parsedImages;


    private ArrayList<String> _lastResults;

    public ImageTagger() {
    }

    public ImageTagger(Hashtable<String,ArrayList<String>> images) {
        this._images = images;
    }

    public boolean loadFromFile(String path) {

        FileParser fp = new FileParser(path);
        List<String> raw = fp.readAll();

        if (raw == null) {
            System.out.println("Error: ImageTagger read null from file.");
            return false;
        }
        _images = new Hashtable<>();
        ArrayList<String> temp = new ArrayList<>();
        for(String str: raw) {
            Collections.addAll(temp, str.split(" "));
            String imageName = temp.get(0);
            temp.remove(0);
            _images.put(imageName,temp);
            temp = new ArrayList<>();
        }

        return true;
    }

    public ArrayList<String> getByTags(String... tag) {

        Hashtable<String,ArrayList<String>> defenciveCopy = new Hashtable<>(this._images);
        this._parsedImages = this._images;
        for (String t : tag) {
            _parsedImages = getByTag(t, _parsedImages);
        }

        ArrayList<String> results = new ArrayList<>();
        _parsedImages.forEach((key,value)-> results.add(key));

        this._images = new Hashtable<>(defenciveCopy);
        this._lastResults = results;
        return results;
    }

    private Hashtable<String,ArrayList<String>> getByTag(String tag, Hashtable<String,ArrayList<String>> images) {

        Hashtable<String,ArrayList<String>> filtered = new Hashtable<>();
        Enumeration<String> imageKeys = images.keys();
        while(imageKeys.hasMoreElements()){
            String key = imageKeys.nextElement();
            ArrayList<String> value = images.get(key);
            value.forEach(t->{
                if(t.equalsIgnoreCase(tag)){
                    filtered.put(key,value);
                }
            });
        }


//        System.out.println(filtered.toString());
        return filtered;

    }

//    public ArrayList<String> getByTags(String... tag) {
//
//        ArrayList<ArrayList<String>> defenciveCopy = new ArrayList<>(this._images);
//        this._parsedImages = this._images;
//        for (String t : tag) {
//            _parsedImages = getByTag(t, _parsedImages);
//        }
//
//        ArrayList<String> results = new ArrayList<>();
//        for (ArrayList<String> arr: _parsedImages) {
//            results.add(arr.get(0));
//        }
//
//        this._images = new ArrayList<>(defenciveCopy);
//        this._lastResults = results;
//        return results;
//    }
//
//    private ArrayList<ArrayList<String>> getByTag(String tag, ArrayList<ArrayList<String>> images) {
//
//        ArrayList<ArrayList<String>> filtered = new ArrayList<>();
//        ListIterator<ArrayList<String>> it = images.listIterator();
//        it.forEachRemaining(element -> {
//            if (element.size() > 1) {
////                element.remove(0);
//                for (String str : element) {
//                    if (str.equalsIgnoreCase(tag)) {
//                        filtered.add(element);
//                    }
//                }
//            }
//        });
//
////        System.out.println(filtered.toString());
//        return filtered;
//
//    }

    public ArrayList<String> get_lastResults() {
        return _lastResults;
    }
}
