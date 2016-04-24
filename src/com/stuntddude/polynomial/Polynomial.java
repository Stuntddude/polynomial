package com.stuntddude.polynomial;

import processing.core.PApplet;

public final class Polynomial extends PApplet {
	public static final Polynomial polynomial = new Polynomial();

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		frameRate(60);
	}

	@Override
	public void draw() {
		background(0xFFFFFFFF); //white
		noLoop();
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { Polynomial.class.getName() }, polynomial);
	}
}
