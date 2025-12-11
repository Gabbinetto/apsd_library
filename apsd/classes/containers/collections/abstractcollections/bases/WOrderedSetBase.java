package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>>
    extends WSetBase<Data, Chn> implements OrderedSet<Data> { // Must extend
                                                              // WSetBase and
                                                              // implement
                                                              // OrderedSet;
                                                              // Chn must
                                                              // extend
                                                              // SortedChain

  // WOrderedSetBase
  public WOrderedSetBase() {
    super();
  }

  public WOrderedSetBase(Chn chn) {
    super(chn);
  }

  public WOrderedSetBase(TraversableContainer<Data> container) {
    super(container);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer */
  /* ************************************************************************ */

  @Override
  public boolean IsEqual(IterableContainer<Data> iter) {
    return chn.IsEqual(iter);
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet */
  /* ************************************************************************ */

  @Override
  public Data Min() {
    return chn.Min();
  }

  @Override
  public Data Max() {
    return chn.Max();
  }

  @Override
  public Data Predecessor(Data dat) {
    return chn.Predecessor(dat);
  }

  @Override
  public Data Successor(Data dat) {
    return chn.Successor(dat);
  }

  @Override
  public void RemovePredecessor(Data dat) {
    chn.RemovePredecessor(dat);
  }

  @Override
  public void RemoveSuccessor(Data dat) {
    chn.RemoveSuccessor(dat);
  }

  @Override
  public Data PredecessorNRemove(Data dat) {
    return chn.PredecessorNRemove(dat);
  }

  @Override
  public Data SuccessorNRemove(Data dat) {
    return chn.SuccessorNRemove(dat);
  }

}
