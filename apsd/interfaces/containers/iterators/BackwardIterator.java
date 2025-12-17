package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

  // Prev
  default void Prev(Natural amount) {
    long natam = amount.ToLong();
    for (long i = 0L; i < natam && IsValid(); i++)
      Prev();
  }

  default void Prev(long amount) {
    Prev(Natural.Of(amount));
  }

  void Prev();

  // DataNPrev
  default Data DataNPrev() {
    Data dat = GetCurrent();
    Prev();
    return dat;
  }

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
