package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.WOrderedSet;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic.WOrderedSetITest;

public class LLOrderedSetITest extends WOrderedSetITest {

  public LLOrderedSetITest() {
    System.out.println("**** LLOrderedSetI *********************************");
    name = "16 - LLOrderedSetI";
  }

  @Override
  public void NewEmptyContainer() { container = new WOrderedSet<Long>(new LLSortedChain<>()); }

  @Override
  public WOrderedSet<Long> GetNewEmptyContainer() { return new WOrderedSet<Long>(new LLSortedChain<>()); }

  public WOrderedSet<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new WOrderedSet<>(new LLSortedChain<>(), con); }

}
