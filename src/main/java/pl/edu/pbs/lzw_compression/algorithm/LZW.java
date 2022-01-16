package pl.edu.pbs.lzw_compression.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {
    private static final Integer ASCII_LENGTH = 256;

    private LZW() {
    }

    /**
     * Compress a string to a list of output symbols.
     */
    public static List<Integer> compress(String uncompressed) {
        //Build the dictionary.
        int dictSize = ASCII_LENGTH;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++)
            dictionary.put("" + (char)i, i);

        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                //Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        //Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));

        System.out.println("Utworzony słownik (wyłączając tablicę ASCII): ");
        dictionary.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= ASCII_LENGTH)
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getValue() + " -> " + entry.getKey()));

        return result;
    }

    /**
     * Decompress a list of output ks to a string.
     */
    public static String decompress(List<Integer> compressed) {
        //Build the dictionary.
        int dictSize = ASCII_LENGTH;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++)
            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compress k: " + k);

            result.append(entry);

            //Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }

        System.out.println("Utworzony słownik (wyłączając tablicę ASCII): ");
        dictionary.entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= ASCII_LENGTH)
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        return result.toString();
    }
}