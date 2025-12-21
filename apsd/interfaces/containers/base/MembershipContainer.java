package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla verifica di appartenenza. */
public interface MembershipContainer<Data> extends Container {

  // Exists
  boolean Exists(Data dat);

}
