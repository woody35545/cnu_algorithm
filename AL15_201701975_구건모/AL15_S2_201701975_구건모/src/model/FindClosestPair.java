package model;

import model.ReferenceList;

public class FindClosestPair {

	private static final int MAX_NUMBER_OF_NEIGHBORS = 7;
	private static final int DEFAULT_MIN_RECURSIVE_SIZE = 150;

	private PointSet _pointSet;
	private int _minRecursionSize;

	private class SeparatedPair {
		private ReferenceList _leftList;
		private ReferenceList _rightList;

		public SeparatedPair() {

		}
	}

	private PairOfPoints closestPairDirectlyFromSmallPointSet(ReferenceList Px) {
		if (Px.size() <= 1) {
			return null;
		} else {
			Point p0 = this.pointSet().pointReferenceByIndex(Px, 0);
			Point p1 = this.pointSet().pointReferenceByIndex(Px, 1);

			Point closestPair_point_i = p0;
			Point closestPair_point_j = p1;
			long minDistance = p0.distanceTo(p1);

			if (Px.size() == 3) {
				Point p2 = this.pointSet().pointReferenceByIndex(Px, 2);
				if (p0.distanceTo(p2) < minDistance) {
					closestPair_point_i = p0;
					closestPair_point_j = p2;
					minDistance = p0.distanceTo(p2);

				}
				if (p1.distanceTo(p2) < minDistance) {
					closestPair_point_i = p1;
					closestPair_point_j = p2;
				}
			}
			PairOfPoints closestPair = new PairOfPoints(closestPair_point_i, closestPair_point_j);
			return closestPair;
		}
	}

	private Integer seperationLine(ReferenceList Px) {
		return Px.elementAt(Px.size() / 2);
	}

	private PairOfPoints solveRecursively(ReferenceList Px, ReferenceList Py) {
		PairOfPoints closestPair;
		if (Px.size() <= 3) {
			closestPair = this.closestPairDirectlyFromSmallPointSet(Px);
			return closestPair;
		}
		int seperationLine = this.seperationLine(Px);
		SeparatedPair separatedPairFromPx = this.separatedReferenceList(Px, seperationLine);
		SeparatedPair separatedPairFromPy = this.separatedReferenceList(Py, seperationLine);
		ReferenceList Qx = separatedPairFromPx._leftList;
		ReferenceList Rx = separatedPairFromPx._rightList;
		ReferenceList Qy = separatedPairFromPy._leftList;
		ReferenceList Ry = separatedPairFromPy._rightList;

		PairOfPoints closestPairFromLeft = this.solveRecursively(Qx, Qy);
		PairOfPoints closestPairFromRight = this.solveRecursively(Rx, Ry);

		if (closestPairFromLeft == null) {
			closestPair = closestPairFromRight;
		} else if (closestPairFromRight == null) {
			closestPair = closestPairFromLeft;
		} else if (closestPairFromLeft.distance() < closestPairFromRight.distance()) {
			closestPair = closestPairFromLeft;
		} else {
			closestPair = closestPairFromRight;
		}
		long delta = closestPair.distance();
		PairOfPoints closestPairFromDeltaBand = this.closestPairFromDeltaBand(Py, seperationLine, delta);
		if (closestPairFromDeltaBand != null) {
			if (closestPairFromDeltaBand.distance() < closestPair.distance()) {
				closestPair = closestPairFromDeltaBand;
			}
		}

		return closestPair;
	}

	private SeparatedPair separatedReferenceList(ReferenceList referenceList, int separationLine) {
		SeparatedPair separatedPair = new SeparatedPair();
		int sizeOfReferenceList = referenceList.size();
		separatedPair._leftList = new ReferenceList(sizeOfReferenceList);
		separatedPair._rightList = new ReferenceList(sizeOfReferenceList);
		int separationX = this.pointSet().elementAt(separationLine).x;
		for (int index = 0; index < sizeOfReferenceList; index++) {
			Integer pointReference = referenceList.elementAt(index);
			Point pointReferencedByIndex_i = this.pointSet().elementAt(pointReference);
			if (pointReferencedByIndex_i.x < separationX) {
				separatedPair._leftList.add(pointReference);
			} else if (pointReferencedByIndex_i.x > separationX) {
				separatedPair._rightList.add(pointReference);
			} else {
				if (pointReference < separationLine) {
					separatedPair._leftList.add(pointReference);
				} else {
					separatedPair._rightList.add(pointReference);
				}
			}
		}
		return separatedPair;
	}

	public PairOfPoints solveByDivideAndConquer(PointSet pointSetForSolve) {
		if (pointSetForSolve == null || pointSetForSolve.size() <= 1) {
			return null;
		}
		this.setPointSet(pointSetForSolve);
		ReferenceList Px = new ReferenceListOrderedByX(this.pointSet());
		ReferenceList Py = new ReferenceListOrderedByY(this.pointSet());
		PairOfPoints closestPair = this.solveRecursively(Px, Py);
		return closestPair;
	}

	public PairOfPoints solveByComparingAllPairs(PointSet pointSetForSolve) {
		if (pointSetForSolve == null || pointSetForSolve.size() <= 1) {
			return null;
		}

		Point closestPair_point_i = null;
		Point closestPair_point_j = null;
		long minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < pointSetForSolve.size() - 1; i++) {
			Point point_i = pointSetForSolve.elementAt(i);
			for (int j = i + 1; j < pointSetForSolve.size(); j++) {
				Point point_j = pointSetForSolve.elementAt(j);
				long distanceBetween_i_j = point_i.distanceTo(point_j);
				if (distanceBetween_i_j < minDistance) {
					minDistance = distanceBetween_i_j;
					closestPair_point_i = point_i;
					closestPair_point_j = point_j;
				}
			}
		}
		PairOfPoints closestPair = new PairOfPoints(closestPair_point_i, closestPair_point_j);
		return closestPair;

	}

	private ReferenceList deltaBand(ReferenceList Py, Integer separationLine, long delta) {
		ReferenceList Sy = new ReferenceList(Py.size());
		int separationX = this.pointSet().elementAt(separationLine).x;

		long sqrtOfDelta = (long) Math.sqrt(delta);
		for (int index = 0; index < Py.size(); index++) {
			Integer pointReference = Py.elementAt(index);
			Point point = this.pointSet().elementAt(pointReference);
			if (Math.abs(point.x - separationX) <= sqrtOfDelta) {
				Sy.add(pointReference);
			}
		}
		return Sy;
	}

	private PairOfPoints closestPairFromHereToNeighbors(ReferenceList Sy, int hereIndex) {
		Point herePoint = this.pointSet().pointReferenceByIndex(Sy, hereIndex);
		Point closestNeighborPoint = this.pointSet().pointReferenceByIndex(Sy, hereIndex + 1);

		long minDistance = herePoint.distanceTo(closestNeighborPoint);
		int lastNeighborIndex = Math.min((hereIndex + MAX_NUMBER_OF_NEIGHBORS), (Sy.size() - 1));
		for (int neighborIndex = (hereIndex + 2); neighborIndex <= lastNeighborIndex; neighborIndex++) {
			Point neighborPoint = this.pointSet().pointReferenceByIndex(Sy, neighborIndex);
			long distanceFromHereToNeighbor = herePoint.distanceTo(neighborPoint);
			if (distanceFromHereToNeighbor < minDistance) {
				minDistance = distanceFromHereToNeighbor;
				closestNeighborPoint = neighborPoint;
			}
		}
		return new PairOfPoints(herePoint, closestNeighborPoint);
	}

	private PairOfPoints closestPairFromDeltaBand(ReferenceList Py, Integer separationLine, long delta) {
		PairOfPoints closestPair = null;
		ReferenceList Sy = this.deltaBand(Py, separationLine, delta);
		int Sy_size = Sy.size();
		if (Sy_size > 1) {
			int hereIndex = 0;
			closestPair = this.closestPairFromHereToNeighbors(Sy, hereIndex);
			for (hereIndex = 1; hereIndex < Sy_size - 1; hereIndex++) {
				PairOfPoints closestPairToNeighbors = this.closestPairFromHereToNeighbors(Sy, hereIndex);
				if (closestPairToNeighbors.distance() < closestPair.distance()) {
					closestPair = closestPairToNeighbors;
				}
			}
		}
		return closestPair;

	}

	public PairOfPoints solveByHybrid(PointSet pointSetForSolve) {
		if (pointSetForSolve == null || pointSetForSolve.size() <= 1) {
			return null;
		}
		this.setPointSet(pointSetForSolve);
		ReferenceList Px = new ReferenceListOrderedByX(this.pointSet());
		ReferenceList Py = new ReferenceListOrderedByY(this.pointSet());
		PairOfPoints closestPair = this.solveByHybridRecursively(Px, Py);
		return closestPair;
	}

	private PairOfPoints solveByComparingAllPairsForSmallSet(ReferenceList Px) {
		if (Px.size() <= 1) {
			return null;
		}
		Point closestPair_point_i = this.pointSet().pointReferenceByIndex(Px, 0);
		Point closestPair_point_j = this.pointSet().pointReferenceByIndex(Px, 1);
		long minDistance = closestPair_point_i.distanceTo(closestPair_point_j);
		for (int i = 0; i < Px.size(); i++) {
			Point point_i = this.pointSet().pointReferenceByIndex(Px, i);
			for (int j = i + 1; j < Px.size(); j++) {
				Point point_j = this.pointSet().pointReferenceByIndex(Px, j);
				long distanceBetween_i_j = point_i.distanceTo(point_j);
				if (distanceBetween_i_j < minDistance) {
					minDistance = distanceBetween_i_j;
					closestPair_point_i = point_i;
					closestPair_point_j = point_j;
				}
			}
		}
		PairOfPoints closestPair = new PairOfPoints(closestPair_point_i, closestPair_point_j);
		return closestPair;

	}

	private PairOfPoints solveByHybridRecursively(ReferenceList Px, ReferenceList Py) {
		if (Px.size() < this.minRecursiveSize()) {
			return this.solveByComparingAllPairsForSmallSet(Px);
		}
		PairOfPoints closestPair;
		int separationLine = Px.elementAt(Px.size() / 2);

		SeparatedPair separatedPairFromPx = this.separatedReferenceList(Px, separationLine);
		SeparatedPair separatedPairFromPy = this.separatedReferenceList(Py, separationLine);

		ReferenceList Qx = separatedPairFromPx._leftList;
		ReferenceList Rx = separatedPairFromPx._rightList;
		ReferenceList Qy = separatedPairFromPy._leftList;
		ReferenceList Ry = separatedPairFromPy._rightList;

		PairOfPoints closestPairFromLeft = this.solveByHybridRecursively(Qx, Qy);
		PairOfPoints closestPairFromRight = this.solveByHybridRecursively(Rx, Ry);

		if (closestPairFromLeft == null) {
			closestPair = closestPairFromRight;
		} else if (closestPairFromRight == null) {
			closestPair = closestPairFromLeft;
		} else if (closestPairFromLeft.distance() < closestPairFromRight.distance()) {
			closestPair = closestPairFromLeft;
		} else {
			closestPair = closestPairFromRight;
		}
		long delta = closestPair.distance();
		PairOfPoints closestPairFromDeltaBand = this.closestPairFromDeltaBand(Py, separationLine, delta);
		if (closestPairFromDeltaBand != null) {
			if (closestPairFromDeltaBand.distance() < closestPair.distance()) {
				closestPair = closestPairFromDeltaBand;
			}
		}
		return closestPair;

	}

	private PointSet pointSet() {
		return this._pointSet;
	}

	private void setPointSet(PointSet newPointSet) {
		this._pointSet = newPointSet;
	}

	public int minRecursiveSize() {
		return this._minRecursionSize;
	}

	public void setMinRecursiveSize(int newMinRecursiveSize) {
		this._minRecursionSize = newMinRecursiveSize;
	}

	public FindClosestPair() {
		this.setPointSet(null);
		this.setMinRecursiveSize(FindClosestPair.DEFAULT_MIN_RECURSIVE_SIZE);
	}
}
