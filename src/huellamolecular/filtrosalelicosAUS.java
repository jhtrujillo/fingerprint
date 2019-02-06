package huellamolecular;

public class filtrosalelicosAUS {
	
	public void filtrarporClases(String vcfpath) {
		archivos ar = new archivos();
		String[] datos = ar.leerfichero2(vcfpath);
		int cont=0;
		for (int i = 0; i < ar.numerolineas; i++) {
			int clase = tipodeclase(datos[i]) ;
			//System.out.println(datos[i]);
			if (clase > 0 ){
				System.out.println( datos[i].split("\t")[0]+"\t"+datos[i].split("\t")[1]+"\t"+clase);
				cont++;
			}
			
		}
		//System.out.println(cont);
	}

	public int tipodeclase(String snp) {
		int resultado = 0;
		int lowdose = 0;
		int nodose = 0;
		int highdose = 0;
		int middledose = 0;
		
		String[] datos = snp.split("\t");
		for (int i = 4; i < datos.length; i++) {

			if (datos[i].compareTo("9:1") == 0 || datos[i].compareTo("8:2") == 0 || datos[i].compareTo("1:9") == 0 || datos[i].compareTo("2:8") == 0) {
				lowdose++;
			}
			else if (datos[i].compareTo("0:1") == 0 || datos[i].compareTo("1:0") == 0) {
				nodose++;
			}
			else if (datos[i].compareTo("1:1") == 0) {
				highdose++;
			}
			else if (datos[i].compareTo("7:3") == 0 || datos[i].compareTo("6:4") == 0 || datos[i].compareTo("4:6") == 0|| datos[i].compareTo("3:7") == 0) {
				middledose++;
			}
			
			if (lowdose >= 66 && nodose >= 66 && highdose >= 16){
				resultado = 1;
			}
			else if (lowdose >= 66 && nodose >= 66 && highdose <= 0) {
				resultado = 2;
			}
			else if (lowdose >= 33 && nodose >= 33 && highdose <= 0 && middledose <= 0  ) {
				resultado = 3;
			}else{
				resultado = 0;
			}
			
		}

		return resultado;
	}

	public static void main(String[] args) {
		filtrosalelicosAUS fa = new filtrosalelicosAUS();
		fa.filtrarporClases(args[0]);
		
		//fa.filtrarporClases(
		//"/home/estuvar4/local_biodata/biodata/projects/tesis/metodologia/huella_molecular/gbs/vcf/263_GBS_Good_Samples_no_adapter_genotypes_biallelicos_no_failed_trimmomatic_alelos.txt");
	}
}