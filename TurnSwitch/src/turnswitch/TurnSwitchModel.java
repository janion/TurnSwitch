package turnswitch;

import observable.Observable;
import observable.ObserverManager;
import observable.ObserverManagerImpl;
import observable.ObserverType;

public class TurnSwitchModel implements Observable {
	
	public static final ObserverType<CabDirectionSwitchPosition> POSITION_CHANGED = new ObserverType<>();
	
	private ObserverManager observerManager;
	private CabDirectionSwitchPosition currentPosition;
	
	public TurnSwitchModel() {
		observerManager = new ObserverManagerImpl();
	}
	
	public CabDirectionSwitchPosition getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(CabDirectionSwitchPosition currentPosition) {
		this.currentPosition = currentPosition;
		observerManager.notifyObservers(POSITION_CHANGED, currentPosition);
	}

	@Override
	public ObserverManager getObserverManager() {
		return observerManager;
	}

}
