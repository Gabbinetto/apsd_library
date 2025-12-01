package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

  // Prev
  default void Prev(Natural amount) {
    // TODO: Implementare
  }

  default void Prev(long amount) {
    Prev(Natural.Of(amount));
  }

  default void Prev() {
    Prev(Natural.ONE);
  }

  // DataNPrev
  Data DataNPrev();

  // ForEachBackward
  default boolean ForEachBackward(Predicate<Data> fun) {
    if (fun != null) {
      while (IsValid()) {
        if (fun.Apply(DataNPrev())) {
          return true;
        }
      }
    }
    return false;
  }

}
