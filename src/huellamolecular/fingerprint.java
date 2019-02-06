package huellamolecular;

import java.io.IOException;

public class fingerprint {
	public static void main(String[] args) throws IOException {

		String opcion = args[0];

		if (opcion.compareTo("generarDosis") == 0) {
			try {
				geneDosis dosiscgene = new geneDosis();
				String vcfFile = args[1];
				dosiscgene.loadVCF(vcfFile);
			} catch (Exception e) {
				System.out.println("Try: java -jar fingerprint.java generarDosis [path_vcf]");
			}
		}
		
		
		if (opcion.compareTo("seleccionarAbanicoDosis") == 0) {
			try {
				filtrarporDosisalelicas abanicodosis = new filtrarporDosisalelicas();
				abanicodosis.loadDosis(args[1]);
			} catch (Exception e) {
				System.out.println("Try: java -jar fingerprint.jar seleccionarAbanicoDosis [dosis.txt (generado con opción generarDosis)]");
			}
		}


	}

}
