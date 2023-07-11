module quiz_pages.projectquizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens quiz_pages to javafx.fxml;
    opens quiz_pages.Controllers to javafx.fxml;
    opens quiz_pages.DesignPatterns.Factory to com.fasterxml.jackson.databind;
    opens quiz_pages.Models to com.fasterxml.jackson.databind;

    exports quiz_pages;
    exports quiz_pages.Controllers;
    exports quiz_pages.DesignPatterns.Factory;
    exports quiz_pages.Models;

}