package apsd.interfaces.containers.iterators;

/** Interface: Iteratore arbitrario. */
public interface Iterator<Data> {

  // IsValid
  boolean IsValid();

  // Reset
  void Reset();

  // GetCurrent
  Data GetCurrent();

}
