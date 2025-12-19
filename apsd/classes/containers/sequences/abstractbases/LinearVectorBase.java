package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  // LinearVectorBase
  public LinearVectorBase(Natural size) {
    super(size);
  }

  public LinearVectorBase(TraversableContainer<Data> container) {
    super(container);
  }

  protected LinearVectorBase(Data[] arr) {
    super(arr);
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

    long size = Size().ToLong();
    Data[] newArr = (Data[]) new Object[(int) nsize];
    for (long i = 0; i < (nsize < size ? nsize : size); i++) {
      newArr[(int) i] = this.arr[(int) i];
    }
    this.arr = newArr;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural num) {
    return this.arr[(int) ExcIfOutOfBound(num)];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural num) {
    this.arr[(int) ExcIfOutOfBound(num)] = dat;
  }

}
