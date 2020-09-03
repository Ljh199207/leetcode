package com.ljh.data.array;


import java.util.Scanner;

/**
 * @author ljh
 * @date 2020-08-18 09:15
 * 数组实现队列实现
 * 循环队列
 */
public class CycleArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列的头数据");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入想添加的数字");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    //使用try catch 处理异常语句来取数据
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.showHead();
                        System.out.printf("队列的头数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }


    static class ArrayQueue {

        private int maxSize;//表示数组的最大容量
        private int front;//队列头
        private int rear;//队列尾
        private int[] arr;//该数据用于存放数据模拟队列

        public ArrayQueue(int size) {
            maxSize = size;
            arr = new int[maxSize];
        }

        //判断队列是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断队列是否满
        public boolean isEmpty() {
            return front == rear;
        }

        //添加数据到队列
        public void add(int a) {
            //判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据");
                return;
            }
            arr[rear] = a;
            rear = (rear + 1) % maxSize;
        }

        //获取队列数据，出队列
        public int getQueue() {
            //判断队列是否空
            if (isEmpty()) {
                //通过判处异常来处理
                throw new RuntimeException("队列空，不能取数据");
            }
            //这里需要分析出front是指向队列的第一个元素
            //1. 先把front 对应的值保留到一个临时变量
            //2. 将front 后移，考虑取模
            //3. 将临时保留的变量返回
            int temp = arr[front];
            front = (front + 1) % maxSize;
            return temp;
        }

        //显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空，没有数据");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        ////求出当前队列有效数据的个数
        public int size() {
            return (rear + maxSize - front) % maxSize;
        }


        //显示队列的头元素
        public int showHead() {
            if (isEmpty()) {
                throw new RuntimeException("队列空，没有数据");
            }
            return arr[front];

        }


    }


}
