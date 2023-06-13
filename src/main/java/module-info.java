module com.amirheshmati.javaproj {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.amirheshmati.javaproj to javafx.fxml;
    exports com.amirheshmati.javaproj;
}