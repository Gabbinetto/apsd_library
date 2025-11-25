package zapsdtest.simpletest.apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.Vector;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.sequences.generic.XVectorITest;

public class VectorITest extends XVectorITest {

  public VectorITest() {
    System.out.println("**** VectorI ***************************************");
    name = "01 - VectorI";
  }

  @Override
  public void NewEmptyContainer() { container = new Vector<>(); }

  @Override
  public Vector<Long> GetNewEmptyContainer() { return new Vector<>(); }

  public Vector<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new Vector<>(con); }

}
