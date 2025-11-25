package zapsdtest.simpletest.apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.DynVector;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.sequences.generic.XDynVectorITest;

public class DynVectorITest extends XDynVectorITest {

  public DynVectorITest() {
    System.out.println("**** DynVectorI ************************************");
    name = "03 - DynVectorI";
  }

  @Override
  public void NewEmptyContainer() { container = new DynVector<>(); }

  @Override
  public DynVector<Long> GetNewEmptyContainer() { return new DynVector<>(); }

  public DynVector<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new DynVector<>(con); }

}
