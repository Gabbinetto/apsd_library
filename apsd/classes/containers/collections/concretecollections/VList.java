package apsd.classes.containers.collections.concretecollections;

import apsd.interfaces.containers.sequences.DynVector;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data> extends VChainBase<Data> implements List<Data> { // Must extend VChainBase and implement List

  public VList() {
    super();
  }

  public VList(TraversableContainer<Data> con) {
    super(con);
  }

  protected VList(DynVector<Data> vec) {
    super(vec);
  }

  // NewChain
  @Override
  protected VChainBase<Data> NewChain(DynVector<Data> vec) {
    return new VList<>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  // TOD: SetAt

  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    return (MutableSequence<Data>) super.SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data dat, Natural num) {
    vec.InsertAt(dat, num);
  }

}
