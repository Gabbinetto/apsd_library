package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>>
    extends Sequence<Data>, SortedIterableContainer<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer */
  /* ************************************************************************ */

  @Override
  default boolean Exists(Data dat) {
    return Search(dat) != null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    // In ordine, quindi si può usare una ricerca binaria
    long low = 0, high = Size().ToLong() - 1;
    while (low <= high) {
      long mid = low + ((high - low) / 2L);

      int comparison = dat.compareTo(GetAt(Natural.Of(mid)));

      if (comparison == 0)
        return Natural.Of(mid);
      else if (comparison < 0) {
        high = mid - 1L;
      } else if (comparison > 0) {
        low = mid + 1L;
      }
    }

    return null;
  }

}
