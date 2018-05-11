package ExtendTest;

public class ExtendInner extends WithInner.Inner {
	
	public ExtendInner(WithInner wi) {
		wi.super();
	}

}
