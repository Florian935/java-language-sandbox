package model.utils;

import java.io.Serializable;

public class RVB implements Serializable {

    private static final long serialVersionUID = 123456L;
    private Double rouge;
    private Double vert;
    private Double bleu;

    public Double getRouge() {
        return rouge;
    }

    public void setRouge(Double rouge) {
        this.rouge = (rouge <= 1 || rouge >= 0) ? rouge : null;
    }

    public Double getVert() {
        return vert;
    }

    public void setVert(Double vert) {
        this.vert = (vert <= 1 || vert >= 0) ? vert : null;
    }

    public Double getBleu() {
        return bleu;
    }

    public void setBleu(Double bleu) {
        this.bleu = (bleu <= 1 || bleu >= 0) ? bleu : null;

    }
}
