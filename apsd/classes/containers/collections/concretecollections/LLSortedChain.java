package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data>
    implements SortedChain<Data> {

  public LLSortedChain() {
    super();
  }

  public LLSortedChain(LLSortedChain<Data> chn) {
    super(chn);
  }

  public LLSortedChain(TraversableContainer<Data> con) {
    super(con);
  }

  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }

  // NewChain
  @Override
  protected LLSortedChain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLSortedChain<Data>(size, head, tail);
  }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain */
  /* ************************************************************************ */

  public LLNode<Data> PredFind(Data dat) {
    if (dat == null)
      return null;
    ListFRefIterator itr = new ListFRefIterator();
    long len = Size().ToLong();
    LLNode<Data> predNode = null;

    if (len == 0)
      return null;

    while (len > 1) {
      long half = len / 2;
      ListFRefIterator tempItr = new ListFRefIterator(itr);
      tempItr.Next(half);
      LLNode<Data> tmp = tempItr.GetCurrent().Get();
      if (tempItr.GetCurrent().Get().Get().compareTo(dat) < 0) {
        predNode = tmp;
        itr = tempItr;
        len -= half;
      } else {
        len = half - 1;
      }
    }

    return predNode;
  }

  public LLNode<Data> PredPredFind(Data dat) {
    if (dat == null)
      return null;
    ListFRefIterator itr = new ListFRefIterator();
    long len = Size().ToLong();
    LLNode<Data> predNode = null;

    if (len == 0)
      return null;

    while (len > 1) {
      long half = len / 2;
      ListFRefIterator tempItr = new ListFRefIterator(itr);
      tempItr.Next(half - 1);
      LLNode<Data> tmp = tempItr.GetCurrent().Get();
      tempItr.Next();
      if (tempItr.GetCurrent().Get().Get().compareTo(dat) < 0) {
        predNode = tmp;
        itr = tempItr;
        len -= half;
      } else {
        len = half - 1;
      }
    }

    return predNode;
  }

  public LLNode<Data> SuccFind(Data dat) {
    LLNode<Data> predNode = PredFind(dat);
    if (predNode == null) {
      predNode = headref.Get();
      if (predNode == null)
        return null;
    } else {
      predNode = predNode.GetNext().Get();
    }

    while (predNode != null && predNode.Get().compareTo(dat) == 0) {
      predNode = predNode.GetNext().Get();
    }
    return predNode;
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    if (dat == null)
      return false;

    LLNode<Data> predNode = PredFind(dat);
    LLNode<Data> newNode = new LLNode<Data>(dat);

    if (predNode == null) {
      // Insert at head
      newNode.SetNext(headref.Get());
      headref.Set(newNode);
      if (tailref.IsNull()) {
        tailref.Set(newNode);
      }
    } else {
      // Insert after predNode
      newNode.SetNext(predNode.GetNext().Get());
      predNode.SetNext(newNode);
      if (predNode == tailref.Get()) {
        tailref.Set(newNode);
      }
    }

    size.Increment();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    LLNode<Data> predNode = PredFind(dat);
    if (predNode == null) {
      // Rimuovi in testa
      if (headref.IsNull() || !headref.Get().Get().equals(dat))
        return false;
      headref.Set(headref.Get().GetNext().Get());
      if (headref.IsNull()) {
        tailref.Set(null);
      }
      size.Decrement();
      return true;
    }

    LLNode<Data> targetNode = predNode.GetNext().Get();
    if (targetNode == null || !targetNode.Get().equals(dat))
      return false;

    predNode.SetNext(targetNode.GetNext().Get());
    if (targetNode == tailref.Get()) {
      tailref.Set(predNode);
    }
    size.Decrement();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  public Natural Search(Data dat) {
    long index = 0L;
    if (dat == null)
      return null;

    ForwardIterator<Data> itr = FIterator();
    while (itr.IsValid()) {
      Data currentData = itr.GetCurrent();
      if (currentData.equals(dat)) {
        return Natural.Of(index);
      } else if (currentData.compareTo(dat) > 0) {
        break;
      }
      index++;
      itr.Next();
    }

    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence */
  /* ************************************************************************ */

  @Override
  public Natural SearchPredecessor(Data dat) {
    return Search(Predecessor(dat));
  }

  @Override
  public Natural SearchSuccessor(Data dat) {
    return Search(Successor(dat));
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet */
  /* ************************************************************************ */

  @Override
  public Data Predecessor(Data dat) {
    LLNode<Data> predNode = PredFind(dat);
    return predNode == null ? null : predNode.Get();
  }

  @Override
  public Data Successor(Data dat) {
    LLNode<Data> succNode = SuccFind(dat);
    return succNode == null ? null : succNode.Get();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat) {
    LLNode<Data> predNode = PredFind(dat);
    LLNode<Data> targetNode = (predNode == null) ? headref.Get() : predNode.GetNext().Get();

    if (targetNode != null && targetNode.Get().equals(dat)) {
      return false; // Esiste già
    }

    // Inserisci
    LLNode<Data> newNode = new LLNode<Data>(dat);
    if (predNode == null) {
      // Inserisci in testa
      newNode.SetNext(headref.Get());
      headref.Set(newNode);
      if (tailref.IsNull()) {
        tailref.Set(newNode);
      }
    } else {
      // Inserisci dopo predNode
      newNode.SetNext(predNode.GetNext().Get());
      predNode.SetNext(newNode);
      if (predNode == tailref.Get()) {
        tailref.Set(newNode);
      }
    }

    size.Increment();
    return true;
  }

}
