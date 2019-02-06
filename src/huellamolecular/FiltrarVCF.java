package huellamolecular;

import java.util.Hashtable;

public class FiltrarVCF {

	public Hashtable<String, String> vcfHash = new Hashtable<String, String>();

	
	
	public void filtrarvcf(String path_dosis, String vcf) {
		archivos ar = new archivos();
		String[] datos = ar.leerfichero2(path_dosis);

		cargarvcf(vcf);
		
		for (int i = 0; i < 19; i++) {
			System.out.println(this.vcfHash.get(Integer.toString(i)));
		}
		
		for (int i = 0; i < ar.numerolineas; i++) {
			String cromosoma = datos[i].split("\t")[0];
			String snp = datos[i].split("\t")[1];
			System.out.println(this.vcfHash.get(snp+"_"+cromosoma));
		}
		
	}
	

	public void cargarvcf(String vcf) {

		vcfHash = new Hashtable<String, String>();


		archivos ar2 = new archivos();
		String[] datosvcf = ar2.leerfichero2(vcf);

		for (int i = 0; i < 19; i++) {
			//System.out.println(datosvcf[i]);
			this.vcfHash.put(Integer.toString(i), datosvcf[i]);
		}
		
		for (int i = 19; i < ar2.numerolineas; i++) {
			String cromosoma = datosvcf[i].split("	")[1];
			String snp = datosvcf[i].split("	")[0];
			this.vcfHash.put(snp+"_"+cromosoma, datosvcf[i]);
		}
		
		
		
	}
	/*
	public static void main(String[] args) {
		FiltrarVCF al = new FiltrarVCF();
		al.filtrarvcf(args[0], args[1]);
		
		
		al.filtrarvcf(
			"/bioinfo5/tesis/metodologia/huella_molecular/all_radseq_gbs/vcf/snps_australianos.txt",
				"/bioinfo5/tesis/metodologia/huella_molecular/all_radseq_gbs/vcf/AllSamples_genotypes_biallelics_minmaf_0.20_minI_185_minC_50_q_40_d_5.vcf");
				
	}*/

}