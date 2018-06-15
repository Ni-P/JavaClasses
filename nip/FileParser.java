// FileParser v0.1
// Author Niko Pinnis

package nip;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {

    private String _target = null;
    private List<String> _output;
    private Scanner _handle;
    private String _delimiter = "\n";

    public String getDelimiter() {
        return _delimiter;
    }

    public void setDelimiter(String delimiter) {
        this._delimiter = delimiter;
    }

    public List<String> get_output() {
        return _output;
    }

    public FileParser() {
        this._target = ".\\test.txt";
    }

    public FileParser(String _target) {
        this._target = _target;
    }

    /*
    Read all lines from a file and return a List of Strings
    On error return null
    If a file path was not provided before calling this return an empty ArrayList
    */
    public List<String> readAll() {
        if (_target == null) {
            System.out.println("_target was null returning empty list.");
            return new ArrayList<String>();
        }
        try {
            _output = Files.readAllLines(Paths.get(_target),StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return _output;
    }

    /*
    Read he next line from file and return it
    I case of EOF or error return null
    */
    public String readNext() {
        if (_target == null) {
            System.out.println("_target was null returning empty list.");
            return "";
        }

        File file = new File(_target);
        try {
            if(_handle == null)_handle = new Scanner(file);
            _handle.useDelimiter(this._delimiter);
            if (_handle.hasNext()) return _handle.next();
            else return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
