module pl.edu.pbs.lzw_compression_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.edu.pbs.lzw_compression to javafx.fxml;
    exports pl.edu.pbs.lzw_compression;
    exports pl.edu.pbs.lzw_compression.controller;
    opens pl.edu.pbs.lzw_compression.controller to javafx.fxml;
}