import java.util.ArrayList;

public class MemoriaCompartilhada {
	// Constantes da classe
	public static int QTD_ELEMENTOS = 10000000;
	public static int QTD_BUCKETS = 10000;
	
	// Propriedades da classe
	private static int[] vetor = new int[QTD_ELEMENTOS];
	private static ArrayList<ArrayList<Integer>> vetorDeBuckets =
				new ArrayList<ArrayList<Integer>>();
	private static boolean[] vetorConclusao = new boolean[QTD_BUCKETS];
	
	// Método construtor da classe
	public MemoriaCompartilhada() {
		// Inicialização do vetor
		for (int i = 0 ; i < vetor.length ; i++) {
			vetor[i] = ((int) (vetor.length * Math.random()));
		}
		
		// Inicialização dos buckets
		for (int i = 0 ; i < QTD_BUCKETS ; i++) {
			vetorDeBuckets.add(null);
		}
	}
	
	// Métodos da classe
	public static synchronized void setValor(int indice, int valor) {
		vetor[indice] = valor;
	}
	
	public static synchronized int getValor(int indice) {
		return vetor[indice];
	}
	
	public static synchronized void atribuirBucket(int numero, ArrayList<Integer> bucket) {
		vetorDeBuckets.set(numero, bucket);
	}
	
	public static synchronized int getBucket(int numero) {
		int retorno = QTD_ELEMENTOS;
		
		if (vetorDeBuckets.get(numero).size() > 0) {
			retorno = vetorDeBuckets.get(numero).get(0); 
		}
		
		return retorno;
	}
	
	public static synchronized void consumirBucket(int numero) {
		vetorDeBuckets.get(numero).remove(0);
	}
	
	public static synchronized void imprimirVetor() {
		for (int i = 0 ; i < vetor.length ; i++) {
			System.out.print(vetor[i] + "\t");
		}
		System.out.println();
	}
	
	public static synchronized void concluir(int numero) {
		vetorConclusao[numero] = true;
	}
	
	public static synchronized boolean bucketsConcluiram() {
		boolean retorno = true;
		
		for (int i = 0 ; i < QTD_BUCKETS ; i++) {
			if (! vetorConclusao[i]) {
				retorno = false;
			}
		}
		
		return retorno;
	}
}