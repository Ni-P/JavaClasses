/*  ImageTagger v0.1
    Author Niko Pinnis
    Licence: MIT
*/

package nip;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ImageTagger {

    private ArrayList<ArrayList<String>> _images;
    private ArrayList<ArrayList<String>> _parsedImages;
    private ArrayList<String> _lastResults;


    public ImageTagger() {
    }

    public ImageTagger(ArrayList<ArrayList<String>> images) {
        this._images = images;
    }

    public boolean loadFromFile(String path) {

        FileParser fp = new FileParser(path);
        List<String> raw = fp.readAll();

        if (raw == null) {
            System.out.println("Error: ImageTagger could not read from file.");
            return false;
        }
        _images = new ArrayList<ArrayList<String>>();
        ArrayList<String> temp = new ArrayList<>();
        for(String str: raw) {
            for(String element: str.split(" ")) {
                temp.add(element);
            }
            _images.add(temp);
            temp = new ArrayList<>();
        }

        return true;
    }

    public ArrayList<String> getByTags(String... tag) {

        ArrayList<ArrayList<String>> defenciveCopy = new ArrayList<>(this._images);
        this._parsedImages = this._images;
        for (String t : tag) {
            _parsedImages = getByTag(t, _parsedImages);
        }

        ArrayList<String> results = new ArrayList<>();
        for (ArrayList<String> arr: _parsedImages) {
            results.add(arr.get(0));
        }

        this._images = new ArrayList<>(defenciveCopy);
        this._lastResults = results;
        return results;
    }

    private ArrayList<ArrayList<String>> getByTag(String tag, ArrayList<ArrayList<String>> images) {

        ArrayList<ArrayList<String>> filtered = new ArrayList<>();
        ListIterator<ArrayList<String>> it = images.listIterator();
        it.forEachRemaining(element -> {
            if (element.size() > 1) {
//                element.remove(0);
                for (String str : element) {
                    if (str.equalsIgnoreCase(tag)) {
                        filtered.add(element);
                    }
                }
            }
        });

//        System.out.println(filtered.toString());
        return filtered;

    }
}
