module micromaintainsys {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires com.dlsc.formsfx;
    requires pdfbox.app;

    opens micromaintainsys.model;
    opens micromaintainsys.control to javafx.fxml;

    opens micromaintainsys to javafx.fxml;
    exports micromaintainsys;
    exports micromaintainsys.control;
    exports micromaintainsys.control.management_Controllers;
    opens micromaintainsys.control.management_Controllers to javafx.fxml;
}