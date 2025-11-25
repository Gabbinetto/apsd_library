package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic.XSortedChainITest;

public class VSortedChainITest extends XSortedChainITest {

  public VSortedChainITest() {
    System.out.println("**** VSortedChainI *********************************");
    name = "07 - VSortedChainI";
  }

  @Override
  public void NewEmptyContainer() { container = new VSortedChain<>(); }

  @Override
  public VSortedChain<Long> GetNewEmptyContainer() { return new VSortedChain<>(); }

  public VSortedChain<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new VSortedChain<>(con); }

}
