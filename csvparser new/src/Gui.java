import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Gui extends javax.swing.JFrame
{
    public boolean hasStarted;
    String file;
    String destination;
    int filetype;

    public Gui()
    {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        inputBrowseButton = new javax.swing.JToggleButton();
        inputField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        outputBrowseButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        outputField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        actorsButton = new javax.swing.JRadioButton();
        actressButton = new javax.swing.JRadioButton();
        ratingButton = new javax.swing.JRadioButton();
        businessbutton = new javax.swing.JRadioButton();
        countryButton = new javax.swing.JRadioButton();
        durationButton = new javax.swing.JRadioButton();
        bioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parser");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Parser groep 9");

        inputBrowseButton.setText("Browse");
        inputBrowseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputBrowseButtonMouseClicked(evt);
            }
        });
        inputBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBrowseButtonActionPerformed(evt);
            }
        });

        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputFieldKeyTyped(evt);
            }
        });

        startButton.setText("Run!");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });

        outputBrowseButton.setText("Browse");
        outputBrowseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outputBrowseButtonMouseClicked(evt);
            }
        });
        outputBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputBrowseButtonActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" Input file destination :");

        outputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Output file destination :");

        buttonGroup1.add(actorsButton);
        actorsButton.setText("Actors");
        actorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actorsButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(actressButton);
        actressButton.setText("Actress");

        buttonGroup1.add(ratingButton);
        ratingButton.setText("Movie Rating");

        buttonGroup1.add(businessbutton);
        businessbutton.setText("Business");

        buttonGroup1.add(countryButton);
        countryButton.setText("Countries");

        buttonGroup1.add(durationButton);
        durationButton.setText("Running Times");

        buttonGroup1.add(bioButton);
        bioButton.setText("Biography");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(actorsButton)
                                                        .addComponent(businessbutton)
                                                        .addComponent(bioButton))
                                                .addGap(79, 79, 79)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(actressButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                                                                .addComponent(ratingButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(durationButton)
                                                                        .addComponent(countryButton))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(inputBrowseButton))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(outputBrowseButton)))))
                                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(actorsButton)
                                        .addComponent(actressButton)
                                        .addComponent(ratingButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(countryButton)
                                        .addComponent(businessbutton))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(durationButton)
                                        .addComponent(bioButton))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputBrowseButton)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(outputBrowseButton)
                                        .addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(startButton))
        );

        actorsButton.getAccessibleContext().setAccessibleName("Actors");

        pack();
    }// </editor-fold>

    private void inputBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }



    //browse knop (input)
    private void inputBrowseButtonMouseClicked(java.awt.event.MouseEvent evt) {

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile().toString();
            inputField.setText(file);
            System.out.println(file);
        }




    }
    //textveld (input) actions
    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {


    }
    //run button
    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {

        if(inputField.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Please enter a filepath!");
        else if(outputField.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Please enter an output path");
        else
        {
            if(actorsButton.isSelected())
                filetype = 0;
            else if(actressButton.isSelected())
                filetype = 1;
            else if(bioButton.isSelected())
                filetype = 2;
            else if(businessbutton.isSelected())
                filetype = 3;
            else if(countryButton.isSelected())
                filetype = 4;
            else if(durationButton.isSelected())
                filetype = 5;
            else if(ratingButton.isSelected())
                filetype = 6;
            hasStarted = !inputField.getText().equals("");
        }

    }
    //textField keytyped
    private void inputFieldKeyTyped(java.awt.event.KeyEvent evt) {

        file = inputField.getText();



    }

    private void outputFieldActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void outputBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void outputBrowseButtonMouseClicked(java.awt.event.MouseEvent evt) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.showSaveDialog(null);
        destination = filechooser.getSelectedFile().toString();
        outputField.setText(destination);
        System.out.println(destination);

    }

    private void actorsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JRadioButton actorsButton;
    private javax.swing.JRadioButton actressButton;
    private javax.swing.JRadioButton bioButton;
    private javax.swing.JRadioButton businessbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton countryButton;
    private javax.swing.JRadioButton durationButton;
    private javax.swing.JToggleButton inputBrowseButton;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton outputBrowseButton;
    private javax.swing.JTextField outputField;
    private javax.swing.JRadioButton ratingButton;
    private javax.swing.JButton startButton;
    // End of variables declaration                   
}