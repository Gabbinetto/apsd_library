package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Box;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.IterableContainer;
// import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> { // Must extend IterableContainer

  // GetAt

  // GetFirst
  // GetLast

  // Search

  // IsInBound

  // default long ExcIfOutOfBound(Natural num) {
  //   if (num == null) throw new NullPointerException("Natural number cannot be null!");
  //   long idx = num.ToLong();
  //   if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
  //   return idx;
  // }

  // SubSequence

}
