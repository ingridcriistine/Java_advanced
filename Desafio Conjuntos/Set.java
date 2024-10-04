import java.util.ArrayList;

public class Set<T> {
    
    private ArrayList<T> set;

    public Set(ArrayList<T> newSet) {
        this.set = (ArrayList<T>) newSet.clone();
    }

    public Set() {
        this.set = new ArrayList<>();
    }

    public void addItem(T value) {
        set.add(value);
    }

    public ArrayList<T> getSet() {
        return this.set;
    }

    public Boolean belongs(T value) {
        
        return this.set.contains(value);
    }

    public Set<T> union(Set<T> set) {

        ArrayList<T> union = (ArrayList<T>) set.getSet().clone();

        for(int i = 0; i < this.set.size(); i++) {
            if(!union.contains(this.set.get(i))) {
                union.add(this.set.get(i));
            }
        }
 
        return new Set<T>(union);
    }

    public Set<T> intersection(Set<T> set) {
        
        ArrayList<T> intersection = new ArrayList<>();

        for(int i = 0; i < this.set.size(); i++) {
            if(set.getSet().contains(this.set.get(i))) {
                intersection.add(this.set.get(i));
            }
        }
        
        return new Set<T>(intersection);
    }

    public Boolean contain(Set<T> set2) {

        for(int i = 0; i < this.set.size(); i++) {
            if(!set2.getSet().contains(this.set.get(i))) {
                return false;
            }
        }

        return true;
    }
}
