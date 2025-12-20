package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.sequences.RemovableAtSequence;
import apsd.classes.utilities.Natural;

public interface Chain<Data> extends Set<Data>, RemovableAtSequence<Data> { // Must extend RemovableAtSequence

  // InsertIfAbsent
  default boolean InsertIfAbsent(Data dat) {
    if (!Exists(dat)) {
      Insert(dat);
      return true;
    }
    return false;
  }

  // RemoveOccurrences
  default void RemoveOccurrences(Data dat) {
    long idx = 0;
    while (idx < Size().ToLong()) {
      Natural nat = Natural.Of(idx);
      if (GetAt(nat).equals(dat)) {
        RemoveAt(nat); // idx+1 diventa idx, quindi no idx++
      } else {
        idx++;
      }
    }
  }

  // SubChain
  default Chain<Data> SubChain(Natural start, Natural end) {
    return (Chain<Data>) SubSequence(start, end);
  }

}
