package huellamolecular;

import java.io.IOException;

public class fingerprint {
	public static void main(String[] args) throws IOException {

		try {
			String opcion = args[0];
			// genera un archivo dosis.txt de la siguiente manera: SCSP803280_000184304_1460
			// 0.578125 0.5466667 0.525 0.50769234
			if (opcion.compareTo("generarDosis") == 0 || opcion.compareTo("1") == 0) {
				try {
					geneDosis dosiscgene = new geneDosis();
					String vcfFile = args[1];
					dosiscgene.loadVCF(vcfFile);
				} catch (Exception e) {
					System.out.println("Try: java -jar fingerprint.jar [generarDosis | 1] [path_vcf] > snps_dosis.txt");
				}
			}

			// recibe el archivo dosis.txt generado con generarDosis, y selecciona los SNPs
			// que tienen un abanico en sus dosis alelicas.
			else if (opcion.compareTo("seleccionarDosisAbanico") == 0 | opcion.compareTo("2") == 0) {
				try {
					seleccionarAbanicoDosis abanicodosis = new seleccionarAbanicoDosis();
					abanicodosis.loadDosis(args[1]);
				} catch (Exception e) {
					System.out.println(
							"Try: java -jar fingerprint.jar [seleccionarDosisAbanico|2] [snps_dosis.txt (generado con opción generarDosis)] > snps_dosis_abanico.txt");
				}
			}

			// genera a partir del vcd un txt con los alelos por cada snps: 17120
			// SCSP803280_000007146 C T 1:1 6:4 4:6
			else if (opcion.compareTo("generarAlelosVCF") == 0 | opcion.compareTo("3") == 0) {
				try {
					generaralelos alelos = new generaralelos();
					alelos.getalelos(args[1]);
				} catch (Exception e) {
					System.out.println("Try: java -jar fingerprint.jar [generarAlelosVCF|3] [path_vcf] > snps_alelos.txt");
				}
			}

			// Recibe el archivos snps_alelos.txt generado por generarAlelosVCF y selecciona
			// los SNPs que cumplen con los filtros de los Australianos.
			if (opcion.compareTo("seleccionarDosisAUS") == 0 || opcion.compareTo("4") == 0) {
				try {
					filtrosalelicosAUS fa = new filtrosalelicosAUS();
					fa.filtrarporClases(args[1]);
				} catch (Exception e) {
					System.out.println(
							"Try: java -jar fingerprint.jar [seleccionarDosisAUS|4] [snps_alelos.txt (generado con opción generarAlelosVCF)] > snps_dosis_aus.txt");
				}
			}

			// Recibe un listado de snps a seleecionar en el vcf.
			else if (opcion.compareTo("FiltrarVCF") == 0 || opcion.compareTo("5") == 0) {
				try {
					FiltrarVCF al = new FiltrarVCF();
					al.filtrarvcf(args[1], args[2]);
				} catch (Exception e) {
					System.out.println(
							"Try: java -jar fingerprint.jar [FiltrarVCF|5] [snps_dosis_aus.txt | snps_dosis_abanico.txt] [path_vcf]");
				}
			}
			// Recibe un listado de snps a seleecionar en el vcf.
			else if (opcion.compareTo("ReducirHuellaVCF") == 0 || opcion.compareTo("6") == 0) {
				try {
					VCFgetfilterprint vcfmatrix = new VCFgetfilterprint();;
					vcfmatrix.VCFfingerprint(args[1], args[2], 0.0, 0.0);
				} catch (Exception e) {
					System.out.println(
							"Try: java -jar fingerprint.jar [ReducirHuellaVCF|6] [path_vcf_original] [path_vcf_filtrado (nombre del archivo resultado)]");
				}
			}
			// Recibe un listado de snps a seleecionar en el vcf.
			else if (opcion.compareTo("similitudGeneitcaCCdist" ) == 0 || opcion.compareTo("7") == 0) {
				try {
					VCFgetfilterprint vcfmatrix = new VCFgetfilterprint();;
					vcfmatrix.VCFload(args[1]);
					System.out.println("p:2,GD:3");
					vcfmatrix.getSimilitudeStats(System.out);
				} catch (Exception e) {
					System.out.println(
							"Try: java -jar fingerprint.jar [similitudGeneitcaCCdist|7] [path_vcf] ");
				}
			}

			// Recibe un listado de snps a seleecionar en el vcf.
			else if (opcion.compareTo("-h") == 0 || opcion.compareTo("-help") == 0 || opcion.compareTo("--help") == 0
					|| opcion.compareTo("") == 0) {
				try {
					System.out.println("Try: java -jar fingerprint.jar [generarDosis|1] [path_vcf] > snps_dosis.txt");
					System.out.println("Try: java -jar fingerprint.jar [seleccionarDosisAbanico|2] [snps_dosis.txt (generado con opción generarDosis)] > snps_dosis_abanico.txt");
					System.out.println("Try: java -jar fingerprint.jar [generarAlelosVCF|3] [path_vcf] > snps_alelos.txt");
					System.out.println("Try: java -jar fingerprint.jar [seleccionarDosisAUS|4] [snps_alelos.txt (generado con opción generarAlelosVCF)] > snps_dosis_aus.txt");
					System.out.println("Try: java -jar fingerprint.jar [FiltrarVCF|5] [snps_dosis_aus.txt | snps_dosis_abanico.txt] [path_vcf]");
					System.out.println("Try: java -jar fingerprint.jar [ReducirHuellaVCF|6] [path_vcf_original] [path_vcf_filtrado (nombre del archivo resultado)]");
					System.out.println("Try: java -jar fingerprint.jar [similitudGeneitcaCCdist|7] [path_vcf] ");

				} catch (Exception e) {
					System.out.println("Try java -jar fingerprint.jar -help");
				}
			}
		} catch (Exception e) {
			System.out.println("Try java -jar fingerprint.jar -help");
		}

	}

}
