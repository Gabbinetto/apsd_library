package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

  // Next
  default void Next(Natural amount) {
    long natam = amount.ToLong();
    for (long i = 0L; i < natam; i++)
      DataNNext();
  }

  default void Next(long amount) {
    Next(Natural.Of(amount));
  }

  default void Next() {
    Next(Natural.ONE);
  }

  // DataNNext
  Data DataNNext();

  default boolean ForEachForward(Predicate<Data> fun) {
    if (fun != null) {
      while (IsValid()) {
        if (fun.Apply(DataNNext())) {
          return true;
        }
      }
    }
    return false;
  }

}
