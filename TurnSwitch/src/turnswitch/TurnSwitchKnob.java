package turnswitch;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TurnSwitchKnob {

	private Pane pane;
	private Circle bigCircle;
	private Circle smallCircle;
	private Rectangle pointerRectangle;
	private Rectangle notchRectangle;
	
	public TurnSwitchKnob() {
		pane = new StackPane();
		
		Pane otherPane = new Pane();
		otherPane.setMouseTransparent(true);
		pane.getChildren().add(otherPane);

		bigCircle = new Circle();
		bigCircle.setFill(Color.TRANSPARENT);
		pane.getChildren().add(bigCircle);
		ChangeListener<Number> listener = (observable, oldVal, newVal) -> bigCircle.setRadius(Math.min(otherPane.getWidth(), otherPane.getHeight()) / 2.0);
		otherPane.widthProperty().addListener(listener);
		otherPane.heightProperty().addListener(listener);

		smallCircle = new Circle();
		smallCircle.getStyleClass().add("turnswitch-knob");
		smallCircle.radiusProperty().bind(bigCircle.radiusProperty().multiply(0.6));
		smallCircle.strokeWidthProperty().bind(smallCircle.radiusProperty().multiply(0.2));

		pointerRectangle = new Rectangle();
		pointerRectangle.getStyleClass().add("turnswitch-pointer");
		pointerRectangle.widthProperty().bind(bigCircle.radiusProperty().multiply(0.95));
		pointerRectangle.heightProperty().bind(bigCircle.radiusProperty().multiply(0.2));
		pointerRectangle.arcHeightProperty().bind(pointerRectangle.heightProperty().multiply(0.5));
		pointerRectangle.arcWidthProperty().bind(pointerRectangle.heightProperty().multiply(0.5));
		
		ChangeListener<Number> sizeOrAngleChangeListener = (observable, oldVal, newVal) -> knobSizeOrAngleChanged();
		pointerRectangle.widthProperty().addListener(sizeOrAngleChangeListener);
		pointerRectangle.rotateProperty().addListener(sizeOrAngleChangeListener);

		notchRectangle = new Rectangle();
		notchRectangle.getStyleClass().add("turnswitch-notch");
		notchRectangle.widthProperty().bind(bigCircle.radiusProperty().multiply(0.1));
		notchRectangle.heightProperty().bind(pointerRectangle.heightProperty().multiply(0.1));
		notchRectangle.rotateProperty().bind(pointerRectangle.rotateProperty());

		pane.getChildren().add(pointerRectangle);
		pane.getChildren().add(notchRectangle);
		pane.getChildren().add(smallCircle);
		
		pane.setMinSize(100, 100);
	}
	
	private void knobSizeOrAngleChanged() {
		double angle = (pointerRectangle.getRotate() * Math.PI) / 180;
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);

		pointerRectangle.setTranslateX(pointerRectangle.getWidth() * 0.5 * cos);
		pointerRectangle.setTranslateY(pointerRectangle.getWidth() * 0.5 * sin);

		notchRectangle.setTranslateX((pointerRectangle.getWidth() - (notchRectangle.getWidth() * 0.5)) * cos);
		notchRectangle.setTranslateY((pointerRectangle.getWidth() - (notchRectangle.getWidth() * 0.5)) * sin);
	}
	
	public DoubleProperty radiusProperty() {
		return bigCircle.radiusProperty();
	}
	
	public void setRotation(double degreeAngle) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new KeyValue(pointerRectangle.rotateProperty(), degreeAngle, Interpolator.EASE_BOTH)));
		timeline.play();
	}
	
	public Pane getNode() {
		return pane;
	}

}
