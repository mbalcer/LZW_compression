package pl.edu.pbs.lzw_compression.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.pbs.lzw_compression.algorithm.LZW;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViewController {
    public static final String REGEX_COMPRESSED_DATA = "([0-9]+,\\s)*[0-9]+$";
    @FXML
    private TextArea uncompressedTextLabel;
    @FXML
    private TextArea compressedTextLabel;

    @FXML
    protected void compressTextClick() {
        System.out.println("------------------");
        System.out.println("Kompresja danych");
        String text = uncompressedTextLabel.getText();
        List<Integer> compressed = LZW.compress(text);
        String compressedString = compressed.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("Wynik: " + compressedString);
        compressedTextLabel.setText(compressedString);
    }

    @FXML
    protected void decompressTextClick() {
        System.out.println("------------------");
        System.out.println("Dekompresja danych");
        String text = compressedTextLabel.getText();

        if (Pattern.compile(REGEX_COMPRESSED_DATA).matcher(text).matches()) {
            List<Integer> indexes = Stream.of(text.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            String decompressed = LZW.decompress(indexes);
            System.out.println("Wynik: " + decompressed);
            uncompressedTextLabel.setText(decompressed);
        } else {
            System.out.println("Indeksy zakodowanych danych muszą być w formacie: 1%d, 2%d, ..., n%d");
        }
    }

    @FXML
    protected void clearUncompressedTextLabel() {
        uncompressedTextLabel.clear();
    }

    @FXML
    protected void clearCompressedTextLabel() {
        compressedTextLabel.clear();
    }
}