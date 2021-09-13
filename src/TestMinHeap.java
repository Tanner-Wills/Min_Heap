import java.util.Arrays;

public class TestMinHeap {
    public static void main(String[] args) {

        MinHeap<Integer> heapy = new MinHeap<>();
        heapy.add(0);
        heapy.add(1);
        heapy.add(2);

        heapy.add(4);
        heapy.add(3);


        heapy.add(5);
        heapy.add(7);

        heapy.add(6);
        heapy.add(8);


        heapy.add(9);
        heapy.add(10);

        heapy.add(14);
        System.out.println(heapy.size());
        heapy.add(13);


        heapy.add(15);




        System.out.println(Arrays.toString(heapy.getBackingArray()));


        //System.out.println(heapy.getBackingArray().length);

        //heapy.remove();
        //System.out.println(Arrays.toString(heapy.getBackingArray()));

    }
}
