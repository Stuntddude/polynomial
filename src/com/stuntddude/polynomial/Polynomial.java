package com.stuntddude.polynomial;

import processing.core.PApplet;

public final class Polynomial extends PApplet {
	public static final Polynomial polynomial = new Polynomial();

	@Override
	public void settings() {
		size(1280, 720);
		smooth(8);
	}

	@Override
	public void setup() {
		frameRate(60);
	}

	@Override
	public void draw() {
		background(0xFFFFFFFF); //white



		//apply grid scaling
		pushMatrix();
		translate(width/2, height/2);
		float scale = 20.f; //TODO: make scale dependent on window size
		scale(scale, -scale);



		//draw grid
		stroke(0xFF7F7F7F); //grey
		strokeWeight(1/scale);

		//TODO: optimize this section
		for (int i = -100; i <= 100; ++i) {
			line(i, -100, i, 100); //vertical lines
			line(-100, i, 100, i); //horizontal lines
		}

		//draw axes in bold
		stroke(0xFF000000); //black
		strokeWeight(2/scale);

		line(0, -100, 0, 100); //vertical
		line(-100, 0, 100, 0); //horizontal

		//draw an arrow on the ends of each axis
		fill(0xFF000000); //black

		float hy = height/scale/2, hx = width/scale/2;
		float a = 0.25f, b = 0.5f;
		triangle(0,  hy, a,  hy - b, -a,  hy - b); //top
		triangle(0, -hy, a, -hy + b, -a, -hy + b); //bottom
		triangle( hx, 0,  hx - b, a,  hx - b, -a); //right
		triangle(-hx, 0, -hx + b, a, -hx + b, -a); //left



		//un-apply grid scaling
		popMatrix();



		noLoop();
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { Polynomial.class.getName() }, polynomial);
	}
}
