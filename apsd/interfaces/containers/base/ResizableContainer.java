package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

// import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer {

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand
  default void Expand() {
    Expand(Natural.ONE);
  }

  void Expand(Natural amount);

  // Reduce
  default void Reduce() {
    Reduce(Natural.ONE);
  }

  void Reduce(Natural amount);

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer */
  /* ************************************************************************ */

  @Override
  default void Grow(Natural amount) throws ArithmeticException {
    if (Capacity().ToLong() == Integer.MAX_VALUE) {
      throw new ArithmeticException("Overflow: capacity cannot overcome Integer.MAX_VALUE");
    }
    if (Size().ToLong() + amount.ToLong() >= Capacity().ToLong()) {
      ReallocableContainer.super.Grow(amount);
    }
  }

  @Override
  default void Shrink() {
    if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong())
      ReallocableContainer.super.Shrink();
  }

}
