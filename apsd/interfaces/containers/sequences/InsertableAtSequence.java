package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/**
 * Interface: Sequence con supporto all'inserimento di un dato tramite
 * posizione.
 */
public interface InsertableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  // InsertAt
  void InsertAt(Data dat, Natural num);

  // InsertFirst
  default void InsertFirst(Data dat) {
    InsertAt(dat, Natural.ZERO);
  }

  // InsertLast
  default void InsertLast(Data dat) {
    InsertAt(dat, Size());
  }

}
