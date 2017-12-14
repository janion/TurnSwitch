package turnswitch;

public enum CabDirectionSwitchPosition implements TurnSwitchPosition {
	
	ON("On", -90),
	OFF("Off", 0),
	REVERSE("Reverse", 90);
	
	private String text;
	private double angle;
	
	private CabDirectionSwitchPosition(String text, double angle) {
		this.text = text;
		this.angle = angle;
	}

	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public double getAngle() {
		return angle;
	}

}
