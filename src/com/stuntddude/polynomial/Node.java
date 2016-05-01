package com.stuntddude.polynomial;

import processing.core.PApplet;

public final class Node {
	public static final Polynomial context = Polynomial.context;
	public static final float r = 0.4f;

	public float x, y;

	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public boolean contains(float xx, float yy) {
		return PApplet.dist(x, y, xx, yy) < r;
	}

	public void move(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public void draw() {
		context.strokeWeight(1/Polynomial.scale);
		context.stroke(0xFF000000); //black
		context.fill(0xFFDFDF5F); //mild yellow

		context.ellipse(x, y, r*2, r*2);
	}
}
