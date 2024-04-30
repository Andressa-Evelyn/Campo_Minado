package CampoMinado.modelo;

import java.util.ArrayList;

import CampoMinado.explosaoException;

public class Campo {
    private boolean minado = false;
    private boolean aberto =  false;
    private boolean marcado = false;

    private int linha;
    private int coluna;

    private ArrayList<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna){          //Construtor
        this.linha = linha; 
        this.coluna = coluna;
    }

    //Se estiver na mesma linha/coluna e a distância for 1 -> vizinho
    //Se tiver na diagonal e a distância for 2 -> vizinho

    public boolean adicionarVizinhos(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente =coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if(deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if(deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    //Marcar e desmarcar antes de abrir

    public void alternarMarcaçao(){
        if(aberto == false){
            marcado = !marcado;
        }
    }

    //Lógica para abrir

    public boolean abrir(){
        if(!aberto && !marcado){
            aberto = true;

            if(minado){
                throw new explosaoException();
            }

            if(vizinhançaSegura()){
                vizinhos.forEach(v -> v.abrir());        //estrutura do forEach(v -> v.exemplo())
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean vizinhançaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado); 
    }

    public boolean isMarcado(){
        return marcado;
    }

    public void minar(){
        if(!minado){
         minado = true;
        }
    }
    
    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public ArrayList<Campo> getVizinhos() {
        return vizinhos;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean objetivoAlcançado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public long minasVizinhança(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    public void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "X";
        } else if(aberto && minado){
            return "*";
        } else if(aberto && minasVizinhança() > 0){
            return Long.toString(minasVizinhança());
        } else if(aberto){
            return " ";
        } else {
            return "?";
        }
    }
}
