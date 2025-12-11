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
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size > Integer.MAX_VALUE)
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");

    Data[] newArr = (Data[]) new Object[(int) newsize.ToLong()];
    System.arraycopy(this.arr, 0, newArr, 0, (int) newsize.ToLong());
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural num) {
    return this.arr[(int) ExcIfOutOfBound(num)];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural num) {
    this.arr[(int) ExcIfOutOfBound(num)] = dat;
  }

}
