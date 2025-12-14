package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.classes.containers.sequences.abstractbases.VectorBase;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data> { // Must extend DynCircularVectorBase

  public DynCircularVector() {
    super(Natural.ZERO);
  }

  public DynCircularVector(Natural inisize) {
    super(inisize);
  }

  public DynCircularVector(TraversableContainer<Data> con) {
    super(con);
  }

  protected DynCircularVector(Data[] arr) {
    super(arr, arr.length);
  }

  // NewVector
  @Override
  protected VectorBase<Data> NewVector(Data[] arr) {
    return new DynCircularVector<Data>(arr);
  }

}
