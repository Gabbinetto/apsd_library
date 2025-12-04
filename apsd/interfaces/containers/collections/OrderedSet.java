package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.BackwardIterator;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data> { // Must extend Set

  // Min
  default Data Min() {
    return FoldForward((dat, min) -> {
      return (min == null || dat.compareTo(min) < 0) ? dat : min;
    }, null);
  }

  // RemoveMin
  default void RemoveMin() {
    MinNRemove();
  }

  // MinNRemove
  default Data MinNRemove() {
    Data min = Min();
    Remove(min);
    return min;
  }

  // Max
  default Data Max() {
    return FoldForward((dat, max) -> {
      return (max == null || dat.compareTo(max) > 0) ? dat : max;
    }, null);
  }

  // RemoveMax
  default void RemoveMax() {
    MaxNRemove();
  }

  // MaxNRemove
  default Data MaxNRemove() {
    Data max = Max();
    Remove(max);
    return max;
  }

  // Predecessor
  default Data Predecessor(Data dat) {
    ForwardIterator<Data> iter = FIterator();
    Data prev = iter.GetCurrent();
    iter.Next();
    if (prev.compareTo(dat) == 0)
      return null; // Se dat è il primo elemento, non c'è predecessore

    Data current = null;
    while (iter.IsValid()) {
      current = iter.GetCurrent();
      if (current.equals(dat))
        return prev;
      prev = current;
      iter.Next();
    }

    return null;
  }

  // RemovePredecessor
  default void RemovePredecessor(Data dat) {
    PredecessorNRemove(dat);
  }

  // PredecessorNRemove
  default Data PredecessorNRemove(Data dat) {
    Data prev = Predecessor(dat);
    Remove(prev);
    return prev;
  }

  // Successor
  default Data Successor(Data dat) {
    BackwardIterator<Data> iter = BIterator();
    Data succ = iter.DataNPrev();
    if (succ.equals(dat))
      return null; // Se dat è l'ultimo elemento, non c'è successore

    Data current = null;
    while (iter.IsValid()) {
      current = iter.GetCurrent();
      if (current.compareTo(dat) == 0)
        return succ;
      succ = current;
      iter.Prev();
    }

    return null;
  }

  // RemoveSuccessor
  default void RemoveSuccessor(Data dat) {
    SuccessorNRemove(dat);
  }

  // SuccessorNRemove
  default Data SuccessorNRemove(Data dat) {
    Data succ = Successor(dat);
    Remove(succ);
    return succ;
  }

}
