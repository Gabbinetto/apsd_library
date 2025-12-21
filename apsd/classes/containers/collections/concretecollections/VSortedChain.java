package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.classes.utilities.MutableNatural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> {

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
  /* Override specific member functions from Chain */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat) {
    if (IsEmpty()) {
      Insert(dat);
      return true;
    }

    Natural idx = SearchPredecessor(dat);
    idx = (idx == null) ? Natural.ZERO : idx.Increment();

    if (idx.compareTo(Size()) < 0 && GetAt(idx).equals(dat))
      return false;

    vec.InsertAt(dat, idx);
    return true;
  }

  @Override
  public void RemoveOccurrences(Data dat) {
    if (IsEmpty())
      return;

    Natural idx = Search(dat);

    if (idx == null)
      return;

    MutableNatural i = idx.ToMutableNatural();

    while (vec.GetAt(i.ToNatural()) == dat) {
      vec.RemoveAt(i.GetNIncrement());
    }
  }

}
