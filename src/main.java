import utils.EntradaTeclado;
import image_treatment.MIF_Conversion;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException,
			IOException {
		String out = new String(), temp = new String();
		int address;
		BufferedImage image;

		System.out.print("Insira nome do arquivo (sem a extensão): ");
		temp = EntradaTeclado.leString();
		System.out.print("Insira primeiro endereço da memória: ");
		address = EntradaTeclado.leInt();
		File fp = new File(temp + ".png");

		out += "-- " + temp + "\n";

		if (!fp.canRead()) {
			System.out.println("Can't open file.");
			return;
		}

		try {
			image = ImageIO.read(fp);
		} catch (IOException e) {
			System.out.println("Error while reading image.");
			e.printStackTrace();
			return;
		}

		if (image.getWidth() % 8 != 0 || image.getHeight() % 8 != 0) {
			System.out.println("Invalid size. Must be multiple of 8.");
			return;
		} else {
			out += "-- " + image.getWidth() / 8 + " x " + image.getHeight() / 8
					+ "\n\n";
			out += MIF_Conversion.convertToMIF(image, address);
		}

		String newLine = System.getProperty("line.separator");
		out.replace("\n", newLine);
		try (PrintWriter pw = new PrintWriter(temp + ".txt")) {
			pw.println(out);
		}
	}
}
