package zapsdtest.simpletest.apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.sequences.generic.XDynVectorITest;

public class DynCircularVectorITest extends XDynVectorITest {

  public DynCircularVectorITest() {
    System.out.println("**** DynCircularVectorI ****************************");
    name = "04 - DynCircularVectorI";
  }

  @Override
  public void NewEmptyContainer() { container = new DynCircularVector<>(); }

  @Override
  public DynCircularVector<Long> GetNewEmptyContainer() { return new DynCircularVector<>(); }

  public DynCircularVector<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new DynCircularVector<>(con); }

}
