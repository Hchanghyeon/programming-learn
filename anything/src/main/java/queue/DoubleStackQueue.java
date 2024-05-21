package queue;

import java.util.Stack;

/**
 * 첫번째 스택은 받는 용도로만 사용
 * 두번째 스택은 빼는 용도로만 사용
 *
 * 만약, 두번째 스택에 데이터가 없는 경우 첫번째 스택에서 모두 가져와서 전달해야함
 * 두번째 스택에 데이터가 있는 상태에서 첫번째 스택에 있는 데이터를 받아올 경우 순서가 바뀜
 * 또한 첫번째 스택에서 전부 다 가져오지 않을 경우 순서가 또 꼬일 수 있음
 */


public class DoubleStackQueue {

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.add(1);
        queue.add(2);
        System.out.println(queue.poll());
    }

    private static class Queue {
        Stack inStack;
        Stack outStack;

        Queue() {
            this.inStack = new Stack();
            this.outStack= new Stack();
        }

        private void add(int num) {
            inStack.add(num);
        }

        private int poll() {
            if(outStack.isEmpty()) {
                while(!inStack.isEmpty()){
                    outStack.add(inStack.pop());
                }
            }
            return (int)outStack.pop();
        }
    }
}
