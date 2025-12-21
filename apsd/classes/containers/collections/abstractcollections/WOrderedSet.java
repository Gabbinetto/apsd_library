package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.collections.Chain;;

/** Object: Wrapper ordered set implementation via ordered chain. */
public class WOrderedSet<Data extends Comparable<? super Data>> extends WOrderedSetBase<Data, SortedChain<Data>> {

  public WOrderedSet() {
    super();
  }

  public WOrderedSet(Chain<Data> chn) {
    super(chn);
  }

  public WOrderedSet(TraversableContainer<Data> con) {
    super(con);
  }

  public WOrderedSet(Chain<Data> chn, TraversableContainer<Data> con) {
    super(chn);
    con.TraverseForward((dat) -> {
      this.chn.InsertIfAbsent(dat);
      return false;
    });
  }

  @Override
  protected void ChainAlloc() {
    chn = new VSortedChain<Data>();
  }

}
