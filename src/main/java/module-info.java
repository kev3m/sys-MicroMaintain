module micromaintainsys {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires com.dlsc.formsfx;

    opens micromaintainsys.model;
    opens micromaintainsys.control to javafx.fxml;

    opens micromaintainsys to javafx.fxml;
    exports micromaintainsys;
    exports micromaintainsys.control;
}