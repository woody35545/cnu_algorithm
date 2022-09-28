package model;

public class ReferenceListOrderedByX extends ReferenceListOrderedByCoordinate {

	public ReferenceListOrderedByX(PointSet givenPointSet) {
		super(givenPointSet);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int coordinateReferencedByIndex(int i) {
		Point point = this.pointSet().elementAt(this.elementAt(i));
		return point.x;
	}

}
