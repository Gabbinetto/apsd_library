package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/**
 * Interface: Sequence con supporto alla rimozione di un dato tramite posizione.
 */
public interface RemovableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  // RemoveAt
  default void RemoveAt(Natural num) {
    AtNRemove(num);
  }

  // AtNRemove
  Data AtNRemove(Natural num);

  // RemoveFirst
  default void RemoveFirst() {
    FirstNRemove();
  }

  // FirstNRemove
  default Data FirstNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Container is empty!");
    }
    return AtNRemove(Natural.ZERO);
  }

  // RemoveLast
  default void RemoveLast() {
    LastNRemove();
  }

  // LastNRemove
  default Data LastNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Container is empty!");
    }
    return AtNRemove(Size().Decrement());
  }

}
