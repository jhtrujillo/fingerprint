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
				System.out.println("Try: java -jar fingerprint.java generarDosis [path_vcf] > dosis.txt");
			}
		}
		
		
		if (opcion.compareTo("seleccionarDosisAbanico") == 0) {
			try {
				seleccionarAbanicoDosis abanicodosis = new seleccionarAbanicoDosis();
				abanicodosis.loadDosis(args[1]);
			} catch (Exception e) {
				System.out.println("Try: java -jar fingerprint.jar seleccionarDosisAbanico [dosis.txt (generado con opción generarDosis)]");
			}
		}
		
		if (opcion.compareTo("generarAlelosVCF") == 0) {
			try {
				generaralelos alelos = new generaralelos();
				alelos.getalelos(args[1]);
			} catch (Exception e) {
				System.out.println("Try: java -jar fingerprint.jar generarAlelosVCF [path_vcf] > alelos.txt");
			}
		}
		
		if (opcion.compareTo("seleccionarDosisAUS") == 0) {
			try {
				filtrosalelicosAUS fa = new filtrosalelicosAUS();
				fa.filtrarporClases(args[1]);
			} catch (Exception e) {
				System.out.println("Try: java -jar fingerprint.jar seleccionarDosisAUS [alelos.txt (generado con opción generarAlelosVCF)]");
			}
		}

	}

}
