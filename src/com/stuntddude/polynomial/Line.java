package com.stuntddude.polynomial;

import processing.core.PConstants;

public final class Line {
	private static final Polynomial context = Polynomial.context;

	private final int color;
	private final Node a, b;

	public Line(Node a, Node b) {
		context.colorMode(PConstants.HSB, 250);
		color = context.color(context.random(0, 250), context.random(100, 250), context.random(150, 200));
		this.a = a;
		this.b = b;
	}

	public float at(float x) {
		float slope = slope(); //derive the slope from the points
		float intercept = a.y - (slope * a.x); //derive the intercept from the slope and one of the points

		if (Float.isNaN(slope))
			return a.y;
		return slope*x + intercept;
	}

	public float slope() {
		return (b.y - a.y)/(b.x - a.x);
	}

	public void draw() {
		context.strokeWeight(1.5f/Polynomial.scale);
		context.stroke(color);

		context.line(-100, at(-100), 100, at(100));
	}
}
