package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final DynVector<Data> vec;

  // VChainBase
  public VChainBase() {
    this.vec = new DynCircularVector<>();
  }

  public VChainBase(DynVector<Data> vec) {
    this.vec = vec;
  }

  public VChainBase(TraversableContainer<Data> con) {
    this.vec = new DynCircularVector<>(con);
  }

  // NewChain
  abstract protected VChainBase<Data> NewChain(DynVector<Data> vec);

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return vec.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    vec.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    Natural idx = vec.Search(dat);
    if (idx != null) {
      vec.RemoveAt(idx);
      return true;
    }
    return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new MutableForwardIterator<Data>() {

      private long index = 0;

      @Override
      public boolean IsValid() {
        return index < vec.Size().ToLong();
      }

      @Override
      public void SetCurrent(Data dat) {
        vec.SetAt(dat, Natural.Of(index));
      }

      @Override
      public void Next() {
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        index++;
      }

      @Override
      public void Reset() {
        index = 0;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) {
          throw new IllegalStateException("Iterator terminated!");
        }
        return vec.GetAt(Natural.Of(index));
      }

    };
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new MutableBackwardIterator<Data>() {

      private long index = (IsEmpty()) ? -1 : vec.Size().Decrement().ToLong();

      @Override
      public boolean IsValid() {
        return index > -1;
      }

      @Override
      public void SetCurrent(Data dat) {
        vec.SetAt(dat, Natural.Of(index));
      }

      @Override
      public void Prev() {
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        index--;
      }

      @Override
      public void Reset() {
        index = (IsEmpty()) ? -1 : vec.Size().Decrement().ToLong();
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) {
          throw new IllegalAccessError("Iterator terminated!");
        }
        return vec.GetAt(Natural.Of(index));
      }

    };
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural num) {
    return vec.GetAt(num);
  }

  @Override
  public Sequence<Data> SubSequence(Natural start, Natural end) {
    return (Sequence<Data>) NewChain(vec.SubVector(start, end));
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural num) {
    return vec.AtNRemove(num);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    long deletions = 0;
    if (fun == null) {
      return false;
    }

    MutableForwardIterator<Data> wrt = FIterator();
    for (; wrt.IsValid(); wrt.Next()) {
      Data dat = wrt.GetCurrent();
      if (!fun.Apply(dat)) {
        wrt.SetCurrent(null);
        deletions++;
      }
    }

    if (deletions == 0)
      return false;

    wrt.Reset();
    MutableForwardIterator<Data> rdr = FIterator();
    for (; rdr.IsValid(); rdr.Next()) {
      Data current = rdr.GetCurrent();
      if (current == null)
        continue;

      wrt.SetCurrent(current);
      rdr.SetCurrent(null);
    }

    vec.Reduce(Natural.Of(deletions));
    return true;

  }

}
