package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic.XListITest;

public class VListITest extends XListITest {

  public VListITest() {
    System.out.println("**** VListI ****************************************");
    name = "05 - VListI";
  }

  @Override
  public void NewEmptyContainer() { container = new VList<>(); }

  @Override
  public VList<Long> GetNewEmptyContainer() { return new VList<>(); }

  public VList<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new VList<>(con); }

}
