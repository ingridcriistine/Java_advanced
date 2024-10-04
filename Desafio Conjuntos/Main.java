public class Main {
    public static void main(String[] args) {
        
        Set2 set = new Set2();
        set.addItem(90);
        set.addItem(45);
        set.addItem(36);
        set.addItem(89);

        Set2 set2 = new Set2();
        set2.addItem(90);
        set2.addItem(45);
        set2.addItem(36);
        set2.addItem(89);
        set.addItem(set2);

        Set2 set3 = new Set2();
        set3.addItem(90);
        set3.addItem(45);
        set3.addItem(89);
        set3.addItem(35);


        for (Object i : set.uniao(set3).getSet()){
            System.out.println(i);
        }


        // System.out.println(set.contem(set2));
        System.out.println(set.pertence(set3));
        // System.out.println(set3);
        // System.out.println(set2);
    }
}