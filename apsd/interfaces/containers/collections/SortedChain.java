package apsd.interfaces.containers.collections;

// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data> { // Must extend OrderedChain and SortedSequence

  // SearchPredecessor

  // SearchSuccessor

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  // default void Intersection(SortedChain<Data> chn) {
  //   Natural i = Natural.ZERO, j = Natural.ZERO;
  //   while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
  //     int cmp = GetAt(i).compareTo(chn.GetAt(j));
  //     if (cmp < 0) {
  //       RemoveAt(i);
  //     } else {
  //       j = j.Increment();
  //       if (cmp == 0) { i = i.Increment(); }
  //     }
  //   }
  //   while (i.compareTo(Size()) < 0) {
  //     RemoveAt(i);
  //   }
  // }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...

}
