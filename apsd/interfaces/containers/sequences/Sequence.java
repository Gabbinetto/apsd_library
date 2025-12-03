package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/**
 * Interface: IterableContainer con supporto alla lettura e ricerca tramite
 * posizione.
 */
public interface Sequence<Data> extends IterableContainer<Data> { // Must extend IterableContainer

  // GetAt
  default Data GetAt(Natural num) {
    ForwardIterator<Data> iter = FIterator();
    long idx = ExcIfOutOfBound(num);
    iter.Next(idx);

    return iter.GetCurrent();
  }

  // GetFirst
  default Data GetFirst() {
    return GetAt(Natural.ZERO);
  }

  // GetLast
  default Data GetLast() {
    return GetAt(IsEmpty() ? Natural.ZERO : Size().Decrement());
  }

  // Search
  default Natural Search(Data dat) {
    Box<Long> idx = new Box<>(-1L);
    if (TraverseForward((element) -> {
      idx.Set(idx.Get() + 1L);
      return (element == null && dat == null) || (element != null && element.equals(dat));
    })) {
      return Natural.Of(idx.Get());
    }

    return null;
  }

  // IsInBound
  default boolean IsInBound(Natural num) {
    return (num.compareTo(Natural.ZERO) >= 0) && (num.compareTo(Size()) < 0);
  }

  default long ExcIfOutOfBound(Natural num) {
    if (num == null)
      throw new NullPointerException("Natural number cannot be null!");
    long idx = num.ToLong();
    if (idx >= Size().ToLong())
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
    return idx;
  }

  // SubSequence
  Sequence<Data> SubSequence(Natural start, Natural end);

}
