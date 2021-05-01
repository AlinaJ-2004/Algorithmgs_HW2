public class HeapM<T extends Comparable<T>> {

    public Object S[];
    public int last;
    public int capacity;

    public HeapM(int cap) {
        S = new Object[cap + 1];
        last = 0;
        capacity = cap;
    }

    public int size() {
        return last;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int compare(Object x, Object y) {
        return ((T) x).compareTo((T) y);
    }
    public T min() {
        return (T) S[1];
    }

    public void insert(T t) {
        if (size() == capacity)
            System.out.println("Heap overflow.");
        else{
            last++;
            S[last] = t;
            upHeapBubble();
        }
    }

    public T extractMin() {
        T min = min();
        if (isEmpty())
            System.out.println("Heap is empty.");
        else {
            S[1] = S[last];
            last--;
            downHeapBubble();
            return min;
        }
        return min;
    }


    private void downHeapBubble(){
        int index = 1;
        while (true){
            int child = index*2;
            if (child > size())
                break;
            if (child + 1 <= size()){
                child = findMin(child, child + 1);
            }
            if (compare(S[index],S[child]) <= 0 )
                break;
            swap(index,child);
            index = child;
        }
    }

    private void upHeapBubble(){
        int index = size();
        while (index > 1){
            int parent = index / 2;
            if (compare(S[index], S[parent]) >= 0)
                break;
            swap(index,parent);
            index = parent;
        }
    }

    private void swap(int i, int j) {
        Object temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    private int findMin(int leftChild, int rightChild) {
        if (compare(S[leftChild], S[rightChild]) <= 0)
            return leftChild;
        else
            return rightChild;
    }

    private T getMin() {
        T min = min();
        return min;
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= size(); i++) {
            s += S[i];
            if (i != last)
                s += " ";
        }
        return s + " ";
    }


    public static void main(String[] args) {
        HeapM tree = new HeapM(100);
        System.out.println("Is the heap empty? : " + tree.isEmpty());
        tree.insert(6);
        System.out.println("Is the heap empty? : " + tree.isEmpty());
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        System.out.println("The heap: " +  tree.toString());
        System.out.println("Size of the heap is " + tree.size());
        System.out.println("The minimum element that gets removed is " + tree.extractMin());
        System.out.println("The heap: " + tree.toString());
        System.out.println("The minimum element of the heap: " + tree.getMin());
    }
}

