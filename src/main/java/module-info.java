module Resume {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires kotlin.stdlib;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.scratchpad;
    requires org.apache.poi.examples;
    requires org.apache.poi.ooxml;
    requires org.apache.xmlbeans;
    requires org.apache.commons.io;
    requires org.apache.commons.codec;
    requires org.apache.poi.xwpf.converter;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.commons.logging;
    requires mail;
    requires jakarta.activation;
//    requires org.opencv.core.Core;

    opens com.example.project1 to javafx.fxml;
    exports com.example.project1;
}