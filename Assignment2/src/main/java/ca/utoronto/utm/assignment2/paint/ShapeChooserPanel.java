package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;

        public ShapeChooserPanel(View view) {

                this.view = view;

                String[] models = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };

                int row = 0;
                for (String mode : models) {
                        //
                        Button button = createShapeButton(mode, 70, 70);
                        button.setMinWidth(80);
                        button.setMinHeight(80);
                        this.add(button, 0, row);
                        row++;
                        button.setOnAction(e -> view.setMode(mode) );
                }
        }

        private Button createShapeButton(String shape, double width, double height) {
                Canvas canvas = new Canvas(70, 70);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                double padding = 10;

                switch (shape) {
                        case "Circle":
                                gc.getFillRule();
                                gc.strokeOval(padding, padding, 50, 50);  // 绘制较小的圆形

                                break;
                        case "Rectangle":
                                gc.strokeRect(padding, padding, 50, 30);  // 绘制较小的矩形
                                break;
                        case "Square":
                                gc.strokeRect(padding, padding, 50, 50);  // 绘制较小的正方形
                                break;
                        case "Squiggle":
                                gc.beginPath();
                                gc.moveTo(padding, 35);
                                gc.lineTo(20, padding);
                                gc.lineTo(40, 35);
                                gc.lineTo(60, 60);
                                gc.lineTo(70, 35);
                                gc.stroke();
                                break;
                        case "Polyline":
                                gc.beginPath();
                                gc.moveTo(padding, 50);
                                gc.lineTo(30, padding);
                                gc.lineTo(50, 50);
                                gc.lineTo(70, padding);
                                gc.stroke();
                                break;
                        default:
                                break;
                }


                Button shapeButton = new Button();
                shapeButton.setMinSize(80, 80);
                shapeButton.setGraphic(canvas);
                return shapeButton;
        }





        //
        @Override
        public void handle(ActionEvent event) {
                String command = ((Button) event.getSource()).getText();
                view.setMode(command);
                System.out.println(command);
        }
}


