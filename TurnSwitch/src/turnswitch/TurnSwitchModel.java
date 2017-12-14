package turnswitch;

import observable.Observable;
import observable.ObserverManager;
import observable.ObserverManagerImpl;
import observable.ObserverType;

public class TurnSwitchModel implements Observable {
	
	public static final ObserverType<TurnSwitchPosition> POSITION_CHANGED = new ObserverType<>();
	
	private ObserverManager observerManager;
	private TurnSwitchPosition currentPosition;
	
	public TurnSwitchModel() {
		observerManager = new ObserverManagerImpl();
	}
	
	public TurnSwitchPosition getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(TurnSwitchPosition currentPosition) {
		this.currentPosition = currentPosition;
		observerManager.notifyObservers(POSITION_CHANGED, currentPosition);
	}

	@Override
	public ObserverManager getObserverManager() {
		return observerManager;
	}

}
