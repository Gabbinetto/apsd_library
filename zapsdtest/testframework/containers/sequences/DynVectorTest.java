package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.DynVector;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.base.ResizableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface DynVectorTest<Data, Con extends DynVector<Data>> extends VectorTest<Data, Con>, ResizableContainerTest<Con> {

  default void TestInsertAtWithAutoExpansion(Natural position, Data element) {
    BeginTest("InsertAtWithAutoExpansion");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().InsertAt(element, position);
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "InsertAt should automatically expand size");
    EndTest();
  }

  default void TestAtNRemoveWithAutoReduction(Natural position, Data expectedElement) {
    BeginTest("AtNRemoveWithAutoReduction");
    long initialSize = ThisContainer().Size().ToLong();
    Data removed = ThisContainer().AtNRemove(position);
    assertEquals(expectedElement, removed,
    "AtNRemove should return the removed element");
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "AtNRemove should automatically reduce size");
    EndTest();
  }

  @Override
  default void TestShiftLeft(Natural position, Natural number) {
    BeginTest("ShiftLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position, number);
    assertEquals(initialSize - number.ToLong(), ThisContainer().Size().ToLong(),
    "ShiftLeft should automatically call Reduce and Size should decrease by " + number);
    EndTest();
  }

  @Override
  default void TestShiftLeft(Natural position) {
    BeginTest("ShiftLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position);
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftFirstLeft() {
    BeginTest("ShiftFirstLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstLeft();
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftFirstLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftLastLeft() {
    BeginTest("ShiftLastLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastLeft();
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftLastLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftRight(Natural position, Natural number) {
    BeginTest("ShiftRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftRight(position, number);
    assertEquals(initialSize + number.ToLong(), ThisContainer().Size().ToLong(),
    "ShiftRight should automatically call Expand and Size should increase by " + number);
    for(long num = 0; num < number.ToLong(); num++, position = position.Increment()) {
      assertNull(ThisContainer().GetAt(position), "Position " + position + " should be null");
    }
    EndTest();
  }

  @Override
  default void TestShiftRight(Natural position) {
    BeginTest("ShiftRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftRight(position);
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetAt(position), "Position " + position + " should be null");
    EndTest();
  }

  @Override
  default void TestShiftFirstRight() {
    BeginTest("ShiftFirstRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstRight();
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftFirstRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetFirst(), "First position should be null");
    EndTest();
  }

  @Override
  default void TestShiftLastRight() {
    BeginTest("ShiftLastRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastRight();
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftLastRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetLast(), "Last position should be null");
    EndTest();
  }

}
