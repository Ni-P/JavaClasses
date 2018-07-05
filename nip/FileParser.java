// FileParser v0.2
// Author Niko Pinnis
// License MIT

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

    private String _target;
    private List<String> _output;
    private Scanner _handle;
    private String _delimiter = "\n";

    public String getDelimiter() {
        return this._delimiter;
    }

    public void setDelimiter(String delimiter) {
        if (this._handle != null) this._handle.useDelimiter(delimiter);
        this._delimiter = delimiter;
    }

    public List<String> get_output() {
        return this._output;
    }


    /**
     * A simple Class for reading the content of a file
     */
    public FileParser() {
        this._target = ".\\test.txt";
    }

    public FileParser(String _target) {
        this._target = _target;
    }


    /**
     *
     Read all lines from a file and return a List of Strings
     On error return null
     If a file path was not provided before calling this return an empty ArrayList
     * @return All lines in a single List
     */
    public List<String> readAll() {
        if (this._target == null) {
            System.out.println("_target was null returning empty list.");
            return new ArrayList<String>();
        }
        try {
            this._output = Files.readAllLines(Paths.get(this._target),StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return this._output;
    }


    /**
     Read he next line from file and return it
     I case of EOF or error return null
     * @return A String with the next piece of data based on the delimiter
     */
    public String readNext() {
        if (_target == null) {
            System.out.println("_target was null returning empty list.");
            return "";
        }

        File file = new File(_target);
        try {
            if(this._handle == null) {
                this._handle = new Scanner(file);
                this._handle.useDelimiter(this._delimiter);
            }
            if (this._handle.hasNext()) return this._handle.next();
            else {
                this._handle.close();
                this._handle = null;
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
