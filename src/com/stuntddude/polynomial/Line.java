package com.stuntddude.polynomial;

/** XXX: unfinished */
public final class Line {
	private static final Polynomial context = Polynomial.context;

	private final int color;
	private final Node a, b;

	public Line(Node a, Node b) {
		color = context.color(context.random(50, 250),
		                      context.random(50, 250),
		                      context.random(50, 250));
		this.a = a;
		this.b = b;
	}

	public float at(float x) {
		float slope = slope(); //derive the slope from the points
		float intercept = a.y - (slope * a.x); //derive the intercept from the slope and one of the points

		return slope*x + intercept;
	}

	public float slope() {
		return (b.y - a.y)/(b.x - a.x);
	}

	public void draw() {
		context.strokeWeight(1/Polynomial.scale);
		context.stroke(color);

		context.line(-100, at(-100), 100, at(100));

		//DEBUG
		if (Float.isNaN(slope()))
			System.out.println("NaN!");
		else if (Float.isInfinite(slope()))
			System.out.println("Infinity!");
	}
}
