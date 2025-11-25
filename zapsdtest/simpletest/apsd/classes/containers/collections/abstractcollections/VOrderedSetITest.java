package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.WOrderedSet;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic.WOrderedSetITest;

public class VOrderedSetITest extends WOrderedSetITest {

  public VOrderedSetITest() {
    System.out.println("**** VOrderedSetI **********************************");
    name = "15 - VOrderedSetI";
  }

  @Override
  public void NewEmptyContainer() { container = new WOrderedSet<>(); }

  @Override
  public WOrderedSet<Long> GetNewEmptyContainer() { return new WOrderedSet<>(); }

  public WOrderedSet<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new WOrderedSet<>(con); }

}
