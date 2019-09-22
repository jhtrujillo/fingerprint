package huellamolecular;

public class ComprarDosisHuellavsTargeted {

	public void comparardosis(String dosisSecuenciacion, String dosisTargeted, String SNPChr, String SnpPos,
			String Individuo) {
		archivos arDosis = new archivos();
		archivos arTar = new archivos();

		String[] dosisSec = arDosis.leerfichero2(dosisSecuenciacion);
		String[] dosisTar = arTar.leerfichero2(dosisTargeted);

		String[] individuosSec = dosisSec[0].split("\t");
		int posIndiSec = 0;

		String resultado = "";

		for (int i = 0; i < individuosSec.length; i++) {
			if (individuosSec[i].compareTo(Individuo) == 0) {
				posIndiSec = i;
				i = individuosSec.length;
			}
		}

		for (int i = 1; i < arDosis.numerolineas; i++) {

			String chr = dosisSec[i].split("	")[0];
			String pos = dosisSec[i].split("	")[1];

			if (SNPChr.compareTo(chr) == 0 && SnpPos.compareTo(pos) == 0) {
				String dosis = dosisSec[i].split("\t")[posIndiSec];
				resultado = chr + "\t" + pos + "\t" + Individuo + "\t" + dosis + "\t";
				// System.out.print(chr + " " + pos + " "+ Individuo+" " + dosis+" ");
				i = arDosis.numerolineas;
			}
		}

		String[] individuosTar = dosisTar[0].replace("PV_", "").replace("_R", "").split("\t");
		int posIndiTar = 0;

		for (int i = 0; i < individuosTar.length; i++) {
			if (individuosTar[i].compareTo(Individuo) == 0) {
				posIndiTar = i;
				i = individuosTar.length;
			}
		}

		// System.out.println(posIndiTar);
		String dosisTarvalue = "";

		for (int i = 1; i < arTar.numerolineas; i++) {

			String chr = dosisTar[i].split("	")[0];
			String pos = dosisTar[i].split("	")[1];

			if (SNPChr.compareTo(chr) == 0 && SnpPos.compareTo(pos) == 0) {
				dosisTarvalue = dosisTar[i].split("\t")[posIndiTar];
				resultado += dosisTarvalue;
				// System.out.println(dosis);
				i = arTar.numerolineas;
			}
		}

		if (dosisTarvalue.compareTo(SNPChr) != 0) {
			System.out.println(resultado);
		}

	}

	public void comprarindividuos(String dosisSecuenciacion, String dosisTargeted, String SNPChr, String SnpPos) {
		archivos arDosis = new archivos();
		String[] dosisSec = arDosis.leerfichero2(dosisSecuenciacion);
		String[] individuosSec = dosisSec[0].split("\t");

		for (int i = 2; i < individuosSec.length; i++) {

			comparardosis(dosisSecuenciacion, dosisTargeted, SNPChr, SnpPos, individuosSec[i]);
			// System.out.println(individuosSec[i]);

		}

	}
	
	
	public static void main(String[] args) {
		String dosisSecuenciacion = "/home/estuvar4/Documents/validacion_huella_molecular_gwas_targeted/dosis_huellaRanqueada_1erenvio.txt";
		String dosisTargeted = "/home/estuvar4/Documents/validacion_huella_molecular_gwas_targeted/dosis_CEN_132101_FreeBayes_SNPs_Raw.txt";

		ComprarDosisHuellavsTargeted cdht = new ComprarDosisHuellavsTargeted();
		//cdht.comparardosis(dosisSecuenciacion, dosisTargeted, "super_59", "421521","103");
		cdht.comprarindividuos(dosisSecuenciacion, dosisTargeted, "super_59", "421521");
		//cdht.comprarindividuos(args[0], args[1],args[1], args[2]);

	}

}
