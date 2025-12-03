package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data>
    extends Vector<Data>, ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data> { // Must
                                                                                                      // extend
                                                                                                      // ResizableContainer,
                                                                                                      // InsertableAtSequence,
                                                                                                      // RemovableAtSequence,
                                                                                                      // and
                                                                                                      // Vector

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence */
  /* ************************************************************************ */

  @Override
  default void InsertAt(Data dat, Natural num) {
    long idx = ExcIfOutOfBound(num);
    Natural size = Size();

    if (size.compareTo(Capacity()) >= 0) {
      Expand();
    }

    SetAt(dat, size);

    ShiftLeft(size, Natural.Of(size.ToLong() - idx));
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural num) {
    long idx = ExcIfOutOfBound(num);
    Data dat = GetAt(num);
    Natural size = Size();

    ShiftRight(num, Natural.Of(size.ToLong() - 1L - idx));

    SetAt(null, size.Decrement());

    if (size.Decrement().ToLong() < Capacity().ToLong() / THRESHOLD_FACTOR)
      Shrink();

    return dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector */
  /* ************************************************************************ */

  @Override
  default DynVector<Data> SubVector(Natural start, Natural end) {
    return (DynVector<Data>) Vector.super.SubVector(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
