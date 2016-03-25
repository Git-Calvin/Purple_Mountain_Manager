/**
 * Controller.java
 *
 * Created by Calvin Wong on 10/11/2014
 *
 * Controls GUI for Purple Mountain Manager
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;


public class Controller extends JFrame implements ActionListener {

    static boolean flag = false;
    static int count = 1;

    private JButton checkTrailButton;
    private JLabel imageLabel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;
    private JButton openTrailButton;
    private JButton startButton;

    static List HikersList = new ArrayList();

    private List HikersForE1 = new ArrayList();
    private List HikersForE2 = new ArrayList();
    private List HikersForE3 = new ArrayList();
    private List HikersForE4 = new ArrayList();

    private Thread generator;

    private TrailEntranceQueue entrance1, entrance2, entrance3, entrance4;

    public Controller() {

        initComponents();
        initEntrance();
        startButton.addActionListener(this); // start button listener
        checkTrailButton.addActionListener(this); // check trail
        openTrailButton.addActionListener(this); // open trail
        generator = new Hiker();
        imageLabel.setIcon(new ImageIcon("mountainImage.jpeg")); // add image
    }

    public void initEntrance() {

        entrance1 = new TrailEntranceQueue("Bear Lake Entrance - 1");
        entrance2 = new TrailEntranceQueue("Glacier Gorge Entrance - 2");
        entrance3 = new TrailEntranceQueue("Deer Ridge Entrance - 3");
        entrance4 = new TrailEntranceQueue("Wild Basin Entrance - 4");
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        startButton = new JButton();
        openTrailButton = new JButton();
        checkTrailButton = new JButton();
        jScrollPane3 = new JScrollPane();
        jTextArea2 = new JTextArea();
        imageLabel = new JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        setTitle("Purple Mountain National Forest Manager");

        startButton.setText("Start");
        openTrailButton.setText("Open Trail");
        checkTrailButton.setText("Check Trail");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText(" ");
        jScrollPane3.setViewportView(jTextArea2);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // set location of image

        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(openTrailButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(checkTrailButton, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(openTrailButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkTrailButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    public void startThread() {

        // creates a thread to generate hikers
        jTextArea2.setText("Generating hikers List...");

        flag = true;

        if (!generator.isAlive()) {

            System.out.println("Thread starting...");

            try {
                generator.start();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

    }

    // function to allot hikers list to each entrance; creating a list of HikersList for each entrance
    private void generateHikers() {

        Iterator hiker_list = Controller.HikersList.listIterator();

        System.out.println("Size:::" + Controller.HikersList.size());

        try {

            while (hiker_list.hasNext()) {
                HikersForE1.add(hiker_list.next());
                HikersForE2.add(hiker_list.next());
                HikersForE3.add(hiker_list.next());
                HikersForE4.add(hiker_list.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        extractHiker();
    }

    // extracts Hiker[] from list and pass to each entrance to fill
    public void extractHiker() {
        //System.out.println("entrance1-------------");
        String[] HE = getHikers(HikersForE1);
        entrance1.setHikers(HE);
        entrance1.addHikers();
        entrance1.addLast();

        HE = getHikers(HikersForE2);
        entrance2.setHikers(HE);
        entrance2.addHikers();
        entrance2.addLast();

        HE = getHikers(HikersForE3);
        entrance3.setHikers(HE);
        entrance3.addHikers();
        entrance3.addLast();

        HE = getHikers(HikersForE4);
        entrance4.setHikers(HE);
        entrance4.addHikers();
        entrance4.addLast();
        // printHikers(HE);
        printFullStack();
    }

    // function to display filled stack hikers name
    public void printFullStack() {

        jTextArea2.setText("");
        jTextArea2.setText("Hiker's List from (Filled Stack)\n\n");
        jTextArea2.append(entrance1.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance1.getQueueElement_full());

        jTextArea2.append(entrance2.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance2.getQueueElement_full());

        jTextArea2.append(entrance3.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance3.getQueueElement_full());

        jTextArea2.append(entrance4.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance4.getQueueElement_full());

    }

    // function to display non filled stack hikers name
    public void printNonFullStack() {

        jTextArea2.setText("");
        jTextArea2.setText("Hiker's List from (Non-Filled Stack)\n\n");

        jTextArea2.append(entrance1.toString());
        jTextArea2.append("\n**********************************\n");

        jTextArea2.append(entrance1.getQueueElement_notFull());

        jTextArea2.append(entrance2.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance2.getQueueElement_notFull());

        jTextArea2.append(entrance3.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance3.getQueueElement_notFull());
        jTextArea2.append(entrance4.toString());
        jTextArea2.append("\n**********************************\n");
        jTextArea2.append(entrance4.getQueueElement_notFull());

    }

    // function converting list of hikers to String[]
    public String[] getHikers(List TempHikers) {

        String[] temp = new String[TempHikers.size()];
        Iterator temp_iter = TempHikers.listIterator();
        int i = 0;

        while (temp_iter.hasNext()) {
            temp[i] = temp_iter.next().toString();
            i++;
        }
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            startThread();
        }
        else if (e.getSource() == openTrailButton) {
            flag = false;
            System.out.println("Stopped");
            Hiker.closeFile();
            generateHikers();
        }
        else if (e.getSource() == checkTrailButton) {
            printNonFullStack();
        }
    }
} // end of class