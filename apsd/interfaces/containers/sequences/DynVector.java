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
    ShiftRight(num);
    SetAt(dat, num);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural num) {
    Data dat = GetAt(num);
    ShiftLeft(num);
    return dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector */
  /* ************************************************************************ */

  // ShiftLeft
  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    Vector.super.ShiftLeft(pos, num);
    Reduce(num);
  }

  // ShiftRight
  @Override
  default void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long len = num.ToLong();
    if (len <= 0)
      return;

    Expand(num);
    long size = Size().ToLong();

    long wrt = size - 1;
    for (long rdr = wrt - len; wrt < size; rdr++, wrt++) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    for (wrt = idx; wrt < idx + len; wrt++) {
      SetAt(null, Natural.Of(wrt));
    }
  }

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
