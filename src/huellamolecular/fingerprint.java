package huellamolecular;

import java.io.IOException;

public class fingerprint {
	public static void main(String[] args) throws IOException {

		String opcion = args[0];

		try {
			if (opcion.compareTo("generarDosis") == 0) {
				geneDosis dosiscgene = new geneDosis();
				String vcfFile = args[1];
				dosiscgene.loadVCF(vcfFile);
			}
		} catch (Exception e) {
			System.out.println("Try: java -jar fingerprint.java generarDosis [path_vcf]");
		}

		

	}

}
