package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Random;

public class Frame extends JFrame implements ActionListener
{
    final int frameWidth = 740;
    final int frameHeight = 500;

    List<Vocabulary> vocabList = new List<>();
    List< Pair<String, Integer> > correctnessList = new List<>();

    JButton btnAdd = new JButton("Press here for adding");
    JTextField txtInEN = new JTextField();
    JTextField txtInDE = new JTextField();

    JRadioButton deToEn = new JRadioButton("GE to EN");
    JRadioButton enToDe = new JRadioButton("EN to GE");
    ButtonGroup langBtnGroup = new ButtonGroup();

    JButton btnRequest = new JButton("Ask question/Check answer");
    JLabel lblQuestion = new JLabel("Question");
    JTextField txtInUsrAnswer = new JTextField("Your Answer");
    JLabel lblAnswer = new JLabel();

    JComboBox<String> cmbVocab = new JComboBox<String>();
    JButton btnDelete = new JButton("Delete");

    boolean check = false;
    Vocabulary curQuestion, curAnswer;
    Vocabulary curVocab;
    String curGerman;

    public Frame() {
        super("Vocabulary Test");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setLocation(0, 0);
        setLayout(null);

        setResizable(false);
        setVisible(true);

        //btnAdd.setFont(new Font("Arial", Font.PLAIN, 10));
        btnAdd.setBounds(0, 0, 200, 40);
        btnAdd.setVisible(true);
        btnAdd.setActionCommand("add");
        btnAdd.addActionListener(this);

        txtInDE.setFont(new Font("Arial", Font.PLAIN, 15));
        txtInDE.setBounds(0, 40, 100, 30);
        txtInDE.setToolTipText("German");
        txtInDE.setVisible(true);

        txtInEN.setFont(new Font("Arial", Font.PLAIN, 15));
        txtInEN.setBounds(100, 40, 100, 30);
        txtInEN.setToolTipText("English");
        txtInEN.setVisible(true);

        deToEn.setBounds(0, 185, 100, 30);
        deToEn.setSelected(true);
        deToEn.setVisible(true);

        enToDe.setBounds(100, 185, 100, 30);
        enToDe.setSelected(false);
        enToDe.setVisible(true);

        langBtnGroup.add(deToEn);
        langBtnGroup.add(enToDe);

        btnRequest.setBounds(0, 140, 200, 40);
        btnRequest.setVisible(true);
        btnRequest.setActionCommand("newVoc");
        btnRequest.addActionListener(this);

        lblQuestion.setBounds(210, 140, frameWidth-210, 40);
        lblQuestion.setVisible(true);

        txtInUsrAnswer.setBounds(210, 182, frameWidth-215, 40);
        txtInUsrAnswer.setVisible(true);

        lblAnswer.setFont(new Font("Courier Sans", Font.BOLD, 20));
        lblAnswer.setBounds(5, 222, frameWidth-210, frameHeight-223);
        lblAnswer.setVerticalAlignment(SwingConstants.TOP);
        lblAnswer.setVisible(true);

        cmbVocab.setBounds(250, 0, 300, 40);
        cmbVocab.setVisible(true);

        btnDelete.setBounds(555, 0, 100, 40);
        btnDelete.setActionCommand("delete");
        btnDelete.addActionListener(this);
        btnDelete.setVisible(true);

        vocabList.toFirst();

        add(btnAdd);
        add(txtInEN);
        add(txtInDE);
        add(deToEn);
        add(enToDe);
        add(btnRequest);
        add(lblQuestion);
        add(txtInUsrAnswer);
        add(lblAnswer);
        add(cmbVocab);
        add(btnDelete);
    }

    public void actionPerformed(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "add":
                addVocab();
                break;

            case "newVoc":
                if (check) {
                    checkAnswer();
                } else {
                    askQuestion();
                }
                break;

            case "delete":
                deleteElement();
                break;

            default:
                System.out.println("Unidentified action command: " + evt.getActionCommand());
                break;
        }
    }

    private void deleteElement() {
        if (vocabList.isEmpty() || cmbVocab.getSelectedItem() == null) {
            return;
        }

        Vocabulary prevElement = vocabList.getContent();

        int position = cmbVocab.getSelectedIndex();

        vocabList.toFirst();
        for (int i = 0; i < position; i++) {
            vocabList.next();
        }
        if (vocabList.getContent() == prevElement) {
            vocabList.remove();
            prevElement = vocabList.getContent();
        } else {
            vocabList.remove();
        }

        while (vocabList.hasAccess()) {
            if (vocabList.getContent() == prevElement) {
                break;
            }
        }

        vocabList.next();
        cmbVocab.removeItem(cmbVocab.getSelectedItem());
    }

    private void checkAnswer() {
        check = !check;
        if (txtInUsrAnswer.getText().trim().equals("")) {
            return;
        }
        boolean correct = checkForCorrectness();
        if (correct) {
            lblAnswer.setForeground(Color.GREEN);
            lblAnswer.setText("That was correct!");
        } else {
            lblAnswer.setForeground(Color.RED);
            lblAnswer.setText("That was wrong. The correct word was: " + curAnswer);
        }

        correctnessList.toFirst();
        while (correctnessList.hasAccess()) {
            if (correctnessList.getContent().first.equals(curGerman)) {
                if (correct)
                    correctnessList.getContent().setSecond(correctnessList.getContent().second + 1);
                else
                    correctnessList.getContent().setSecond(correctnessList.getContent().second - 1);
                return;
            }
            correctnessList.next();
        }
    }

    private void askQuestion() {
        check = !check;
        if (vocabList.isEmpty()) {
            lblAnswer.setForeground(Color.BLUE);
            lblAnswer.setText("There isn't any vocabulary");
            check = !check;
            return;
        }
        if (!vocabList.hasAccess()) {
            vocabList.toFirst();
        }

        if (deToEn.isSelected()) {
            curVocab = vocabList.getContent();
            //curQuestion = vocabList.getContent().getRandomWord(Language.FIRST);
            //curAnswer = vocabList.getContent().getRandomWord(Language.SECOND);
        } else {
            //curQuestion = vocabList.getContent().getRandomWord(Language.SECOND);
            //curAnswer = vocabList.getContent().getRandomWord(Language.FIRST);
        }
        curGerman = vocabList.getContent().getFirstWordInFirstLanguage();

        lblQuestion.setText( curVocab.getRandomWord(getLanguage()) );
        txtInUsrAnswer.setText("");
        lblAnswer.setText("");


        getNextVocab();
        //vocabList.next();


    }

    public Language getLanguage() {
        if (deToEn.isSelected()) {
            return Language.FIRST;
        } else {
            return Language.SECOND;
        }
    }

    public Language getInverseLanguage() {
        if (deToEn.isSelected()) {
            return Language.SECOND;
        } else {
            return Language.FIRST;
        }
    }

    private void getNextVocab() {
        Random rng = new Random();
        boolean lessKnown = rng.nextInt(cmbVocab.getItemCount()) > (int)cmbVocab.getItemCount()/3;

        List< Pair<String, Integer> > tempList = new List< Pair<String, Integer> >();
        int amount;
        do {
            amount = 0;
            correctnessList.toFirst();
            while (correctnessList.hasAccess()) {
                if (correctnessList.getContent().second() <= 0 && lessKnown) {
                    tempList.append(correctnessList.getContent());
                    amount++;
                } else if (correctnessList.getContent().second() > 0 && !lessKnown) {
                    tempList.append(correctnessList.getContent());
                    amount++;
                }
                correctnessList.next();
            }
            if (tempList.isEmpty()) {
                lessKnown = !lessKnown;
            }
        } while (tempList.isEmpty());

        int nextIndex = rng.nextInt(amount);
        tempList.toFirst();
        for (int i = 0; i < nextIndex; i++) { tempList.next(); }
        String nextString = tempList.getContent().first();
        while (vocabList.hasAccess()) {
            if (vocabList.getContent().isIn(nextString, Language.FIRST)) {
                return;
            }
            vocabList.next();
        }
    }

    private String createCmbContent(String german, String english) {
        try {
            return (String) german.trim() + " | " + english.trim();
        } catch (java.lang.NullPointerException e) {
            return "";
        }
    }

    private boolean checkForCorrectness() {
        return curVocab.isIn(txtInUsrAnswer.getText().trim(), getInverseLanguage());
    }

    private void addVocab() {
        if (txtInDE.getText().trim().equals("") || txtInEN.getText().trim().equals("")) {
            txtInDE.setText(txtInDE.getText().trim());
            txtInEN.setText(txtInEN.getText().trim());
            return;
        }

        vocabList.toFirst();
        while (vocabList.hasAccess()) {
            if (vocabList.getContent().isIn(txtInDE.getText(), Language.FIRST) ||
                    vocabList.getContent().isIn(txtInEN.getText(), Language.SECOND)) {
                vocabList.getContent().add(txtInDE.getText(), txtInEN.getText());

                txtInDE.setText("");
                txtInEN.setText("");

                return;
            }
            vocabList.next();
        }

        Vocabulary tempVoc = new Vocabulary( txtInDE.getText(), txtInEN.getText() );
        vocabList.append(tempVoc);

        Pair<String, Integer> tempCor = new Pair<String, Integer>(txtInDE.getText(), 0);
        correctnessList.append(tempCor);

        String voc = createCmbContent(txtInDE.getText().trim(), txtInEN.getText().trim());
        cmbVocab.addItem(voc);

        txtInDE.setText("");
        txtInEN.setText("");
    }

}
