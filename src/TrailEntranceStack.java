import java.util.Arrays;

/**
 * TrailEntranceStack.java
 *
 * Created by Calvin Wong on 10/11/2014
 *
 * A stack is created to add hikers
 */

public class TrailEntranceStack {

    private String[] stack;
    private int size;
    private int top;

    public TrailEntranceStack(int size) {
        this.stack = new String[size];
        this.top = -1;
        this.size = size;
    }

    public void push(String obj) {
        if (top < size) {
            stack[++top] = obj;
        }
    }

    public String pop() {
        if (top < 0) {
            throw new IndexOutOfBoundsException();
        }
        String obj = stack[top--];
        stack[top + 1] = null;
        return obj;
    }

    public int size() {
        return size;
    }

    public int elements() {
        return top + 1;
    }

    public int freeSpace() {
        return (size - top) - 1;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "TrailEntranceStack{" +
                "stack=" + Arrays.toString(stack) +
                ", size=" + size +
                ", top=" + top +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrailEntranceStack that = (TrailEntranceStack) o;

        if (size != that.size) return false;
        if (top != that.top) return false;
        if (!Arrays.equals(stack, that.stack)) return false;

        return true;
    }
} // end of class
