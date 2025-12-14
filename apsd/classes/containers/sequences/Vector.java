package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Data> { // Must extend LinearVectorBase

  // Vector
  public Vector() {
    super(Natural.ZERO);
  }

  public Vector(Natural inisize) {
    super(inisize);
  }

  public Vector(TraversableContainer<Data> con) {
    super(con);
  }

  protected Vector(Data[] arr) {
    super(arr);
  }

  // NewVector
  @Override
  protected VectorBase<Data> NewVector(Data[] arr) {
    return new Vector<Data>(arr);
  }

}
