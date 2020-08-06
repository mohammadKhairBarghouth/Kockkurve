package mainPackage;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Vector {
	double length, rotation;
	public Point beginningPoint, endPoint;

	public Vector(Point beginningPoint, double length, double rotation) {
		this.length = length;
		this.rotation = rotation;
		this.beginningPoint = beginningPoint;
		endPoint = new Point(beginningPoint.x + length, beginningPoint.y, null);
		endPoint.rotate(beginningPoint, rotation);
	}

	public void draw(Group canvas) {
		canvas.getChildren().add(new Line(beginningPoint.x, beginningPoint.y, endPoint.x, endPoint.y));
	}

	public void draw(Group canvas, Color color) {
		Line line = new Line(beginningPoint.x, beginningPoint.y, endPoint.x, endPoint.y);
		line.setFill(color);
		canvas.getChildren().add(line);
	}

	public double getRotation() {
		return this.rotation;
	}

	public void buildRecursion(Group canvas, int round) {
		
		//Building new vectors on the main vector. 
		Point p1 = new Point(this.beginningPoint.x, this.beginningPoint.y, null);
		Vector v1 = new Vector(p1, this.length / 3,this.rotation);

		Point p2 = new Point(this.beginningPoint.x + (this.length / 3), this.beginningPoint.y, null);
		p2.rotate(beginningPoint, this.rotation);
		Vector v2 = new Vector(p2, this.length / 3, this.rotation + 60);

		Point p3 = new Point(v2.endPoint.x, v2.endPoint.y, null);
		Vector v3 = new Vector(p3, this.length / 3, this.rotation - 60);
		
		Point p4 = new Point(this.beginningPoint.x + (this.length / 3 * 2), this.beginningPoint.y, null);
		p4.rotate(beginningPoint, this.rotation);
		Vector v4 = new Vector(p4, this.length / 3, this.rotation);

		if (round == 0) {
			v1.draw(canvas);
			v2.draw(canvas);
			v4.draw(canvas);
			v3.draw(canvas);
			return;

		} else {
			v1.buildRecursion(canvas, round - 1);
			v2.buildRecursion(canvas, round - 1);
			v3.buildRecursion(canvas, round - 1);
			v4.buildRecursion(canvas, round - 1);
		}
	}
}
