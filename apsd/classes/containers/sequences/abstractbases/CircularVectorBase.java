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

  private int IndexCircular(Natural num) {
    if (Size().ToLong() == 0L) {
      throw new IndexOutOfBoundsException("CircularVectorBase IndexCircular(): sequence is empty.");
    }
    return (int) ((this.start + ExcIfOutOfBound(num)) % arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) {
      throw new ArithmeticException("Overflow: newsize cannot exceed Integer.MAX_VALUE!");
    } else if (newsize.compareTo(Capacity()) == 0) {
      return;
    }

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
    return this.arr[IndexCircular(num)];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural num) {
    this.arr[IndexCircular(num)] = dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (idx >= size - (idx + len)) {
      super.ShiftLeft(pos, num);
      return;
    }

    long iniwrt = idx - 1 + len;
    long wrt = iniwrt;
    for (long rdr = wrt - len; rdr >= 0; rdr--, wrt--) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    for (; iniwrt - wrt < len; wrt--)
      SetAt(null, Natural.Of(wrt));

    start = (start + len) % arr.length;
  }

  // TODO: ShiftRight
}
