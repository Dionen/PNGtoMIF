import utils.EntradaTeclado;
import image_treatment.ImageSize;
import image_treatment.Pixel;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

// 20x20, 52 na borda
// 196x61, 407 na borda

public class main {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
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
		
		out += "-- " + image.getWidth()/8 + " x " + image.getHeight()/8 + "\n\n";
		out += ImageSize.convertToMIF(image, address);
		
		/*
		// Salva em um arquivo
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("temo.txt"), "utf-8"))) {
			writer.write(out);
			writer.flush();
			writer.close();
		}*/
		
		String newLine = System.getProperty("line.separator");
		out.replace("\n", newLine);
		try (PrintWriter pw = new PrintWriter(temp + ".txt")) {
			   pw.println(out);
			}
		
		//System.out.println(out);
	}

}
