package zapsdtest.simpletest.apsd.classes.containers.deqs;

import apsd.classes.containers.deqs.WQueue;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.deqs.generic.WQueueITest;

public class VQueueITest extends WQueueITest {

  public VQueueITest() {
    System.out.println("**** VQueueI ***************************************");
    name = "11 - VQueueI";
  }

  @Override
  public void NewEmptyContainer() { container = new WQueue<>(); }

  @Override
  public WQueue<Long> GetNewEmptyContainer() { return new WQueue<>(); }

  public WQueue<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new WQueue<>(con); }

}
