package arraylist;

import java.util.Arrays;

public class NewArrayList<E> {

    private static final int defaultSize = 10;
    private Object[] elements;
    private int size = 0;

    public NewArrayList() {
        this.elements = new Object[defaultSize];
    }

    public NewArrayList(int size) {
        this.elements = new Object[size];
    }


    public E get(int index){
        checkIndex(index);

        return (E) elements[index];
    }


    private void checkIndex(final int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
    }

    public void add(E element){
        if(size == elements.length){
            elements = Arrays.copyOf(elements, (int)(elements.length * 1.5));
        }

        elements[size++] = element;
    }

    public void remove(int index){
        checkIndex(index);

        removeProcess(index);
    }

    public void remove(E element){
        for(int i = 0; i < size; i++){
            if(elements[i].equals(element)){
                removeProcess(i);
            }
        }
    }

    private void removeProcess(final int index) {
        int newSize = size - 1;

        if(newSize > index){
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }

        elements[size = newSize] = null;
    }

    public boolean isEmpty(){
        return elements.length == 0;
    }

    public int size(){
        return size;
    }

    public Object[] getElements(){
        return elements;
    }
}
