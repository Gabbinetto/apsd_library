package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must
                                                                                                                       // extend
                                                                                                                       // OrderedChain
                                                                                                                       // and
                                                                                                                       // SortedSequence

  // SearchPredecessor
  default Natural SearchPredecessor(Data dat) {
    return Search(Predecessor(dat));
  }

  // SearchSuccessor
  default Natural SearchSuccessor(Data dat) {
    return Search(Successor(dat));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  default Data Min() {
    return GetAt(Natural.ZERO);
  }

  @Override
  default Data Max() {
    return GetAt(Size().Decrement());
  }

  @Override
  default void RemoveMin() {
    RemoveAt(Natural.ZERO);
  }

  @Override
  default void RemoveMax() {
    RemoveAt(Size().Decrement());
  }

  @Override
  default Data MinNRemove() {
    Data min = Min();
    RemoveAt(Natural.ZERO);
    return min;
  }

  @Override
  default Data MaxNRemove() {
    Data max = Max();
    RemoveAt(Size().Decrement());
    return max;
  }

  @Override
  default Data Predecessor(Data dat) {
    Natural idx = Search(dat);
    if (idx.compareTo(Natural.ZERO) == 0)
      return null;

    return GetAt(idx.Decrement());
  }

  @Override
  default Data Successor(Data dat) {
    Natural idx = Search(dat);
    if (idx.compareTo(Size().Decrement()) == 0)
      return null;

    return GetAt(idx.Decrement());
  }

  /* ************************************************************************ */
  /* Override specific member functions from Set */
  /* ************************************************************************ */

  default void Intersection(SortedChain<Data> chn) {
    Natural i = Natural.ZERO, j = Natural.ZERO;
    while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
      int cmp = GetAt(i).compareTo(chn.GetAt(j));
      if (cmp < 0) {
        RemoveAt(i);
      } else {
        j = j.Increment();
        if (cmp == 0) {
          i = i.Increment();
        }
      }
    }
    while (i.compareTo(Size()) < 0) {
      RemoveAt(i);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    return SortedSequence.super.Search(dat);
  }
}
