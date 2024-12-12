package Aging;

import java.util.*;

public class AgingPageReplacement {

    static class Page {
        int numero;
        int idade; 
        boolean bitReferencia;

        public Page(int numero) {
            this.numero = numero;
            this.idade = 0;
            this.bitReferencia = false;
        }
    }

    public int aging(int[] pages, int frames) {
        List<Page> cache = new ArrayList<>();
        int pageFaults = 0;

        for (int page : pages) {
            
            boolean found = false;
            for (Page p : cache) {
                if (p.numero == page) {
                    p.bitReferencia = true;
                    found = true;
                    break;
                }
            }

            if (!found) {
                pageFaults++;

                if (cache.size() >= frames) {
                    Page pageToRemove = cache.get(0);
                    for (Page p : cache) {
                        if (p.idade < pageToRemove.idade) {
                            pageToRemove = p;
                        }
                    }
                    cache.remove(pageToRemove);
                }

                cache.add(new Page(page));
            }

            for (Page p : cache) {
                p.idade = (p.idade >> 1); 
                if (p.bitReferencia) {
                    p.idade = p.idade | 0x80; 
                    p.bitReferencia = false;
                }
            }
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        AgingPageReplacement simulator = new AgingPageReplacement();
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int frames = 3;

        System.out.println("Faltas de pagina (Aging): " + simulator.aging(pages, frames));
    }
}
