module pl.edu.pbs.lzw_compression_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.edu.pbs.lzw_compression to javafx.fxml;
    exports pl.edu.pbs.lzw_compression;
}