package observable;

public interface Observer<T> {
	
	public void notify(T value);

}
