module MicroMaintain {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens micromaintainsys.model;
    opens micromaintainsys.control to javafx.graphics, javafx.fxml;

    opens micromaintainsys to javafx.fxml;
    exports micromaintainsys;
    exports micromaintainsys.control;
}