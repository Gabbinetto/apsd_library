package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data> { // Must extend ReallocableContainer
                                                                                    // and MutableSequence

  // ShiftLeft
  default void ShiftLeft(Natural pos) {
    ShiftLeft(pos, Natural.ONE);
  }

  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long cap = Capacity().ToLong();
    long len = num.ToLong();
    len = (len <= cap - idx - 1) ? len : cap - idx - 1;
    if (len <= 0)
      return;

    long iniwrt = idx;
    long wrt = iniwrt;
    for (long rdr = wrt + len; rdr < cap; rdr++, wrt++) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    for (; wrt - iniwrt < len; wrt++) {
      SetAt(null, Natural.Of(wrt));
    }
  }

  // ShiftFirstLeft
  default void ShiftFirstLeft() {
    ShiftLeft(Natural.ZERO);
  }

  // ShiftLastLeft
  default void ShiftLastLeft() {
    ShiftLeft(Size().Decrement());
  }

  // ShiftRight
  default void ShiftRight(Natural pos) {
    ShiftRight(pos, Natural.ONE);
  }

  default void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long cap = Capacity().ToLong();
    long len = num.ToLong();
    len = (len <= cap - idx - 1) ? len : cap - idx - 1;
    if (len <= 0)
      return;

    long wrt = cap - 1;
    for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    long lastwrt = wrt + 1;
    for (wrt = idx + 1; wrt < lastwrt; wrt++) {
      SetAt(null, Natural.Of(wrt));
    }
  }

  // ShiftFirstRight
  default void ShiftFirstRight() {
    ShiftRight(Natural.ZERO);
  }

  // ShiftLastRight
  default void ShiftLastRight() {
    ShiftRight(Size().Decrement());
  }

  // SubVector
  default Vector<Data> SubVector(Natural start, Natural end) {
    return (Vector<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  default Natural Size() {
    return Capacity();
  }

}
