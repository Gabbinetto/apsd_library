package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.classes.utilities.Natural;

public interface List<Data> extends Chain<Data>, MutableSequence<Data>, InsertableAtSequence<Data> { // Must extend
                                                                                                     // MutableSequence,
                                                                                                     // InsertableAtSequence,
                                                                                                     // and Chain

  // SubList
  default List<Data> SubList(Natural start, Natural end) {
    return (List<Data>) SubChain(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    InsertAt(dat, Natural.ZERO);
    return true;
  }

}
