package zapsdtest.simpletest.apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.CircularVector;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.sequences.generic.XVectorITest;

public class CircularVectorITest extends XVectorITest {

  public CircularVectorITest() {
    System.out.println("**** CircularVectorI *******************************");
    name = "02 - CircularVectorI";
  }

  @Override
  public void NewEmptyContainer() { container = new CircularVector<>(); }

  @Override
  public CircularVector<Long> GetNewEmptyContainer() { return new CircularVector<>(); }

  public CircularVector<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new CircularVector<>(con); }

}
