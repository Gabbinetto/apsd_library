package zapsdtest.testframework.containers.deqs;

import apsd.interfaces.containers.deqs.Stack;

import zapsdtest.testframework.containers.base.ClearableContainerTest;
import zapsdtest.testframework.containers.base.InsertableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface StackTest<Data, Con extends Stack<Data>> extends ClearableContainerTest<Con>, InsertableContainerTest<Data, Con> {

  default void TestTop(Data expectedElement) {
    BeginTest("Top");
    Data top = ThisContainer().Top();
    assertEquals(expectedElement, top, "Top should return " + expectedElement);
    EndTest();
  }

  default void TestPop(boolean edgeCase) {
    BeginTest("Pop");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Pop();
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Pop on Empty Stack");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after Pop on Empty Stack");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after Pop");
    }
    EndTest();
  }

  default void TestTopNPop(Data expectedElement, boolean edgeCase) {
    BeginTest("TopNPop");
    long initialSize = ThisContainer().Size().ToLong();
    Data popped = ThisContainer().TopNPop();
    assertEquals(expectedElement, popped, "TopNPop should return " + expectedElement);
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Pop on Empty Stack");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after TopNPop on Empty Stack");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after TopNPop");
    }
    EndTest();
  }

  default void TestPush(Data element) {
    BeginTest("Push");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Push(element);
    Data top = ThisContainer().Top();
    assertEquals(element, top, "Top should return " + element + " after Push");
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after Push");
    EndTest();
  }

}
