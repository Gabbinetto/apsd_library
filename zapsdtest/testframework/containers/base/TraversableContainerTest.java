package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.traits.Predicate;
import apsd.interfaces.traits.Accumulator;

import static org.junit.jupiter.api.Assertions.*;

public interface TraversableContainerTest<Data, Con extends TraversableContainer<Data>> extends MembershipContainerTest<Data, Con> {

  default void TestTraverseForwardNullPredicate() {
    BeginTest("TraverseForwardNullPredicate");
    boolean result = ThisContainer().TraverseForward(null);
    assertFalse(result, "TraverseForward with null predicate should return false");
    EndTest();
  }

  default void TestTraverseBackwardNullPredicate() {
    BeginTest("TraverseBackwardNullPredicate");
    boolean result = ThisContainer().TraverseBackward(null);
    assertFalse(result, "TraverseBackward with null predicate should return false");
    EndTest();
  }

  default void TestTraverseForward(Predicate<Data> predicate, boolean expected) {
    BeginTest("TraverseForward");
    boolean result = ThisContainer().TraverseForward(predicate);
    assertEquals(expected, result, "TraverseForward should return " + expected);
    EndTest();
  }

  default void TestTraverseBackward(Predicate<Data> predicate, boolean expected) {
    BeginTest("TraverseBackward");
    boolean result = ThisContainer().TraverseBackward(predicate);
    assertEquals(expected, result, "TraverseBackward should return " + expected);
    EndTest();
  }

  default void TestFoldForwardNullAccumulator(Data initial) {
    BeginTest("FoldForwardNullAccumulator");
    Data result = ThisContainer().FoldForward(null, initial);
    assertEquals(initial, result, "FoldForward with null accumulator should return initial value");
    EndTest();
  }

  default void TestFoldBackwardNullAccumulator(Data initial) {
    BeginTest("FoldBackwardNullAccumulator");
    Data result = ThisContainer().FoldBackward(null, initial);
    assertEquals(initial, result, "FoldBackward with null accumulator should return initial value");
    EndTest();
  }

  default void TestFoldForward(Accumulator<Data, Data> accumulator, Data initial, Data expected) {
    BeginTest("FoldForward");
    Data result = ThisContainer().FoldForward(accumulator, initial);
    assertEquals(expected, result, "FoldForward should return " + expected);
    EndTest();
  }

  default void TestFoldBackward(Accumulator<Data, Data> accumulator, Data initial, Data expected) {
    BeginTest("FoldBackward");
    Data result = ThisContainer().FoldBackward(accumulator, initial);
    assertEquals(expected, result, "FoldBackward should return " + expected);
    EndTest();
  }

  default void TestPrintContent(String str) {
    TestTraverseForward(dat -> {
      System.out.print(dat);
      System.out.print(" ");
      return false;
    },
    false
    );
  }

}
