import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        //Minheap -> smallest value at the top
        if(data == null)
            throw new IllegalArgumentException("Can't add null data to the heap!");

        // if array is empty
        else if(size == 0) {
            backingArray[1] = data;
            size++;

        } else {
            // if array needs to be resized
            if (size + 1 == backingArray.length) {
                T[] resizeArray = (T[]) new Comparable[backingArray.length * 2];
                for (int i = 1; i <= size; i++)
                    resizeArray[i] = backingArray[i];
                backingArray = resizeArray;
            }
            // add data at size
            size ++;
            backingArray[size] = data;
            upHeap(backingArray);
        }
    }

    // Helper method
    private void upHeap(T[] backingArray) {
        /**
         * Start at index size/2
         * begin down heap -> data from parent is compared to the children
         * (Minheap, so smaller data must be at the top)
         * if child is smaller than parent, swap data of child and parent.
         * keep index/2 till you are at the index 1, then terminate.
         */

        int index = size;
        T placeHolder = backingArray[index];

        while(index > 1){
            if (placeHolder.compareTo(backingArray[index / 2]) < 0) {
                backingArray[index] = backingArray[index / 2];
                backingArray[index / 2] = placeHolder;
                placeHolder = backingArray[index/2];
                index = index/2;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        /**
         * Store data @ root
         * copy data in last index to the root
         * decrement size
         * begin down heaping (compare the larger child & swap if the child is smaller)
         * Left child = 2*n
         * Right child = 2*n + 1
         * Parent = n/2
         */

        if(size == 0)
            throw new NoSuchElementException("Can't remove data from an empty list!");
        else{
            T oldRoot = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size --;
            int n = 1;
            //downHeapIterate(backingArray);
            backingArray = downHeapRecursion(backingArray, n);
            return oldRoot;
        }
    }

    private T[] downHeapRecursion(T[] backingArray, int n){
        T pointerDown = backingArray[n];

        // base case
        if(2*n > size){
            return backingArray;
        }

        else{
            // if both children exist
            if(2*n <= size && 2*n + 1 <= size){

                // if left child is smaller
                if(backingArray[2*n].compareTo(backingArray[2*n + 1]) < 0){
                    System.out.println("N = "+ n);
                    System.out.println("left child, swap " + backingArray[n] + " for " + backingArray[2*n ] );
                    backingArray[n] = backingArray[2*n];
                    backingArray[2*n] = pointerDown;
                    n = 2*n;
                }
                // if right child is smaller
                else {
                    System.out.println("N = "+ n);
                    System.out.println("right child, swap " + backingArray[n] + " for " + backingArray[2*n + 1] );
                    backingArray[n] = backingArray[2*n + 1];
                    backingArray[2*n + 1] = pointerDown;
                    n = 2*n + 1;
                }
            }
            // if only left child exists
            else if(2*n <= size){
                backingArray[n] = backingArray[2*n];
                backingArray[2*n] = pointerDown;
                n = 2*n;
            }
        }

        return downHeapRecursion(backingArray,n);
    }




    private void downHeapIterate(T[] backingArray){
        int n = 1;
        T pointerDown = backingArray[1];

        while(n <= (size/2) ){

            // if both children exist
            if(2*n <= size && 2*n + 1 <= size){

                // if left child is smaller
                if(backingArray[2*n].compareTo(backingArray[2*n + 1]) < 0){
                    System.out.println("N = "+ n);
                    System.out.println("left child, swap " + backingArray[n] + " for " + backingArray[2*n ] );
                    backingArray[n] = backingArray[2*n];
                    backingArray[2*n] = pointerDown;
                    n = 2*n;
                }
                // if right child is smaller
                else {
                    System.out.println("N = "+ n);
                    System.out.println("right child, swap " + backingArray[n] + " for " + backingArray[2*n + 1] );
                    backingArray[n] = backingArray[2*n + 1];
                    backingArray[2*n + 1] = pointerDown;
                    n = 2*n + 1;
                }
            }
            // if only left child exists
            else if(2*n <= size){
                backingArray[n] = backingArray[2*n];
                backingArray[2*n] = pointerDown;
                n = 2*n;
            }
            else
                //System.out.println("no child is bigger, so terminate");
                break;
        }
    }



    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}