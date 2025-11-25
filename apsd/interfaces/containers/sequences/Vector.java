package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> { // Must extend ReallocableContainer and MutableSequence

  // ShiftLeft

  // default void ShiftLeft(Natural pos, Natural num) {
  //   long idx = ExcIfOutOfBound(pos);
  //   long size = Size().ToLong();
  //   long len = num.ToLong();
  //   len = (len <= size - idx) ? len : size - idx;
  //   if (len > 0) {
  //     long iniwrt = idx;
  //     long wrt = iniwrt;
  //     for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
  //       Natural natrdr = Natural.Of(rdr);
  //       SetAt(GetAt(natrdr), Natural.Of(wrt));
  //       SetAt(null, natrdr);
  //     }
  //     for (; wrt - iniwrt < len; wrt++) {
  //       SetAt(null, Natural.Of(wrt));
  //     }
  //   }
  // }

  // ShiftFirstLeft

  // ShiftLastLeft

  // ShiftRight

  // ShiftFirstRight

  // ShiftLastRight

  // SubVector

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...

}
