package com.stuntddude.polynomial;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public final class Polynomial extends PApplet {
	public static final Polynomial context = new Polynomial();

	public static final float scale = 20.f;

	private final List<Node> nodes = new ArrayList<>();
	private final List<Line> lines = new ArrayList<>();

	@Override
	public void settings() {
		size(1280, 720);
		smooth(8);
	}

	@Override
	public void setup() {
		float hy = height/scale/2, hx = width/scale/2;

		for (int i = 0; i < 2; ++i) {
			nodes.add(new Node(random(-hx, hx), random(-hy, hy)));
			nodes.add(new Node(random(-hx, hx), random(-hy, hy)));
			lines.add(new Line(nodes.get(i*2), nodes.get(i*2 + 1)));
		}
	}

	@Override
	public void draw() {
		background(0xFFFFFFFF); //white



		//apply grid scaling
		pushMatrix();
		translate(width/2, height/2);
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



		//draw lines
		for (Line line : lines)
			line.draw();

		//draw nodes
		for (Node node : nodes)
			node.draw();



		//un-apply grid scaling
		popMatrix();



		noLoop();
	}

	private Node dragging = null;

	@Override
	public void mousePressed() {
		for (Node node : nodes)
			if (node.inside((mouseX - width/2.f)/scale, (-mouseY + height/2.f)/scale))
				dragging = node;
	}

	@Override
	public void mouseDragged() {
		if (dragging != null) {
			dragging.x = (mouseX - width/2.f)/scale;
			dragging.y = (height/2.f - mouseY)/scale;
			loop();

			//move the node back into the window without changing its line's slope
			float hy = height/scale/2, hx = width/scale/2;
			if (dragging.x > hx || dragging.x < -hx || dragging.y > hy || dragging.y < -hy) {
				Line line = lines.get(nodes.indexOf(dragging)/2);

				if      (dragging.y >  hy && (line.at(hx) >  hy || line.at(-hx) >  hy)) //top edge
					dragging.move(( hy - dragging.y)/line.slope(),  hy - dragging.y);
				else if (dragging.y < -hy && (line.at(hx) < -hy || line.at(-hx) < -hy)) //bottom edge
					dragging.move((-hy - dragging.y)/line.slope(), -hy - dragging.y);
				else if (dragging.x >  hx) //right edge
					dragging.move( hx - dragging.x, ( hx - dragging.x)*line.slope());
				else if (dragging.x < -hx) //left edge
					dragging.move(-hx - dragging.x, (-hx - dragging.x)*line.slope());
			}
		}
	}

	@Override
	public void mouseReleased() {
		dragging = null;
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { Polynomial.class.getName() }, context);
	}
}
