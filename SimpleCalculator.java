import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private TextField firstNumberField;
    private TextField secondNumberField;
    private TextField resultField;

    @Override
    public void start(Stage primaryStage) {
        firstNumberField = new TextField();
        secondNumberField = new TextField();
        resultField = new TextField();
        resultField.setEditable(false);

        firstNumberField.setMaxWidth(100);
        secondNumberField.setMaxWidth(100);
        resultField.setMaxWidth(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        String[][] buttonLabels = {
            {"+", "-"},
            {"*", "/"}
        };

        for (int row = 0; row < buttonLabels.length; row++) {
            for (int col = 0; col < buttonLabels[row].length; col++) {
                String label = buttonLabels[row][col];
                Button button = new Button(label);
                button.setOnAction(e -> handleOperation(label));
                grid.add(button, col, row);
            }
        }

        VBox resultBox = new VBox(5, new Label("Result:"), resultField);
        resultBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
            new HBox(5, new VBox(5, new Label("First Number:"), firstNumberField), 
                           new VBox(5, new Label("Second Number:"), secondNumberField)),
            resultBox,
            grid
        );

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.show();
    }

    private void handleOperation(String operation) {
        try {
            double firstNumber = Double.parseDouble(firstNumberField.getText());
            double secondNumber = Double.parseDouble(secondNumberField.getText());
            double result = 0;
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        resultField.setText("Error: Division by zero");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }
            resultField.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            resultField.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
