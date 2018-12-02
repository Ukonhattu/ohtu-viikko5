
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujoukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti < 0");
        }
        lukujoukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti tai kasvatuskoko < 0");
        }

        lukujoukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujoukko.length) {
                kasvata();
            }
            return true;
        }
        return false;
    }

    private void kasvata() {
        int[] taulukkoOld = new int [lukujoukko.length];
        kopioiTaulukko(lukujoukko, taulukkoOld);
        lukujoukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukujoukko);
    }

    public boolean kuuluu(int luku) {
        boolean on = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                on = true;
            }
        }
        return on;
    }

    public boolean poista(int luku) {
        int apu, kohta = etsi(luku);
        if (kohta > -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = lukujoukko[j];
                lukujoukko[j] = lukujoukko[j + 1];
                lukujoukko[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private int etsi(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                lukujoukko[kohta] = 0;
                break;
            }
        }
        return kohta;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            default:
                return generoiMerkkijono();
        }
    }

    private String generoiMerkkijono() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujoukko[i];
            tuotos += ", ";
        }
        tuotos += lukujoukko[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujoukko, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }
        
}