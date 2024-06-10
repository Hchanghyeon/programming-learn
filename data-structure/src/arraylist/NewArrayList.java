package arraylist;

import java.util.Arrays;

/**
 * 직접 구현해본 간단한 ArrayList
 */
public class NewArrayList<E> {

    private static final int defaultSize = 10;
    private Object[] elements;
    private int size = 0;

    // 생성시 기본 10개 생성
    public NewArrayList() {
        this.elements = new Object[defaultSize];
    }

    // 인수 값 지정시 elements는 기본 설정 값으로 생성 됨
    public NewArrayList(int size) {
        this.elements = new Object[size];
    }

    // 인덱스 범위 계산 후 정상적이면 데이터 가져오기
    public E get(int index){
        checkIndex(index);

        return (E) elements[index];
    }

    // 인덱스 범위 계산
    private void checkIndex(final int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
    }

    // elements 배열의 개수 확인 후 최대 값이면 기존 값의 1.5배 곱한 값으로 새로 만들고 add
    public void add(E element){
        if(size == elements.length){
            elements = Arrays.copyOf(elements, (int)(elements.length * 1.5));
        }

        elements[size++] = element;
    }

    // index 값으로 삭제
    public void remove(int index){
        checkIndex(index);

        removeProcess(index);
    }

    // 요소 값으로 삭제(Null값 고려하지 않음)
    public void remove(E element){
        for(int i = 0; i < size; i++){
            if(elements[i].equals(element)){
                removeProcess(i);
            }
        }
    }

    // 실제 삭제 로직
    private void removeProcess(final int index) {
        int newSize = size - 1;

        // 만약 새로운 사이즈가 삭제하려는 index보다 큰 경우 -> 요약하면 마지막 index가 아닌 중간 index인 경우
        if(newSize > index){
            System.arraycopy(elements, index + 1, elements, index, newSize - index); // 중간에 삭제한 부분을 땡겨서 덮어 씌움
        }

        elements[size = newSize] = null; // 마지막 요소는 null 처리 -> GC 동작
    }

    // 현재 리스트가 비어있는지 체크
    public boolean isEmpty(){
        return elements.length == 0;
    }

    // 현재 리스트의 사이즈 체크
    public int size(){
        return size;
    }
}
