package image_treatment;

import java.awt.image.BufferedImage;

public class MIF_Conversion {

	public static String convertToMIF(BufferedImage image, int address) {
		int i, j, x, y, height, width;
		String s = "";

		height = image.getHeight();
		width = image.getWidth();

		for (i = 0; i < height; i += 8) {
			for (j = 0; j < width; j += 8) {
				for (x = i; x < i + 8; x++) {
					s += "\t" + address + "  :   ";
					for (y = j; y < j + 8; y++) {
						if (image.getRGB(y, x) == -1
								|| image.getRGB(y, x) == -460552) {
							s += "1";
						} else {
							s += "0";
						}
					}
					s += ";\n";
					address++;
				}
				s += "\n";

			}
		}
		return s;
	}

}
