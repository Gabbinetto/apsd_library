package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.OrderedSet;

import static org.junit.jupiter.api.Assertions.*;

public interface OrderedSetTest<Data extends Comparable<? super Data>, Con extends OrderedSet<Data>> extends SetTest<Data, Con> {

  default void TestMin(Data expectedElement) {
    BeginTest("Min");
    Data min = ThisContainer().Min();
    assertEquals(expectedElement, min, "Min should return " + expectedElement);
    EndTest();
  }

  default void TestMax(Data expectedElement) {
    BeginTest("Max");
    Data max = ThisContainer().Max();
    assertEquals(expectedElement, max, "Max should return " + expectedElement);
    EndTest();
  }

  default void TestRemoveMin() {
    BeginTest("RemoveMin");
    ThisContainer().RemoveMin();
    EndTest();
  }

  default void TestRemoveMax() {
    BeginTest("RemoveMax");
    ThisContainer().RemoveMax();
    EndTest();
  }

  default void TestMinNRemove(Data expectedElement) {
    BeginTest("MinNRemove");
    Data min = ThisContainer().MinNRemove();
    assertEquals(expectedElement, min, "MinNRemove should return " + expectedElement);
    EndTest();
  }

  default void TestMaxNRemove(Data expectedElement) {
    BeginTest("MaxNRemove");
    Data max = ThisContainer().MaxNRemove();
    assertEquals(expectedElement, max, "MaxNRemove should return " + expectedElement);
    EndTest();
  }

  default void TestPredecessor(Data element, Data expectedElement) {
    BeginTest("Predecessor");
    Data pred = ThisContainer().Predecessor(element);
    assertEquals(expectedElement, pred, "Predecessor should return " + expectedElement);
    EndTest();
  }

  default void TestSuccessor(Data element, Data expectedElement) {
    BeginTest("Successor");
    Data succ = ThisContainer().Successor(element);
    assertEquals(expectedElement, succ, "Successor should return " + expectedElement);
    EndTest();
  }

  default void TestRemovePredecessor(Data element, Data expectedElement) {
    BeginTest("RemovePredecessor");
    ThisContainer().RemovePredecessor(element);
    EndTest();
  }

  default void TestRemoveSuccessor(Data element, Data expectedElement) {
    BeginTest("RemoveSuccessor");
    ThisContainer().RemoveSuccessor(element);
    EndTest();
  }

  default void TestPredecessorNRemove(Data element, Data expectedElement) {
    BeginTest("PredecessorNRemove");
    Data pred = ThisContainer().PredecessorNRemove(element);
    assertEquals(expectedElement, pred, "Predecessor should return " + expectedElement);
    EndTest();
  }

  default void TestSuccessorNRemove(Data element, Data expectedElement) {
    BeginTest("SuccessorNRemove");
    Data succ = ThisContainer().SuccessorNRemove(element);
    assertEquals(expectedElement, succ, "Successor should return " + expectedElement);
    EndTest();
  }

}
