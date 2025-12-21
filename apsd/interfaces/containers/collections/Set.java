package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;
import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> {

  // Union
  default void Union(Set<Data> set) {
    if (set == null || set.IsEmpty())
      return;

    set.FIterator().ForEachForward(
        dat -> {
          if (!Exists(dat))
            Insert(dat);
          return false;
        });
  }

  // Difference
  default void Difference(Set<Data> set) {
    if (set == null || set.IsEmpty())
      return;

    set.BIterator().ForEachBackward(
        dat -> {
          if (Exists(dat))
            Remove(dat);
          return false;
        });
  }

  // Intersection
  default void Intersection(Set<Data> set) {
    if (set == null)
      return;

    if (set.IsEmpty()) {
      Clear();
      return;
    }

    BIterator().ForEachBackward(
        dat -> {
          if (!set.Exists(dat))
            Remove(dat);
          return false;
        });
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(IterableContainer<Data> iter) {
    if (!(iter instanceof Set<Data>))
      return false;

    Box<Boolean> equal = new Box<>(true);

    iter.FIterator().ForEachForward(dat -> {
      if (!Exists(dat))
        equal.Set(false);
      return !equal.Get();
    });

    if (!equal.Get())
      return equal.Get();

    FIterator().ForEachForward(dat -> {
      if (!iter.Exists(dat))
        equal.Set(false);
      return !equal.Get();
    });

    return equal.Get();
  }

}
