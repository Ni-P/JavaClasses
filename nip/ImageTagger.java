package nip;

import java.util.ArrayList;
import java.util.ListIterator;

public class ImageTagger {

    private ArrayList<ArrayList<String>> _images;
    private ArrayList<ArrayList<String>> _parsedImages;


    public ImageTagger() {
    }

    public ImageTagger(ArrayList<ArrayList<String>> images) {
        this._images = images;
    }

    public ArrayList<String> getByTags(String... tag) {

        ArrayList<ArrayList<String>> defenciveCopy = new ArrayList<ArrayList<String>>(this._images);
        this._parsedImages = this._images;
        for (String t : tag) {
            _parsedImages = getByTag(t, _parsedImages);
        }

        ArrayList<String> results = new ArrayList<>();
        for (ArrayList<String> arr: _parsedImages) {
            results.add(arr.get(0));
        }

        this._images = new ArrayList<ArrayList<String>>(defenciveCopy);
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

        System.out.println(filtered.toString());
        return filtered;

    }
}
