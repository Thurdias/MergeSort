import java.util.ArrayList;

public class Bucket extends Thread {
	// Propriedades da classe
	private int numeroDoBucket = 0;
	private int indiceInicial = 0;
	private int indiceFinal = 0;
	
	// Método construtor cheio da classe
	public Bucket(int numeroDoBucket, int indiceInicial, int indiceFinal) {
		super();
		this.numeroDoBucket = numeroDoBucket;
		this.indiceInicial = indiceInicial;
		this.indiceFinal = indiceFinal;
	}
	
	// Método de execução paralela da classe
	public void run() {
		ArrayList<Integer> bucket = new ArrayList<Integer>();
		
		for (int i = indiceInicial ; i <= indiceFinal ; i++) {
			bucket.add(MemoriaCompartilhada.getValor(i));
		}
		
		for (int i = 0 ; i < (bucket.size() - 1) ; i++) {
			for (int j = (i + 1) ; j < bucket.size() ; j++) {
				if (bucket.get(i) > bucket.get(j)) {
					int temp = bucket.get(i);
					bucket.set(i, bucket.get(j));
					bucket.set(j, temp);
				}
			}
		}
		
		MemoriaCompartilhada.atribuirBucket(numeroDoBucket, bucket);
		MemoriaCompartilhada.concluir(numeroDoBucket);
	}
}