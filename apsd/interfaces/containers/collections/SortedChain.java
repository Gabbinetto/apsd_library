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
    long low = 0, high = Size().ToLong() - 1;
    long mid = -1;
    while (low <= high) {
      mid = low + ((high + low) / 2L);

      int comparison = dat.compareTo(GetAt(Natural.Of(mid)));

      if (comparison == 0)
        break;
      else if (comparison < 0) {
        high = mid - 1L;
      } else if (comparison > 0) {
        low = mid + 1L;
      }
    }

    while (dat.compareTo(GetAt(Natural.Of(mid))) <= 0) {
      mid--;
      if (mid < 0)
        return null;
    }

    return Natural.Of(mid);
  }

  // SearchSuccessor
  default Natural SearchSuccessor(Data dat) {
    long low = 0, high = Size().ToLong() - 1;
    long mid = -1;
    while (low <= high) {
      mid = low + ((high + low) / 2L);

      int comparison = dat.compareTo(GetAt(Natural.Of(mid)));

      if (comparison == 0)
        break;
      else if (comparison < 0) {
        high = mid - 1L;
      } else if (comparison > 0) {
        low = mid + 1L;
      }
    }

    while (dat.compareTo(GetAt(Natural.Of(mid))) >= 0) {
      mid++;
      if (mid >= Size().ToLong())
        return null;
    }

    return Natural.Of(mid);
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
    return GetAt(SearchPredecessor(dat));
  }

  @Override
  default Data Successor(Data dat) {
    return GetAt(SearchSuccessor(dat));
  }

  @Override
  default Data PredecessorNRemove(Data dat) {
    Natural idx = SearchPredecessor(dat);
    if (idx == null)
      return null;
    Data pred = GetAt(idx);
    RemoveAt(idx);
    return pred;
  }

  @Override
  default Data SuccessorNRemove(Data dat) {
    Natural idx = SearchSuccessor(dat);
    if (idx == null)
      return null;
    Data succ = GetAt(idx);
    RemoveAt(idx);
    return succ;
  }

  // RemovePredecessor

  // RemoveSuccessor

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
