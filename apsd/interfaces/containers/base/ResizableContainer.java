package apsd.interfaces.containers.base;

// import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer { // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand

  // Reduce

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...

  // @Override
  // default void Shrink() {
  //   if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink();
  // }

}
