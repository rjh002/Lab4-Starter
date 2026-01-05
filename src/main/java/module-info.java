module edu.ucsd.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.ucsd.todolist to javafx.fxml;
    exports edu.ucsd.todolist;
}
