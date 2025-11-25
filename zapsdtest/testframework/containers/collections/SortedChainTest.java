package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.SortedChain;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.sequences.SortedSequenceTest;

import static org.junit.jupiter.api.Assertions.*;

public interface SortedChainTest<Data extends Comparable<? super Data>, Con extends SortedChain<Data>> extends ChainTest<Data, Con>, SortedSequenceTest<Data, Con> {

  default void TestSearchPredecessor(Data element, Natural expectedPosition) {
    BeginTest("SearchPredecessor");
    Natural position = ThisContainer().SearchPredecessor(element);
    assertNotNull(position, "Search should find predecessor of the element in sorted sequence");
    assertEquals(expectedPosition, position,
    "Search should return correct position in sorted sequence");
    EndTest();
  }

  default void TestSearchSuccessor(Data element, Natural expectedPosition) {
    BeginTest("SearchSuccessor");
    Natural position = ThisContainer().SearchSuccessor(element);
    assertNotNull(position, "Search should find successor of the element in sorted sequence");
    assertEquals(expectedPosition, position,
    "Search should return correct position in sorted sequence");
    EndTest();
  }

}
