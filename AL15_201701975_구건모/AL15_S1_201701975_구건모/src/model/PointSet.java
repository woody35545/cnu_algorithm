package model;

import list.ArrayList;

public class PointSet extends ArrayList<Point> {
	public PointSet(int givenCapacity) {
		super(givenCapacity);
	}

	public Point pointReferenceByIndex(ReferenceList referenceList, int Index) {
		if (referenceList.orderIsValid(Index)) {
			return this.elementAt(referenceList.elementAt(Index));

		} else {
			return null;
		}
	}
}
