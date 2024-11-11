package ca.utoronto.utm.assignment2.paint;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view;
	private ArrayList<Button> buttons = new ArrayList<>();
	//
	private Button solidButton;
	//
	private Button outlineButton;

	private Button undoButton;

	private TextField thicknessTextField;

	private Button eraserButton;

	private Button backgroundColorButton;

	private ColorPicker backgroundColorPicker;


	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] models = {"Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Oval", "Triangle","SelectionBox"};

		int row = 0;

		//ColorPicker
		ColorPicker colorPicker = new ColorPicker(Color.BLACK);  // Default color is black
		colorPicker.setOnAction(e -> {
			Color selectedColor = colorPicker.getValue();
			this.view.getPaintModel().setCurrentColor(selectedColor);
		});
		this.add(colorPicker, 0, row++);

		//Drawing modes
		for (String mode : models) {
			//
			Button button = createShapeButton(mode, 70, 70);
			button.setMinWidth(80);
			button.setMinHeight(80);
			this.add(button, 0, row);
			this.buttons.add(button);
			row++;
			button.setOnAction(actionEvent -> {
				view.setMode(mode);
				highlightSelectedButton(button);
				view.getPaintModel().setEraserMode(false);
			});
		}


		backgroundColorPicker = new ColorPicker(Color.WHITE);
		Tooltip backgroundTooltip = new Tooltip("Choose a background color");
		backgroundColorPicker.setTooltip(backgroundTooltip);
		backgroundColorPicker.setOnAction(e -> {
			Color selectedBackgroundColor = backgroundColorPicker.getValue();
			setCanvasBackground(selectedBackgroundColor);
		});
		this.add(backgroundColorPicker, 0, row++);

		// Solid/Outline button
		solidButton = new Button("Solid");
		outlineButton = new Button("Outline");
		solidButton.setMinWidth(85);
		outlineButton.setMinWidth(85);
		eraserButton = new Button("Eraser");
		eraserButton.setMinWidth(85);
		this.add(eraserButton, 0, row++);


		this.add(solidButton, 0, row++);
		this.add(outlineButton, 0, row++);

		solidButton.setOnAction(actionEvent -> {
			view.getPaintModel().setIsSolid(true);
			solidButton.setStyle("-fx-background-color: #e6d99b;");
			outlineButton.setStyle("");
		});

		outlineButton.setOnAction(actionEvent -> {
			view.getPaintModel().setIsSolid(false);
			outlineButton.setStyle("-fx-background-color: #e6d99b;");
			solidButton.setStyle("");
		});

		// Thickness text field
		thicknessTextField = new TextField();
		thicknessTextField.setMaxWidth(85);
		thicknessTextField.setMinWidth(85);
		thicknessTextField.setPromptText("Thickness");
		this.add(thicknessTextField, 0, row++);

		thicknessTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean Old, Boolean newValue) {
				try {
					view.getPaintModel().setThickness(Float.parseFloat(thicknessTextField.getText()));
				} catch (NumberFormatException e) {
					thicknessTextField.setText("1.0");
				}
			}
		});

		eraserButton.setOnAction(actionEvent -> {
			view.setMode("Eraser");
			highlightSelectedButton(eraserButton);
			view.getPaintModel().setEraserMode(true);
		});
		buttons.add(eraserButton);



		// undo button
//		undoButton = new Button("undo");
//		undoButton.setMinWidth(85);
//		this.add(undoButton, 0, row++);
//		undoButton.setOnAction(e -> {
//			view.getPaintModel().removeLastShape();
//		});

	}

	private void setCanvasBackground(Color selectedBackgroundColor) {
		view.getPaintPanel().setBackgroundColor(selectedBackgroundColor);
	}

	private void highlightSelectedButton(Button selectedButton) {
		for (Button button : buttons) {
			if (button == selectedButton) {
				button.setStyle("-fx-background-color: rgba(11,9,9,0.44);");
			} else {
				button.setStyle("");
			}
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
				gc.strokeOval(padding, padding, 50, 50);

				break;
			case "Rectangle":
				gc.strokeRect(padding, padding, 50, 30);
				break;
			case "Square":
				gc.strokeRect(padding, padding, 50, 50);
				break;
			case "Squiggle":
				gc.beginPath();
				gc.moveTo(padding, 40);
				gc.quadraticCurveTo(padding + 15, 20, padding + 30, 40);
				gc.quadraticCurveTo(padding + 45, 60, padding + 60, 40);
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
			case "Oval":
				gc.getFillRule();
				gc.strokeOval(padding, padding, 50, 25);
				break;
			case "Triangle":
				double[] x = new double[3];
				double[] y = new double[3];
				x[0] = 35;
				x[1] = 10;
				x[2] = 60;
				y[0] = 8.3;
				y[1] = 50;
				y[2] = 50;
				gc.strokePolygon(x, y, 3);
				break;
			case "SelectionBox":
				gc.setLineDashes(5,5);
				gc.strokeRect(padding, padding, 50, 50);
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


