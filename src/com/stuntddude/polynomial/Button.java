package com.stuntddude.polynomial;

import processing.core.PConstants;

public final class Button {
	private static final Polynomial context = Polynomial.context;

	private final int x, y, w, h;
	private final String s;

	public Button(int x, int y, int w, int h, String s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
	}

	public boolean contains(int xx, int yy) {
		return xx < x + w/2 && xx > x - w/2 && yy < y + h/2 && yy > y - h/2;
	}

	public void draw() {
		context.rectMode(PConstants.CENTER);
		context.noStroke();
		if (contains(context.mouseX, context.mouseY))
			context.fill(0x7F66EE66); //partially transparent pastel green
		else
			context.fill(0x7F7F7F7F); //partially transparent neutral grey
		context.rect(x, y, w, h, 5);

		context.textAlign(PConstants.CENTER, PConstants.CENTER);
		context.fill(0xFFFFFFFF); //white
		context.textSize(16);
		context.text(s, x, y - 2);
	}
}
