package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  protected long start = 0L;

  // CircularVectorBase
  public CircularVectorBase(Natural size) {
    super(size);
  }

  public CircularVectorBase(TraversableContainer<Data> container) {
    super(container);
  }

  protected CircularVectorBase(Data[] arr) {
    super(arr);
  }

  @Override
  protected void ArrayAlloc(Natural newsize) {
    super.ArrayAlloc(newsize);
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size > Integer.MAX_VALUE)
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");

    Data[] newArr = (Data[]) new Object[(int) newsize.ToLong()];
    for (int i = 0; i < size; i++)
      newArr[i] = GetAt(Natural.Of(i));

    this.arr = newArr;
    this.start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural num) {
    return this.arr[(int) ((this.start + num.ToLong()) % Capacity().ToLong())];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural num) {
    this.arr[(int) ((this.start + num.ToLong()) % Capacity().ToLong())] = dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    long idx = (this.start + pos.ToLong()) % Capacity().ToLong();
    super.ShiftLeft(Natural.Of(idx), num);
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) {
    long idx = (this.start + pos.ToLong()) % Capacity().ToLong();
    super.ShiftRight(Natural.Of(idx), num);
  }
}
