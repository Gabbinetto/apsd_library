package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.RemovableAtSequence;
import apsd.classes.utilities.Natural;

import static org.junit.jupiter.api.Assertions.*;

public interface RemovableAtSequenceTest<Data, Con extends RemovableAtSequence<Data>> extends SequenceTest<Data, Con> {

  default void TestRemoveAt(Natural position, boolean edgeCase) {
    BeginTest("RemoveAt");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().RemoveAt(position),
      "RemoveAt should throw exception for invalid position");
    } else {
      long initialSize = ThisContainer().Size().ToLong();
      ThisContainer().RemoveAt(position);
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after RemoveAt");
    }
    EndTest();
  }

  default void TestAtNRemove(Natural position, Data expectedElement, boolean edgeCase) {
    BeginTest("AtNRemove");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().AtNRemove(position),
      "AtNRemove should throw exception for invalid position");
    } else {
      long initialSize = ThisContainer().Size().ToLong();
      Data removed = ThisContainer().AtNRemove(position);
      assertEquals(expectedElement, removed,
      "AtNRemove should return the removed element");
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after AtNRemove");
    }
    EndTest();
  }

  default void TestRemoveFirst() {
    BeginTest("RemoveFirst");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().RemoveFirst();
    if (initialSize == 0) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
        "Size should be 0 after RemoveFirst");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
        "Size should decrease by 1 after RemoveFirst");
    }
    EndTest();
  }

  default void TestFirstNRemove(Data expectedElement) {
    BeginTest("FirstNRemove");
    long initialSize = ThisContainer().Size().ToLong();
    Data removed = ThisContainer().FirstNRemove();
    assertEquals(expectedElement, removed,
    "FirstNRemove should return the first element");
    if (initialSize == 0) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
        "Size should be 0 after FirstNRemove");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
        "Size should decrease by 1 after FirstNRemove");
    }
    EndTest();
  }

  default void TestRemoveLast() {
    BeginTest("RemoveLast");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().RemoveLast();
    if (initialSize == 0) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
        "Size should be 0 after RemoveLast");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
        "Size should decrease by 1 after RemoveLast");
    }
    EndTest();
  }

  default void TestLastNRemove(Data expectedElement) {
    BeginTest("LastNRemove");
    long initialSize = ThisContainer().Size().ToLong();
    Data removed = ThisContainer().LastNRemove();
    assertEquals(expectedElement, removed,
    "LastNRemove should return the last element");
    if (initialSize == 0) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
        "Size should be 0 after LastNRemove");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
        "Size should decrease by 1 after LastNRemove");
    }
    EndTest();
  }

}
