package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must
                                                                                                                // extend
                                                                                                                // CircularVectorBase
                                                                                                                // and
                                                                                                                // implement
                                                                                                                // DynVector

  protected long size = 0L;

  // DynCircularVectorBase
  public DynCircularVectorBase(Natural size) {
    super(size);
    this.size = size.ToLong();
  }

  public DynCircularVectorBase(TraversableContainer<Data> container) {
    super(container);
    this.size = container.Size().ToLong();
  }

  protected DynCircularVectorBase(Data[] arr, long size) {
    super(arr);
    this.size = size;
  }

  @Override
  protected void ArrayAlloc(Natural newsize) {
    super.ArrayAlloc(newsize);
    this.size = newsize.ToLong();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return Natural.Of(this.size);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    super.Clear();
    this.size = 0L;
    this.start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    if (newsize == null)
      throw new NullPointerException("Natural newsize cannot be null!");
    long nsize = newsize.ToLong();
    if (nsize >= Integer.MAX_VALUE) {
      throw new ArithmeticException("Overflow: newsize cannot exceed Integer.MAX_VALUE!");
    } else if (newsize.compareTo(Capacity()) == 0) {
      return;
    }

    Data[] newArr = (Data[]) new Object[(int) nsize];
    for (long i = 0; i < ((nsize < this.size) ? nsize : this.size); i++) {
      newArr[(int) i] = GetAt(Natural.Of(i));
    }
    this.arr = newArr;
    this.start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural num) {
    if (num == null)
      throw new NullPointerException("Natural num cannot be null!");

    Grow(num);
    this.size += num.ToLong();
  }

  @Override
  public void Reduce(Natural num) {
    if (num == null)
      throw new NullPointerException("Natural num cannot be null!");
    long nsize = this.size - num.ToLong();
    if (nsize < 0L)
      nsize = 0L;

    Shrink();
    
    this.size = nsize;

  }

  /* ************************************************************************ */
  /* Specific member functions of Vector */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    super.ShiftLeft(pos, num);
    Reduce(num);
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) {
    Expand(num);
    super.ShiftRight(pos, num);
  }
}
