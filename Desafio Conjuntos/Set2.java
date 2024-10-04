import java.util.ArrayList;

public class Set2 {

    ArrayList<Object> set;

    public Set2() {
        this.set = new ArrayList<>();
    }

    public Set2(ArrayList<Object> setIn) {
        this.set = (ArrayList<Object>) setIn.clone();
    }
    
    public ArrayList<Object> getSet() {
        return set;
    }

    public void setSet(ArrayList<Object> set) {
        this.set = (ArrayList<Object>) set.clone();
    }

    public void addItem(Object item) {
        this.set.add(item);
    }

    public Set2 uniao(Set2 setIn) {

        ArrayList<Object> uniao = (ArrayList<Object>) this.set.clone();;

        for (Object item : setIn.getSet()) {
            if(!this.pertence(item)){
                uniao.add(item);
            }
        }

        return new Set2(uniao);
    }

    public Set2 intersecao(Set2 setIn) {

        ArrayList<Object> intersecao = new ArrayList<>();

        for (Object item : setIn.getSet()) {
            if(this.pertence(item)) {
                intersecao.add(item);
            }
        }

        return new Set2(intersecao);
    }

    public boolean contem(Set2 setIn) {

        for (Object item : setIn.getSet()) {
            if(!this.pertence(item)) {
                return false;
            }
        }

        return true;
    }

    public boolean pertence(Object valor) {
        if (this.set.contains(valor)){
            return true;
        } 

        if(!valor.getClass().getName().equals("Set2")){
            return false;
        }

        System.out.println();
        Set2 setIn = (Set2)valor;
        
        for (Object item : this.set) {
            if(item.getClass().getName().equals("Set2")){

                Set2 subSet = (Set2)item;

                for (Object subItem : subSet.getSet()) {
                    if (!setIn.pertence(subItem)){
                        return false;
                    }
                }
            };
        }

        System.out.println();

        return true;
    }
}
