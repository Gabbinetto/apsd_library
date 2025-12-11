package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;

/** Object: Wrapper set implementation via chain. */
public class WSet<Data> extends WSetBase<Data, Chain<Data>> { // Must extend WSetBase

  public WSet() {
    super();
  }

  public WSet(Chain<Data> chn) {
    super(chn);
  }

  public WSet(TraversableContainer<Data> con) {
    super(con);
  }

  public WSet(Chain<Data> chn, TraversableContainer<Data> con) {
    super(chn);
    con.TraverseForward((dat) -> {
      this.chn.InsertIfAbsent(dat);
      return false;
    });
  }

  @Override
  protected void ChainAlloc() {
    chn = new VList<Data>();
  }

}
