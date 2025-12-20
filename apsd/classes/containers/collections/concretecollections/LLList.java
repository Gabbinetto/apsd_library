package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> {

  public LLList() {
    super();
  }

  public LLList(TraversableContainer<Data> con) {
    super(con);
  }

  protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }

  // NewChain
  @Override
  protected Chain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLList<Data>(size, head, tail);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural num) {
    long idx = ExcIfOutOfBound(num);
    if (idx == 0) {
      SetFirst(dat);
      return;
    } else if (idx == Size().Decrement().ToLong()) {
      SetLast(dat);
      return;
    }

    MutableForwardIterator<Data> iter = FIterator();

    iter.Next(idx);
    iter.SetCurrent(dat);
  }

  @Override
  public void SetFirst(Data dat) {
    if (IsEmpty())
      throw new IndexOutOfBoundsException("SetFirst(): LLList is empty!");
    headref.Get().Set(dat);
  }

  @Override
  public void SetLast(Data dat) {
    if (IsEmpty())
      throw new IndexOutOfBoundsException("SetLast(): LLList is empty!");
    tailref.Get().Set(dat);
  }

  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    return (MutableSequence<Data>) super.SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data dat, Natural num) {
    long idx;
    if (num == null)
      throw new NullPointerException("Natural cannot be null!");
    else if (num.ToLong() > Size().ToLong() || num.ToLong() < 0)
      throw new IndexOutOfBoundsException("Index " + num + " is out of bounds for size " + Size());
    else
      idx = num.ToLong();

    if (idx == 0) {
      InsertFirst(dat);
      return;
    } else if (idx == Size().ToLong()) {
      InsertLast(dat);
      return;
    }

    LLNode<Data> newNode = new LLNode<Data>(dat);
    ForwardIterator<Box<LLNode<Data>>> iter = FRefIterator();
    iter.Next(idx - 1);

    LLNode<Data> prev = iter.GetCurrent().Get();
    LLNode<Data> next = prev.GetNext().Get();
    prev.SetNext(newNode);
    newNode.SetNext(next);
    size.Increment();
  }

  @Override
  public void InsertFirst(Data dat) {
    LLNode<Data> newNode = new LLNode<Data>(dat);
    if (Size().IsZero()) {
      headref.Set(newNode);
      tailref.Set(newNode);
    } else {
      newNode.SetNext(headref.Get());
      headref.Set(newNode);
    }
    size.Increment();
  }

  @Override
  public void InsertLast(Data dat) {
    LLNode<Data> newNode = new LLNode<Data>(dat);
    if (Size().IsZero()) {
      headref.Set(newNode);
      tailref.Set(newNode);
    } else {
      tailref.Get().SetNext(newNode);
      tailref.Set(newNode);
    }
    size.Increment();
  }

}
