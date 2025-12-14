package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase
  public LLChainBase() {
  };

  public LLChainBase(TraversableContainer<Data> con) {
    size.Assign(con.Size());
    final Box<Boolean> first = new Box<>(true);
    con.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (first.Get()) {
        headref.Set(node);
        first.Set(false);
      } else {
        tailref.Get().SetNext(node);
      }
      tailref.Set(node);
      return false;
    });
  }

  protected LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
    this.size.Assign(size);
    this.headref.Set(head);
    this.tailref.Set(tail);
  }

  // NewChain
  abstract public Chain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);

  /* ************************************************************************ */
  /* Specific member functions from LLChainBase */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new MutableForwardIterator<Data>() {
      private ForwardIterator<Box<LLNode<Data>>> refiter = FRefIterator();

      @Override
      public boolean IsValid() {
        return refiter.IsValid();
      }

      @Override
      public void Reset() {
        refiter.Reset();
      }

      @Override
      public Data GetCurrent() {
        return refiter.GetCurrent().Get().Get();
      }

      @Override
      public void SetCurrent(Data dat) {
        if (dat == null)
          return;
        refiter.GetCurrent().Get().Set(dat);
      }

      @Override
      public void Next() {
        refiter.Next();
      }

      @Override
      public Data DataNNext() {
        refiter.Next();
        return refiter.GetCurrent().Get().Get();
      }
    };
  };

  protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() {
    return new ListFRefIterator();
  }

  protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {

    private Box<LLNode<Data>> cur = headref;

    @Override
    public boolean IsValid() {
      return cur != null && !cur.IsNull();
    }

    @Override
    public void Reset() {
      cur = headref;
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      return cur;
    }

    @Override
    public void Next() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      cur = cur.Get().GetNext();
    }

    @Override
    public Box<LLNode<Data>> DataNNext() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      Box<LLNode<Data>> oldCur = cur;
      cur = cur.Get().GetNext();
      return oldCur;
    }
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new MutableBackwardIterator<Data>() {
      private BackwardIterator<Box<LLNode<Data>>> refiter = BRefIterator();

      @Override
      public boolean IsValid() {
        return refiter.IsValid();
      }

      @Override
      public void Reset() {
        refiter.Reset();
      }

      @Override
      public Data GetCurrent() {
        return refiter.GetCurrent().Get().Get();
      }

      @Override
      public void SetCurrent(Data dat) {
        if (dat == null)
          return;
        refiter.GetCurrent().Get().Set(dat);
      }

      @Override
      public void Prev() {
        refiter.Prev();
      }

      @Override
      public Data DataNPrev() {
        refiter.Prev();
        return refiter.GetCurrent().Get().Get();
      }
    };
  };

  protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() {
    return new ListBRefIterator();
  }

  protected class ListBRefIterator implements BackwardIterator<Box<LLNode<Data>>> {
    protected long cur = -1L;
    protected Vector<Box<LLNode<Data>>> nodes = null;

    public ListBRefIterator() {
      Reset();
    }

    public ListBRefIterator(ListBRefIterator iter) {
      cur = iter.cur;
      nodes = new Vector<>(iter.nodes);
    }

    public boolean IsValid() {
      return cur >= 0L && cur < nodes.Size().ToLong();
    }

    @Override
    public void Reset() {
      cur = -1L;
      if (Size().IsZero()) {
        nodes = null;
        return;
      }

      nodes = new Vector<>();
      for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
        nodes.SetAt(ref, Natural.Of(cur));
      }
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      return nodes.GetAt(Natural.Of(cur));
    }

    @Override
    public void Prev() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      cur--;
    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {
      if (!IsValid())
        throw new IllegalStateException("Iterator terminated!");
      return nodes.GetAt(Natural.Of(cur--));
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return size.ToNatural();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    headref.Set(null);
    tailref.Set(null);
    size.Zero();
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    if (dat == null)
      return false;
    final Box<LLNode<Data>> prd = new Box<>();
    return FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (node.Get().equals(dat)) {
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) {
          tailref.Set(prd.Get());
        }
        size.Decrement();
        return true;
      }
      prd.Set(node);
      return false;
    });
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  public Data GetFirst() {
    return headref.Get().Get();
  }

  @Override
  public Data GetLast() {
    return tailref.Get().Get();
  }

  @Override
  public Sequence<Data> SubSequence(Natural start, Natural end) {
    long lStart = start.ToLong(), lEnd = end.ToLong();
    if (lStart > lEnd || lEnd >= Size().ToLong())
      throw new IndexOutOfBoundsException("Invalid start or end index for SubSequence!");

    Box<LLNode<Data>> head = new Box<>(), tail = new Box<>();
    MutableNatural idx = new MutableNatural(lStart);

    TraverseForward(
        (dat) -> {
          if (idx.ToLong() < lStart) {
            idx.Increment();
            return false;
          } else if (idx.ToLong() > lEnd)
            return true;

          LLNode<Data> newNode = new LLNode<>(dat);
          if (idx.ToLong() == lStart) {
            head.Set(newNode);
          } else {
            tail.Get().SetNext(newNode);
          }

          if (!head.IsNull() && head.Get().IsNull())
            head.Get().SetNext(newNode);

          tail.Set(newNode);
          idx.Increment();
          return false;
        });

    return NewChain(lEnd - lStart + 1L, head.Get(), tail.Get());
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural num) {
    long idx = ExcIfOutOfBound(num);

    // Rimozione in testa
    if (idx == 0L) {
      LLNode<Data> oldHead = headref.Get();
      headref.Set(oldHead.GetNext().Get());
      size.Decrement();
      return oldHead.Get();
    }

    MutableNatural current = new MutableNatural(0);
    final Box<LLNode<Data>> removedNode = new Box<>();

    FRefIterator().ForEachForward((ref) -> {
      if (current.ToLong() != idx - 1L) {
        current.Increment();
        return false;
      }
      LLNode<Data> prevNode = ref.Get();
      removedNode.Set(prevNode.GetNext().Get());
      prevNode.SetNext(removedNode.Get().GetNext().Get());
      if (removedNode.Get() == tailref.Get())
        tailref.Set(prevNode);
      return true;
    });

    if (removedNode.IsNull())
      throw new IllegalStateException("Removed node is null!");
    size.Decrement();
    return removedNode.Get().Get();
  }

  // TODO: Metodi come RemoveFirst, RemoveLast, ecc. (Capire come reimplementare)

  /* ************************************************************************ */
  /* Override specific member functions from Collection */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    long oldSize = size.ToLong();

    Box<LLNode<Data>> prev = new Box<>();
    Box<LLNode<Data>> cur = new Box<>(headref.Get());

    while (!cur.IsNull()) {
      // Se l'elemento non va filtrato,
      // avanza normalmente
      if (fun.Apply(cur.Get().Get())) {
        prev.Set(cur.Get());
        cur.Set(cur.Get().GetNext().Get());
        continue;
      }

      // Se prev non punta a niente, si è in testa
      if (prev.IsNull()) {
        headref.Set(cur.Get().GetNext().Get());
        cur.Set(headref.Get());
        size.Decrement();
        continue;
      }

      // Elimina il nodo corrente
      // impostando come prossimo del precedente
      // il prossimo del corrente
      LLNode<Data> next = cur.Get().GetNext().Get();
      prev.Get().SetNext(next);
      cur.Set(next);
      size.Decrement();

    }

    tailref.Set(prev.Get());
    return oldSize != size.ToLong();

  }

}
