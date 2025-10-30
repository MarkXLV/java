package multithreading.problems;

public class Stack {
    private int[] array;
    private int index=-1;
    private int capacity;

    public Stack(int cap)
    {
        this.capacity=cap;
        array=new int[cap];
    }

    public synchronized boolean push(int val){
        if(isFull())
        {
            return false;
        }
        ++index;
        try {Thread.sleep(1000);}catch (Exception ignored){};
        array[index]=val;
        return true;
    }

    public synchronized int pop() {
        if(isEmpty())
        {
            return Integer.MIN_VALUE;
        }
        int k=array[index];
        array[index]=Integer.MIN_VALUE;
        try {Thread.sleep(1000);}catch (Exception ignored){};
        index--;
        return k;
    }

    public boolean isEmpty()
    {
        return index<0;
    }

    public boolean isFull()
    {
        return index==capacity-1;
    }

    public static void main(String[] args) {
        Stack st=new Stack(5);
        new Thread(()->{
            for (int i=0;i<10;i++)
            {
                    System.out.println(
                            "pushed : " + st.push(i)
                    );
            }
        }).start();


        new Thread(()->{
            for (int i=0;i<10;i++)
            {
                    System.out.println(
                           "popped : "+ st.pop()
                    );
            }
        }).start();

    }
}
