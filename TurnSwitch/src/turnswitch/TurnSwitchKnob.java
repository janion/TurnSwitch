package turnswitch;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TurnSwitchKnob {
	
	private Color colour = Color.RED;
	private Pane pane;
	private Circle smallCircle;
	private Rectangle rectangle;
	
	public TurnSwitchKnob() {
		pane = new StackPane();
		
		Pane otherPane = new Pane();
		pane.getChildren().add(otherPane);

		Circle bigCircle = new Circle();
		bigCircle.setFill(Color.TRANSPARENT);
		pane.getChildren().add(bigCircle);
		ChangeListener<Number> listener = (observable, oldVal, newVal) -> bigCircle.setRadius(Math.min(otherPane.getWidth(), otherPane.getHeight()) / 2.0);
		otherPane.widthProperty().addListener(listener);
		otherPane.heightProperty().addListener(listener);

		smallCircle = new Circle();
		smallCircle.setFill(colour);
		smallCircle.radiusProperty().bind(bigCircle.radiusProperty().multiply(0.8));
		pane.getChildren().add(smallCircle);

		rectangle = new Rectangle();
		rectangle.setFill(colour);
		rectangle.widthProperty().bind(bigCircle.radiusProperty());
		rectangle.heightProperty().bind(bigCircle.radiusProperty().multiply(0.2));
		rectangle.translateXProperty().bind(rectangle.widthProperty().divide(2));
		pane.getChildren().add(rectangle);
	}
	
	public void setRotation(double degreeAngle) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new KeyValue(pane.rotateProperty(), degreeAngle, Interpolator.EASE_BOTH)));
		timeline.play();
	}
	
	public Pane getNode() {
		return pane;
	}

}
