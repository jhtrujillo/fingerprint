package huellamolecular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ngsep.clustering.DistanceMatrix;
import ngsep.variants.CalledGenomicVariant;
import ngsep.variants.CalledSNV;
import ngsep.variants.GenomicVariant;
import ngsep.variants.Sample;
import ngsep.variants.VariantCallReport;
import ngsep.vcf.VCFDistanceMatrixCalculator;
import ngsep.vcf.VCFFileReader;
import ngsep.vcf.VCFRecord;

public class geneDosis {

	public VCFFileReader vcfFileReader;
	public Iterator<VCFRecord> iteratorRecords;
	public List<Sample> samples;
	public int genotypePerSamplesComparison[][];
	public int numSNPs = 0;
	Hashtable<String, String[]> hashtable = new Hashtable<String, String[]>();

	public void getDosis(String vcfFile) throws IOException {
		vcfFileReader = new VCFFileReader(vcfFile);
		iteratorRecords = vcfFileReader.iterator();

		samples = vcfFileReader.getHeader().getSamples();
		int genotypePerSamplesComparison[][] = new int[samples.size()][samples.size()];

		Iterator<VCFRecord> iteratorRecords_tmp = iteratorRecords;

		 System.out.print("Chr\tpos\t");
		for (int i = 0; i < vcfFileReader.getHeader().getSampleIds().size(); i++) {
			System.out.print(vcfFileReader.getHeader().getSampleIds().get(i)+"\t");
		}
		System.out.println();
		// System.out.println();

		while (iteratorRecords_tmp.hasNext()) {
			VCFRecord vcfRecord = iteratorRecords.next();
			GenomicVariant var = vcfRecord.getVariant();
			String[] alleles = var.getAlleles();
			List<CalledGenomicVariant> genotypeCalls = vcfRecord.getCalls();
			List<String> listado_individuos = vcfRecord.getHeader().getSampleIds();
			float numericGenotypes[] = new float[genotypeCalls.size()];

			

			String referencia = alleles[0];
			String alternativo = alleles[1];
			int posicion_snp = vcfRecord.getFirst();
			String snp_name = vcfRecord.getSequenceName();

			// System.out.print(snp_name+"_"+posicion_snp+"-Dossage_"+snp_name+"_"+posicion_snp+"\t");

			System.out.print(snp_name + "\t" + posicion_snp + "\t");

			for (int i = 0; i < genotypeCalls.size(); i++) {
				CalledGenomicVariant call = genotypeCalls.get(i);
				byte[] idxCalledAlleles = call.getIndexesCalledAlleles();
				VariantCallReport report = call.getCallReport();

				if (report == null)
					continue;
				float countRef = report.getCount(alleles[0]);
				float countAlt = report.getCount(alleles[1]);
				float dosage = 0;

				if ((countRef + countAlt) > 0) {
					dosage = countRef / (countRef + countAlt);
				}

				if (idxCalledAlleles.length == 0) {
					// System.out.print("./."+"-"+dosage+"\t");
					System.out.print(-1 + "\t");
				} else if (idxCalledAlleles.length == 1) {
					// System.out.print(alternativo+"/"+alternativo+"\t"+dosage+"\t");
					System.out.print(dosage + "\t");
				} else {
					// System.out.print(referencia+"/"+alternativo+"\t"+dosage+"\t");
					System.out.print(dosage + "\t");
				}

			}
			System.out.println();

			numSNPs++;
		}

		// System.out.println(numSNPs);
	}

	public static void main(String[] args) throws IOException {
		geneDosis dosiscgene = new geneDosis();

		//String vcfFile = "/home/estuvar4/Documents/huella_molecular/huellaRanqueada_1erenvio.vcf";
		//dosiscgene.loadVCF(vcfFile);

		 String vcfFile = args[0];
		 dosiscgene.getDosis(vcfFile);

	}

}
