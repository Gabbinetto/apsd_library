package apsd.interfaces.containers.iterators;

/** Interface: Iteratore mutabile. */
public interface MutableIterator<Data> extends Iterator<Data> { // Must extend Iterator

  // SetCurrent
  void SetCurrent(Data dat);

}
