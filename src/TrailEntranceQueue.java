/**
 * TrailEntranceQueue.java
 *
 * Created by Calvin Wong on 10/11/2014
 *
 * Adds Stack to Queue
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TrailEntranceQueue {

    private final int STACK_SIZE = 10;
    private String[] Hikers;
    private int NumOfHikersToAdd;
    private TrailEntranceStack stack;
    private Queue<TrailEntranceStack> queue;
    private String entranceName;

    public TrailEntranceQueue(String name) {

        entranceName = name;

        // create a queue and initialize it
        queue = new LinkedList<TrailEntranceStack>();
        setupStack();
        stack = new TrailEntranceStack(10);
    }

    public void setupStack() {
        // stack to add hikers
        queue.add(stack);
        stack = new TrailEntranceStack(10);

    }

    @Override
    public String toString() {
        return entranceName;
    }

    public void setHikers(String[] HikersList) {
        // get hikers list in string array
        this.Hikers = new String[HikersList.length];
        this.Hikers = HikersList;

        //  keeps track number of hikers to add
        NumOfHikersToAdd = HikersList.length;

    }

    // method to add hikers to stack and then queue; calls recursively till all hikers has been added to stack
    public void addHikers() {

        // verify stack has space to add new hiker
        int stackSpace = stack.freeSpace();

        if (NumOfHikersToAdd <= stackSpace) {
            addHikersToStack(Hikers.length - NumOfHikersToAdd, NumOfHikersToAdd);

        }
        else {

            addHikersToStack(Hikers.length - NumOfHikersToAdd, STACK_SIZE);
            setupStack();
            addHikers();
          }
    }

    // method for adding hikers from the list to current stack
    public void addHikersToStack(int from_hiker_pos, int end_pos) {

        for (int i = 0; i < end_pos; i++) {
            stack.push(Hikers[from_hiker_pos + i]);

            NumOfHikersToAdd--;
        }
     }

    // method to add the last stack to queue
    public void addLast() {
        queue.add(stack);
    }
    // retrieve each queue elements
    public String getQueueElement_full() {
        String res = "";

        Iterator queueElements = queue.iterator();

        while (queueElements.hasNext()) {
            // Get a stack from queue
            TrailEntranceStack temp = (TrailEntranceStack) queueElements.next();
            // call function to extract hikers from Stack
            res = res.concat(printStack(temp));

        }
        return res;
    }

    // retrieve each queue elements i.e Stack this function for finding the non filled stack
    public String getQueueElement_notFull() {

        Iterator queueElements = queue.iterator();

        String res = "";

        while (queueElements.hasNext()) {

            TrailEntranceStack temp = (TrailEntranceStack) queueElements.next();

            res = res.concat(getQueueEmptyStack(temp));
        }
        return res;
    }

    // method to retrieve hikers from stack
    public String printStack(TrailEntranceStack HikerList) {

        String temp_result = "";

        try {
            // checks stack to see if filled or not filled
            if (HikerList.freeSpace() == 0) {
                while (!HikerList.isEmpty()) {
                    // retrieve list of hikers from Stack
                    String temp = HikerList.pop().toString();
                    temp_result = temp_result.concat(temp) + "\n";
   
                }
            }
        } catch (Exception e) {

        }
        return temp_result;
    }

    // method for getting hikers list from non filled stack
    public String getQueueEmptyStack(TrailEntranceStack HikerList) {

        String temp_result = "";

        try {

            if (HikerList.freeSpace() != 0) {
                while (!HikerList.isEmpty()) {

                    String temp = HikerList.pop().toString();
                    temp_result = temp_result.concat(temp) + "\n";
              }
            }
        } catch (Exception e) {
            System.out.println("Exception ::::empty stack:::" + e);
        }

        return temp_result;
    }

    public int getSTACK_SIZE() {
        return STACK_SIZE;
    }

    public String[] getHikers() {
        return Hikers;
    }

    public int getNumOfHikersToAdd() {
        return NumOfHikersToAdd;
    }

    public void setNumOfHikersToAdd(int numOfHikersToAdd) {
        NumOfHikersToAdd = numOfHikersToAdd;
    }

    public TrailEntranceStack getStack() {
        return stack;
    }

    public void setStack(TrailEntranceStack stack) {
        this.stack = stack;
    }

    public Queue<TrailEntranceStack> getQueue() {
        return queue;
    }

    public void setQueue(Queue<TrailEntranceStack> queue) {
        this.queue = queue;
    }

    public String getEntranceName() {
        return entranceName;
    }

    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrailEntranceQueue that = (TrailEntranceQueue) o;

        if (NumOfHikersToAdd != that.NumOfHikersToAdd) return false;
        if (STACK_SIZE != that.STACK_SIZE) return false;
        if (!Arrays.equals(Hikers, that.Hikers)) return false;
        if (entranceName != null ? !entranceName.equals(that.entranceName) : that.entranceName != null) return false;
        if (queue != null ? !queue.equals(that.queue) : that.queue != null) return false;
        if (stack != null ? !stack.equals(that.stack) : that.stack != null) return false;

        return true;
    }
} // end of class
