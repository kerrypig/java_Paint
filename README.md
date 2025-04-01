# Paint Application

This is a Java-based paint application that provides users with a graphical interface to draw various shapes, adjust their properties, and manipulate them on a canvas.

## Features
- **Shape Drawing**: Supports various shapes including:
  - Circle
  - Rectangle
  - Square
  - Oval
  - Squiggle (Freehand drawing)
  - Polyline (Connected line segments)
  - Triangle
  - Selection Box (For selecting and moving shapes)
- **Shape Manipulation**:
  - Drag-and-drop functionality for moving shapes.
  - Undo and Redo operations.
  - Dynamic resizing of shapes (Rectangle, Square, Oval).
- **Customization**:
  - Choose between solid or outline drawing.
  - Set shape thickness.
  - Pick colors using a color picker.

## Design Patterns Used
The application makes use of various design patterns to enhance modularity, expandability, and maintainability.

### Strategy Pattern
The **Strategy Pattern** is used to handle the creation and manipulation of different shapes. By implementing the `ShapeStrategy` interface, various shape strategies like `CircleStrategy`, `RectangleStrategy`, `SquareStrategy`, etc., can handle mouse events independently. This pattern makes it easy to add new shapes or change their behavior without modifying existing code.

### Observer Pattern
The **Observer Pattern** is applied to update the `PaintPanel` when changes occur in the `PaintModel`. The `PaintModel` extends `Observable` and notifies observers (such as `PaintPanel`) whenever a shape is added, removed, or modified. This ensures the view is consistently in sync with the model.

### MVC Pattern
The entire application follows the **Model-View-Controller (MVC) Pattern**:
- **Model**: `PaintModel` - Handles the internal data structure for shapes and provides methods to manipulate them.
- **View**: `PaintPanel`, `ShapeChooserPanel` - Responsible for rendering the shapes and providing user interaction options.
- **Controller**: `View` class - Manages user inputs from menus and keyboard shortcuts, delegating them to the appropriate handlers in the model.

### Command Pattern (Partial Implementation)
The implementation of undo and redo functionalities through methods like `removeLastShape()` and `addOneRemovedShape()` in the `PaintModel` can be extended into a full **Command Pattern** implementation. Each operation could be encapsulated as a command, providing better flexibility and maintainability.


The application is divided into several packages and classes:

### Model
- **`PaintModel`**: Stores all shapes and handles operations like undo, redo, and saving final shapes.
- **`Shape` (Abstract Class)**: Base class for all shapes with common attributes and methods.
- **`Point`**: Represents a 2D point.

### Shapes
- **`Rectangle`, `Square`, `Circle`, `Oval`, `Polyline`, `Squiggle`**: Implementations of different shapes extending the `Shape` class.

### Strategies
- **`ShapeStrategy` (Interface)**: Defines mouse event handling methods.
- **`RectangleStrategy`, `SquareStrategy`, `CircleStrategy`, `OvalStrategy`, `PolylineStrategy`, `SelectionBoxStrategy`, `SquiggleStrategy`, `TriangleStrategy`**: Implement specific handling for each shape type.

### View
- **`PaintPanel`**: Canvas for drawing shapes. Implements `EventHandler<MouseEvent>` and `Observer`.
- **`ShapeChooserPanel`**: UI component to select shapes and configure properties like thickness, color, and fill type.

### Controller
- **`Paint`**: Main application class.

## Usage
1. Run the `Paint` application.
2. Select the desired shape from the `ShapeChooserPanel`.
3. Draw on the canvas by clicking, dragging, or clicking multiple times (for polyline).
4. Use the selection box tool to move shapes around.
5. Adjust thickness, color, and fill type as desired.

## Dependencies
- JavaFX

## Compilation and Execution
To compile and run the application, make sure you have JavaFX set up correctly:

```bash
javac -cp /path/to/javafx/lib/*.jar *.java
java -cp .:/path/to/javafx/lib/*.jar Paint
```

## Future Improvements
- Add support for saving and loading drawings.
- Implement more complex shapes and editing tools.
- Enhance the UI for better usability.

## Author
- qianhe shen
- kairui zhu
- du chen
- yilin wen

