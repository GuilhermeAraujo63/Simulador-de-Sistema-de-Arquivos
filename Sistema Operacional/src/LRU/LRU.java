package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

abstract class AlgoritmoSubstituicao {
    protected int contagemFaltasPagina;
    protected int contagemQuadrosPagina;

    public AlgoritmoSubstituicao(int contagemQuadrosPagina) {
        if (contagemQuadrosPagina < 0)
            throw new IllegalArgumentException();

        this.contagemQuadrosPagina = contagemQuadrosPagina;
        contagemFaltasPagina = 0;
    }

    public int getContagemFaltasPagina() {
        return contagemFaltasPagina;
    }

    public abstract void inserir(int numeroPagina);
}

public class LRU extends AlgoritmoSubstituicao {
    private final LinkedHashMap<Integer, Integer> cache;

    public LRU(int contagemQuadrosPagina) {
        super(contagemQuadrosPagina);
        this.cache = new LinkedHashMap<>(contagemQuadrosPagina, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRU.this.contagemQuadrosPagina;
            }
        };
    }

    @Override
    public void inserir(int numeroPagina) {
        if (!cache.containsKey(numeroPagina)) {
            contagemFaltasPagina++;
        }
        cache.put(numeroPagina, numeroPagina);
    }

    public void exibirCache() {
        System.out.println("Estado atual do cache: " + cache.keySet());
    }

    public int simular(int[] paginas) {
        for (int pagina : paginas) {
            inserir(pagina);
        }
        return getContagemFaltasPagina();
    }
}