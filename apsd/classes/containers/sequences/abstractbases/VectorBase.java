package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data> {

  protected Data[] arr;

  // VectorBase
  public VectorBase(Natural size) {
    if (size == null)
      throw new NullPointerException("Natural size cannot be null!");
    this.ArrayAlloc(size);
  }

  public VectorBase(TraversableContainer<Data> con) {
    if (con == null)
      throw new NullPointerException("TraversableContainer cannot be null!");
    final MutableNatural idx = new MutableNatural();
    con.TraverseForward((dat) -> {
      SetAt(dat, idx.GetNDecrement());
      return false;
    });
  }

  protected VectorBase(Data[] arr) {
    this.arr = arr;
  }

  // NewVector
  abstract protected void NewVector(Data[] arr);

  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural newsize) {
    if (newsize == null)
      throw new NullPointerException("Natural newsize cannot be null!");
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) {
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    }
    arr = (Data[]) new Object[(int) size];
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    long size = Size().ToLong();
    for (int i = 0; i < size; i++)
      SetAt(null, Natural.Of(i));
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer */
  /* ************************************************************************ */

  @Override
  public Natural Capacity() {
    return Natural.Of(arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new MutableForwardIterator<Data>() {
      private int index = 0;

      @Override
      public boolean IsValid() {
        return index < Size().ToLong();
      }

      @Override
      public void SetCurrent(Data dat) {
        arr[index] = dat;
      }

      @Override
      public Data DataNNext() {
        index++;
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        return arr[index];
      }

      @Override
      public void Reset() {
        index = 0;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        return arr[index];
      }
    };
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new MutableBackwardIterator<Data>() {
      private int index = (int) Size().Decrement().ToLong();

      @Override
      public boolean IsValid() {
        return index > -1;
      }

      @Override
      public void SetCurrent(Data dat) {
        arr[index] = dat;
      }

      @Override
      public Data DataNPrev() {
        index--;
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        return arr[index];
      }

      @Override
      public void Reset() {
        index = (int) Size().Decrement().ToLong();
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid())
          throw new IllegalStateException("Iterator terminated!");
        return arr[index];
      }
    };
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  abstract public Data GetAt(Natural num);

  @Override
  abstract public void SetAt(Data dat, Natural num);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    // TODO: Capire come fare :(
    return null;
  }

}
