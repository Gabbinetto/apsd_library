package zapsdtest.simpletest.apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.deqs.WQueue;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.deqs.generic.WQueueITest;

public class LLQueueITest extends WQueueITest {

  public LLQueueITest() {
    System.out.println("**** LLQueueI **************************************");
    name = "12 - LLQueueI";
  }

  @Override
  public void NewEmptyContainer() { container = new WQueue<>(new LLList<>()); }

  @Override
  public WQueue<Long> GetNewEmptyContainer() { return new WQueue<>(new LLList<>()); }

  public WQueue<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new WQueue<>(new LLList<>(), con); }

}
