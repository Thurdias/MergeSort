
public class Principal {
	public static void main(String[] args) {
		// Inicialização do vetor
		new MemoriaCompartilhada();
		//MemoriaCompartilhada.imprimirVetor();
		
		// Quebra do vetor em Buckets
		for (int i = 0 ; i < MemoriaCompartilhada.QTD_BUCKETS ; i++) {
			int qtdElementosPorBucket =
					(MemoriaCompartilhada.QTD_ELEMENTOS /
					 MemoriaCompartilhada.QTD_BUCKETS); 
			
			int indiceInicial = (i * qtdElementosPorBucket);
			
			int indiceFinal = indiceInicial + (qtdElementosPorBucket - 1);
			
			new Bucket(i, indiceInicial, indiceFinal).start();
		}

		// Espera todos os buckets concluírem sua ordenação
		while (! MemoriaCompartilhada.bucketsConcluiram()) {
			try {Thread.sleep(1000);} catch (Exception e) {}
		}
		
		// Reunião dos Buckets no vetor
		for (int i = 0 ; i < MemoriaCompartilhada.QTD_ELEMENTOS ; i++) {
			int menor = MemoriaCompartilhada.QTD_ELEMENTOS;
			int numeroDoBucketMenor = 0;
			
			for (int j = 0 ; j < MemoriaCompartilhada.QTD_BUCKETS ; j++) {
				if (menor > MemoriaCompartilhada.getBucket(j)) {
					menor = MemoriaCompartilhada.getBucket(j);
					numeroDoBucketMenor = j;
				}
			}
			MemoriaCompartilhada.consumirBucket(numeroDoBucketMenor);
			MemoriaCompartilhada.setValor(i, menor);
		}
		
		//MemoriaCompartilhada.imprimirVetor();
		System.out.println("!!!! TERMINEI !!!!");
	}
}