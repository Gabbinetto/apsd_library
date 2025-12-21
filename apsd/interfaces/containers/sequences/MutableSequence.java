package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/**
 * Interface: Sequence & MutableIterableContainer con supporto alla scrittura
 * tramite posizione.
 */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

  // SetAt
  default void SetAt(Data dat, Natural num) {
    MutableForwardIterator<Data> iter = FIterator();
    long idx = ExcIfOutOfBound(num);

    iter.Next(idx);
    iter.SetCurrent(dat);
  }

  // GetNSetAt
  default Data GetNSetAt(Data dat, Natural num) {
    Data old = GetAt(num);
    SetAt(dat, num);
    return old;
  }

  // SetFirst
  default void SetFirst(Data dat) {
    if (Size().IsZero())
      throw new IndexOutOfBoundsException("MutableSequence SetFirst(): sequence is empty.");
    SetAt(dat, Natural.ZERO);
  }

  // GetNSetFirst
  default Data GetNSetFirst(Data dat) {
    return GetNSetAt(dat, Natural.ZERO);
  }

  // SetLast
  default void SetLast(Data dat) {
    if (Size().IsZero())
      throw new IndexOutOfBoundsException("MutableSequence SetLast(): sequence is empty.");
    SetAt(dat, Size().Decrement());
  }

  // GetNSetLast
  default Data GetNSetLast(Data dat) {
    return GetNSetAt(dat, Size().Decrement());
  }

  // Swap
  default void Swap(Natural from, Natural to) {
    Data tmp = GetAt(from);
    SetAt(GetAt(to), from);
    SetAt(tmp, to);

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  MutableSequence<Data> SubSequence(Natural start, Natural end);

}
