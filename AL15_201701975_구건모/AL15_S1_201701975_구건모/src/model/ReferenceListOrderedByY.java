package model;

public class ReferenceListOrderedByY extends ReferenceListOrderedByCoordinate {

	public ReferenceListOrderedByY(PointSet givenPointSet) {
		super(givenPointSet);
	}

	@Override
	protected int coordinateReferencedByIndex(int i) {
		Point point = this.pointSet().elementAt(this.elementAt(i));
		return point.y;

	}

}
