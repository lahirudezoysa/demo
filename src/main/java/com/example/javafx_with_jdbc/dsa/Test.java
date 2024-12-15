package com.example.javafx_with_jdbc.dsa;

public class Test {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);

        stack.pirntStack();
        System.out.println("---------------------------");

        Queue queue = new Queue();
        queue.push(50);
        queue.push(70);
        queue.push(80);

        queue.printQueue();
        System.out.println("---------------------------");
        queue.pop();
    }
}
