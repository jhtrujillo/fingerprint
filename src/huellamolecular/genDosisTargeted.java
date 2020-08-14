package huellamolecular;

import java.io.IOException;

public class genDosisTargeted {

	public void generarDosis(String PathFile) {
		archivos ar = new archivos();
		String[] datos = ar.leerfichero(PathFile);
		int bandera = 0;

		for (int i = 0; i < ar.numerolineas; i++) {
			String linea = datos[i];

			if (linea.contains("CHROM") == true) {

				String[] individuos = linea.split("	");
				
				System.out.print("Chr\tPos\t" + "Ref\t"+ "Alt\t");
				
				for (int j = 9; j < individuos.length; j++) {
					System.out.print(individuos[j] + "\t");
				}

				bandera = 1;

			}

			if (bandera == 1) {

				String[] genotipos = linea.split("	");

				String snp = genotipos[0];
				String ref = genotipos[3];
				String Alt = genotipos[4];

				if (Alt.contains(",") == false) {
					
					String chr=snp.split(":")[0];
					int pos=0;
					
					int lentPos=snp.split(":").length;
					
					if (lentPos>1) {
						pos=Integer.parseInt( snp.split(":")[1].split("-")[0] ) + 200;
					}else {
						chr=snp.split(":")[0];
					}

					System.out.print(chr+"\t"+pos+ "\t" + ref + "\t" + Alt + "\t");

					for (int j = 9; j < genotipos.length; j++) {

						int tamGenotipo = genotipos[j].split(":").length;

						if (tamGenotipo == 8) {

							float countRef = 0;
							float countAlt = 0;
							float dosage = 0;

													
							if (genotipos[j].split(":")[3].compareTo(".") == 0 && genotipos[j].split(":")[5].compareTo(".") == 0) {
								dosage=-1;
							}
							else {
								
								countRef=Float.parseFloat(genotipos[j].split(":")[3].replace(".","0"));
								countAlt=Float.parseFloat(genotipos[j].split(":")[5].replace(".","0"));
								
								if((countRef + countAlt) > 0){
					    			 dosage = countRef / (countRef + countAlt);
				    			}
								
							}
							System.out.print(dosage+"\t");
							//System.out.print(dosage+"["+genotipos[j]+"]" + "\t");
						}

						else if (tamGenotipo == 6) {

							float countRef = 0;
							float countAlt = 0;
							float dosage = 0;
							
							
							if (genotipos[j].split(":")[3].compareTo(".") == 0 && genotipos[j].split(":")[5].compareTo(".") == 0) {
								dosage=-1;
							}
							else {
								
								countRef=Float.parseFloat(genotipos[j].split(":")[3].replace(".","0"));
								countAlt=0;
								
								if((countRef + countAlt) > 0){
					    			 dosage = countRef / (countRef + countAlt);
				    			}
								
								if((countRef + countAlt) > 0){
					    			 dosage = countRef / (countRef + countAlt);
				    			}
								
							}

							System.out.print(dosage + "\t");
						}
					}

					System.out.println("");
				}

			}

		}

	}
	

}
