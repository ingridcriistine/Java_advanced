import java.util.ArrayList;

public class teste {
    
    public ArrayList<Integer> set;
    
    public teste (ArrayList<Integer> set) {
        this.set = set;
    }

    public teste () {
        this.set = new ArrayList<>();
    }

    public ArrayList<Integer> getSet() {
        return set;
    }
    
    public void addItem(Integer item) {
        this.set.add(item);
    }

    public teste uniao(teste setin) {

        ArrayList<Integer> uniao = new ArrayList<>();
        uniao = this.set;

        for (int i = 0; i < setin.getSet().size(); i++) {
            if(!this.set.contains(setin.getSet().get(i))) {
                uniao.add(setin.getSet().get(i));
            }
        }

        return new teste(uniao);
    }

    public teste intersecao(teste setin) {

        ArrayList<Integer> intersecao = new ArrayList<>();

        for (int i = 0; i < setin.getSet().size(); i++) {
            if(this.set.contains(setin.getSet().get(i))) {
                intersecao.add(setin.getSet().get(i));
            }
        }

        return new teste(intersecao);
    }

    public boolean contido(teste setin) {

        for (int i = 0; i < setin.getSet().size(); i++) {
            if(!this.set.contains(setin.getSet().get(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean pertence(Integer valor) {
        return this.set.contains(valor);
    }

}
