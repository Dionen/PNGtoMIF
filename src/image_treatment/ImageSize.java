package image_treatment;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Operações que retornam atributos relacionados a dimensoes de dada imagem.
 * 
 * @author João Guino nº9283607
 */
public class ImageSize {

	/**
	 * Encontra o primeiro pixel não branco em dada imagem.
	 * 
	 * @param image
	 *            Imagem a ser usada.
	 * @throws IOException
	 *             Caso imagem toda branca.
	 */
	public static Pixel first_pixel(BufferedImage image) throws IOException {
		int height, width;

		height = image.getHeight();
		width = image.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (image.getRGB(x, y) != -1) {
					System.out.println("Ponto inicial: " + x + "x " + y + "y");
					return new Pixel(x, y);
				}
			}
		}
		throw new IOException("Blank image");
	}


	public static String convertToMIF(BufferedImage image, int address) {
		int i, j, x, y, height, width;
		String s = "";
		
		height = image.getHeight();
		width = image.getWidth();
		
		for (i = 0; i < height; i+= 8) {
			for (j = 0; j < width; j+= 8) {
				for (x = i; x < i+8; x++){
					s += "\t" + address + "  :   ";
					for (y = j; y < j+8; y++){
						if (image.getRGB(y, x) == -1 || image.getRGB(y, x) == -460552){
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

	
	/**
	 * Encontra a largura do objeto, isto é, a maior linha initerrupta de pontos
	 * no eixo x;
	 * 
	 * @param image
	 *            Imagem a ser usada.
	 */
	public static void getMaxWidth(BufferedImage image) {
		int height, width, counter;
		int max_width = 0;

		height = image.getHeight();
		width = image.getWidth();

		for (int y = 0; y < height; y++) {
			counter = 0;
			for (int x = 0; x < width; x++) {
				if (image.getRGB(x, y) != -1) {
					counter++;
				} else if (image.getRGB(x, y) == -1 && counter != 0) {
					if (counter > max_width)
						max_width = counter;
					counter = 0;
				}
			}
			if (counter > max_width)
				max_width = counter;
		}

		System.out.println("Width: " + max_width);
	}

	/**
	 * Encontra a altura do objeto, isto é, a maior linha initerrupta de pontos
	 * no eixo y;
	 * 
	 * @param image
	 *            Imagem a ser usada.
	 */
	public static void getMaxHeight(BufferedImage image) {
		int height, width, counter;
		int max_height = 0;

		height = image.getHeight();
		width = image.getWidth();

		for (int x = 0; x < width; x++) {
			counter = 0;
			for (int y = 0; y < height; y++) {
				if (image.getRGB(x, y) != -1) {
					counter++;
				} else if (image.getRGB(x, y) == -1 && counter != 0) {
					if (counter > max_height)
						max_height = counter;
					counter = 0;
				}
			}
			if (counter > max_height)
				max_height = counter;
		}

		System.out.println("Hight: " + max_height);
	}
}
