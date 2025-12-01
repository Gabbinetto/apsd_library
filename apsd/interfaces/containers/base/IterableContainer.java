package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> { // Must extend TraversableContainer

  // FIterator
  ForwardIterator<Data> FIterator();

  // BIterator
  BackwardIterator<Data> BIterator();

  // IsEqual
  default boolean IsEqual(IterableContainer<Data> iter) {
    Box<Boolean> equal = new Box<>(true);
    TraverseForward(
      dat -> {
        equal.Set(dat.equals(iter.FIterator().GetCurrent()));
        return !equal.Get();
      }
    );
    return equal.Get();
  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> pred) {
    return FIterator().ForEachForward(pred);
  }

  @Override
  default boolean TraverseBackward(Predicate<Data> pred) {
    return BIterator().ForEachBackward(pred);
  }

}
