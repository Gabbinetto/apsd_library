package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> { // Must
                                                                                                                         // extend
                                                                                                                         // VChainBase
                                                                                                                         // and
                                                                                                                         // implements
                                                                                                                         // SortedChain

  public VSortedChain() {
    super();
  }

  public VSortedChain(VSortedChain<Data> chn) {
    super(chn.vec);
  }

  public VSortedChain(TraversableContainer<Data> con) {
    super(con);
  }

  protected VSortedChain(DynVector<Data> vec) {
    super(vec);
  }

  // NewChain
  @Override
  protected VChainBase<Data> NewChain(DynVector<Data> vec) {
    return new VSortedChain<>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    Natural idx = SearchPredecessor(dat);
    if (idx == null) {
      idx = Natural.ZERO;
    } else {
      idx = idx.Increment();
    }
    vec.InsertAt(dat, idx);
    return true;
  }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // TODO: RemoveOccurrences and InsertIfAbsent 
  
}
