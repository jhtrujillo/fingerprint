package huellamolecular;

import java.io.IOException;

//Clase que filtrar los snps con base al archivo de las dosis alelicas. 

public class filtrarporDosisalelicas {

	public boolean continDosis(double dosismin, double dosismax, String[] dosisSnp) {
		boolean resultado = false;
		for (int i = 1; i < dosisSnp.length; i++) {
			if (Double.parseDouble(dosisSnp[i]) >= dosismin && Double.parseDouble(dosisSnp[i]) <= dosismax) {
				resultado = true;
				i = dosisSnp.length;
			}
		}

		return resultado;
	}

	public void loadDosis(String pathfile) {

		archivos ar = new archivos();
		String[] datos = ar.leerfichero2(pathfile);
		int cont = 0;
		int IndexSnp=datos[0].split("	")[0].split("_").length-1;
		String snp="";
		 
		for (int i = 0; i < ar.numerolineas; i++) {
			String[] dosisSnp = datos[i].split("	");
			 //if (continDosis(0.0, 0.33, dosisSnp) && continDosis(0.34, 0.66, dosisSnp) &&
					//continDosis(0.67, 1.0, dosisSnp) )
			 if (continDosis(0.0, 0.2, dosisSnp) && continDosis(0.21, 0.4, dosisSnp) &&
					 continDosis(0.41, 0.6, dosisSnp) && continDosis(0.61, 0.8, dosisSnp) && continDosis(0.81, 1.0, dosisSnp))
			//if ( continDosis(0.0, 0.1, dosisSnp) && continDosis(0.11, 0.2, dosisSnp) 
				 //	 && continDosis(0.21, 0.3, dosisSnp) && continDosis(0.31, 0.4, dosisSnp) 
				 //&& continDosis(0.41, 0.5, dosisSnp) && continDosis(0.51, 0.6, dosisSnp)
				 //&& continDosis(0.61, 0.7, dosisSnp) && continDosis(0.71, 0.8, dosisSnp) 
				 //&& continDosis(0.81, 0.9, dosisSnp) && continDosis(0.91, 1.0, dosisSnp) 
				 //)	
			{
				 if (IndexSnp==1) {
					 snp=datos[i].split("	")[0].split("_")[0]	;
				 }
				 else if (IndexSnp==2) {
					 snp=datos[i].split("	")[0].split("_")[0] + "_" + datos[i].split("	")[0].split("_")[1]	;
				 }
				 
				 String dosis= datos[i].split("	")[0].split("_")[IndexSnp];
				 
				 System.out.println(snp+ "\t"+dosis );
				cont++;
			} 
		} 
		 //System.out.println(cont);
	}
	
	/*
	public static void main(String[] args) throws IOException {
		filtrarporDosisalelicas ld = new filtrarporDosisalelicas();
		// System.out.println("Rango de 4 grupos...");
		ld.loadDosis(args[0]);
		//ld.loadDosis("/home/estuvar4/eclipse-workspace/huellamolecular/files2/dosis.txt");
	}*/

}
