package pl.edu.pbs.lzw_compression.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.pbs.lzw_compression.algorithm.LZW;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViewController {
    @FXML
    private TextArea uncompressedTextLabel;
    @FXML
    private TextArea compressedTextLabel;

    @FXML
    protected void compressTextClick() {
        String text = uncompressedTextLabel.getText();
        List<Integer> compressed = LZW.compress(text);
        String compressedString = compressed.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        compressedTextLabel.setText(compressedString);
    }

    @FXML
    protected void decompressTextClick() {
        String text = compressedTextLabel.getText();
        List<Integer> indexes = Stream.of(text.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String decompressed = LZW.decompress(indexes);
        uncompressedTextLabel.setText(decompressed);
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