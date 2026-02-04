module edu.ucsd.spendingtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.ucsd.spendingtracker to javafx.fxml;
    opens edu.ucsd.spendingtracker.view.charts to javafx.fxml;
    exports edu.ucsd.spendingtracker;
}
