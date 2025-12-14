package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data> { // Must extend DynLinearVectorBase

  public DynVector() {
    super(Natural.ZERO);
  }

  public DynVector(Natural inisize) {
    super(inisize);
  }

  public DynVector(TraversableContainer<Data> con) {
    super(con);
  }

  protected DynVector(Data[] arr) {
    super(arr, arr.length);
  }

  // NewVector
  @Override
  protected VectorBase<Data> NewVector(Data[] arr) {
    return new DynVector<Data>(arr);
  }

}
