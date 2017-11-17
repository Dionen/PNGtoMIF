package image_treatment;

public class Pixel {
	private int x;
	private int y;
	private int RGB;

	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
		RGB = -2;
	}

	public Pixel(int x, int y, int RGB) {
		this.x = x;
		this.y = y;
		this.RGB = RGB;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRGB() {
		return RGB;
	}
}
