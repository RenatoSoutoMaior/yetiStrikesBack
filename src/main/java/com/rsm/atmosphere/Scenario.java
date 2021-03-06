package com.rsm.atmosphere;

import com.rsm.characters.Cabin;
import com.rsm.characters.Human;
import com.rsm.characters.Yeti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Scenario extends JFrame {

    private Container container;

    private int yetiVictory = 0;
    private int humanVictory = 0;

    private boolean menu = true;
    private boolean closeYetiX = false;
    private boolean closeYetiY = false;

    private JLabel yetiJL;
    private JLabel humanJL;
    private JLabel cabinJL;
    private JLabel wallRightJL;
    private JLabel wallLeftJL;
    private JLabel wallTopJL;
    private JLabel wallDownJL;
    private JLabel openingJL;
    private JLabel theEnd1JL;
    private JLabel theEnd2JL;

    private Icon yetiStop = new ImageIcon("src/main/resources/images/yeti-stoped.gif");
    private Icon yetiRight = new ImageIcon("src/main/resources/images/yeti-right.gif");
    private Icon yetiLeft = new ImageIcon("src/main/resources/images/yeti-left.gif");
    private Icon yetiWins = new ImageIcon("src/main/resources/images/yeti-celebrating.gif");
    private Icon humanStop = new ImageIcon("src/main/resources/images/human-stoped.gif");
    private Icon humanRight = new ImageIcon("src/main/resources/images/human-right.gif");
    private Icon humanLeft = new ImageIcon("src/main/resources/images/human-left.gif");
    private Icon cabin1I = new ImageIcon("src/main/resources/images/cabin-opened.png");
    private Icon cabin2I = new ImageIcon("src/main/resources/images/cabin-closed.png");
    private Icon openingI = new ImageIcon("src/main/resources/images/opening.gif");
    private Icon theEnd1I = new ImageIcon("src/main/resources/images/the-end-yeti.png");
    private Icon theEnd2I = new ImageIcon("src/main/resources/images/the-end-human.png");

    private JButton playNowB = new JButton();

    public Scenario() {
        super("World");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        Icon snowI = new ImageIcon("src/main/resources/images/snow.png");
        setContentPane(new JLabel(snowI));
        container = getContentPane();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void addItems() {
        humanJL = new JLabel(humanStop);
        humanJL.setSize(100, 100);
        humanJL.setLocation(400, 170);
        humanJL.setVisible(false);

        yetiJL = new JLabel(yetiStop);
        yetiJL.setSize(100, 100);
        yetiJL.setLocation(740, 170);
        yetiJL.setVisible(false);

        cabinJL = new JLabel(cabin1I);
        cabinJL.setSize(80, 100);
        cabinJL.setLocation(570, 170);
        cabinJL.setVisible(false);

        wallRightJL = new JLabel();
        wallRightJL.setSize(10, 1080);
        wallRightJL.setLocation(1520, 0);
        wallRightJL.setVisible(false);

        wallLeftJL = new JLabel();
        wallLeftJL.setSize(10, 1080);
        wallLeftJL.setLocation(380, 0);
        wallLeftJL.setVisible(false);

        wallDownJL = new JLabel();
        wallDownJL.setSize(1920, 10);
        wallDownJL.setLocation(0, 750);
        wallDownJL.setVisible(false);

        wallTopJL = new JLabel();
        wallTopJL.setSize(1920, 10);
        wallTopJL.setLocation(0, 160);
        wallTopJL.setVisible(false);

        openingJL = new JLabel(openingI);
        openingJL.setSize(1920, 1080);
        openingJL.setLocation(0, 0);
        openingJL.setVisible(true);

        theEnd1JL = new JLabel(theEnd1I);
        theEnd1JL.setSize(500, 150);
        theEnd1JL.setLocation(710, 210);
        theEnd1JL.setVisible(false);

        theEnd2JL = new JLabel(theEnd2I);
        theEnd2JL.setSize(500, 150);
        theEnd2JL.setLocation(710, 210);
        theEnd2JL.setVisible(false);

        playNowB.setBounds(0, 0, 1920, 1080);
        playNowB.addActionListener(new buttonPlayNow());
        playNowB.setVisible(true);

        container.add(theEnd1JL);
        container.add(theEnd2JL);
        container.add(humanJL);
        container.add(yetiJL);
        container.add(cabinJL);
        container.add(openingJL);
        container.add(playNowB);
        container.add(wallRightJL);
        container.add(wallLeftJL);
        container.add(wallTopJL);
        container.add(wallDownJL);
    }

    private class buttonPlayNow implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
                playNowB.setVisible(false);
                openingJL.setVisible(false);
                menu = false;
            }
        }
    }

    public void play() throws InterruptedException {
        while (menu) {
            repaint();
        }
        artificialIntelligence(new Human(), new Yeti(), new Cabin());
    }

    private void theEnd(Human human, Cabin cabin) {
        if (!human.getLive()) {
            setYetiIcon(yetiWins);
            theEnd1JL.setVisible(true);
            yetiVictory = yetiVictory + 1;
        } else if (!cabin.isOpen()) {
            setYetiIcon(yetiStop);
            theEnd2JL.setVisible(true);
            humanVictory = humanVictory + 1;
        }
        playAgain();
    }

    private void playAgain() {
        int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?");

        if (playAgain == JOptionPane.YES_OPTION) {
            humanJL.setVisible(false);
            yetiJL.setVisible(false);
            cabinJL.setVisible(false);
            theEnd1JL.setVisible(false);
            theEnd2JL.setVisible(false);
            closeYetiX = false;
            closeYetiY = false;
            setCabinIcon(cabin1I);
        } else {
            if (humanVictory == 0) {
                JOptionPane.showMessageDialog(null, "Human did not win and Yeti wons " + yetiVictory + " times!");
                JOptionPane.showMessageDialog(null, "See you later!");
                System.out.println("Human did not win and Yeti wons " + yetiVictory + " times!");
                dispose();
                System.exit(0);
            }
            if (yetiVictory == 0) {
                JOptionPane.showMessageDialog(null, "Human wons " + humanVictory + " times and Yeti did not win!");
                JOptionPane.showMessageDialog(null, "See you later!");
                dispose();
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Human wons " + humanVictory + " times and Yeti wons " + yetiVictory + " times!");
                JOptionPane.showMessageDialog(null, "See you later!");
                dispose();
                System.exit(0);
            }
        }
    }

    private void setYetiIcon(Icon yetiIcon) {
        this.yetiJL.setIcon(yetiIcon);
    }

    private void setHumanIcon(Icon humanIcon) {
        this.humanJL.setIcon(humanIcon);
    }

    private void setCabinIcon(Icon cabinIcon) {
        this.cabinJL.setIcon(cabinIcon);
    }

    private void positionJLYeti(Yeti yeti, Human human) {
        if (yeti.getPositionX() > human.getPositionX()) {
            setYetiIcon(yetiLeft);
        } else {
            setYetiIcon(yetiRight);
        }
    }

    private void positionJLHumanRunToCabin(Human human, Cabin cabin) {
        if (human.getPositionX() > cabin.getPositionX()) {
            setHumanIcon(humanLeft);
        } else {
            setHumanIcon(humanRight);
        }
    }

    private void positionJLHumanRunAway(Human human, Yeti yeti) {
        if (human.getPositionX() < yeti.getPositionX()) {
            setHumanIcon(humanLeft);

        } else {
            setHumanIcon(humanRight);
        }
    }

    private void randomObjectPlace(Human human, Yeti yeti, Cabin cabin) {
        yetiJL.setLocation(yeti.randomPositionX(), yeti.randomPositionY());
        humanJL.setLocation(human.randomPositionX(), human.randomPositionY());
        cabinJL.setLocation(cabin.randomPositionX(), cabin.randomPositionY());
        theWall(human, cabin);
        yetiJL.setVisible(true);
        humanJL.setVisible(true);
        cabinJL.setVisible(true);
    }

    private void theWall(Human human, Cabin cabin) {
        if (human.getPositionX() <= wallLeftJL.getX()) {
            human.setPositionX(human.getPositionX() + 1);
        }
        if (human.getPositionX() >= wallRightJL.getX()) {
            human.setPositionX(human.getPositionX() - 1);
        }
        if (human.getPositionY() <= wallTopJL.getY()) {
            human.setPositionY(human.getPositionY() + 1);
        }
        if (human.getPositionY() >= wallDownJL.getY()) {
            human.setPositionY(human.getPositionY() - 1);
        }
        if (cabin.getPositionX() <= wallLeftJL.getX() + 30) {
            cabin.setPositionX(cabin.getPositionX() + 50);
            cabinJL.setLocation(cabin.getPositionX(), cabin.getPositionY());
        }
        if (cabin.getPositionX() >= wallRightJL.getX() - 30) {
            cabin.setPositionX(cabin.getPositionX() - 50);
            cabinJL.setLocation(cabin.getPositionX(), cabin.getPositionY());
        }
        if (cabin.getPositionY() <= wallTopJL.getY() + 30) {
            cabin.setPositionY(cabin.getPositionY() + 50);
            cabinJL.setLocation(cabin.getPositionX(), cabin.getPositionY());
        }
        if (cabin.getPositionY() >= wallDownJL.getY() - 30) {
            cabin.setPositionY(cabin.getPositionY() - 50);
            cabinJL.setLocation(cabin.getPositionX(), cabin.getPositionY());
        }
    }

    private void compareYetiHuman(Yeti yeti, Human human) {
        if (yeti.getPositionX() == human.getPositionX() && yeti.getPositionY() == human.getPositionY()
                || (yeti.getPositionX() - 1) == human.getPositionX() && yeti.getPositionY() == human.getPositionY()
                || yeti.getPositionX() == human.getPositionX() && (yeti.getPositionY() - 1) == human.getPositionY()
                || (yeti.getPositionX() + 1) == human.getPositionX() && yeti.getPositionY() == human.getPositionY()
                || yeti.getPositionX() == human.getPositionX() && (yeti.getPositionY() + 1) == human.getPositionY()) {
            human.setLive(false);
            humanJL.setVisible(false);
        }
    }

    private void compareHumanCabin(Human human, Cabin cabin) {
        if (human.getPositionX() == cabin.getPositionX() && human.getPositionY() == cabin.getPositionY()
                && human.getLive()) {
            cabin.setOpen(false);
            humanJL.setVisible(false);
            setCabinIcon(cabin2I);
        }
    }

    private void compareBestChoise(Human human, Yeti yeti, Cabin cabin) {
        if (human.getPositionX() > yeti.getPositionX()) {
            if ((human.getPositionX() - yeti.getPositionX() <= 15) && !closeYetiX) {
                closeYetiX = true;
            }
        }
        if (human.getPositionX() < yeti.getPositionX()) {
            if ((human.getPositionX() - yeti.getPositionX() >= -15) && !closeYetiX) {
                closeYetiX = true;
            }
        }
        if (human.getPositionY() > yeti.getPositionY()) {
            if ((human.getPositionY() - yeti.getPositionY() <= 15) && !closeYetiY) {
                closeYetiY = true;
            }
        }

        if (human.getPositionY() < yeti.getPositionY()) {
            if ((human.getPositionY() - yeti.getPositionY() >= -15) && !closeYetiY) {
                closeYetiY = true;
            }
        }
        if ((closeYetiX && closeYetiY)) {
            checkHumanLiveRunYeti(human, yeti);
            positionJLHumanRunAway(human, yeti);
        } else {
            checkHumanLiveRunToCabin(human, yeti, cabin);
            positionJLHumanRunToCabin(human, cabin);
        }
    }

    private void checkHumanLiveRunYeti(Human human, Yeti yeti) {
        if (human.getLive()) {
            yeti.toHunt(human);
            yetiJL.setLocation(yeti.getPositionX(), yeti.getPositionY());
            human.runAway(yeti);
            humanJL.setLocation(human.getPositionX(), human.getPositionY());
        }
    }

    private void checkHumanLiveRunToCabin(Human human, Yeti yeti, Cabin cabin) {
        if (human.getLive()) {
            yeti.toHunt(human);
            yetiJL.setLocation(yeti.getPositionX(), yeti.getPositionY());
            human.runToCabin(cabin);
            humanJL.setLocation(human.getPositionX(), human.getPositionY());
        }
    }

    private void artificialIntelligence(Human human, Yeti yeti, Cabin cabin) throws InterruptedException {
        randomObjectPlace(human, yeti, cabin);

        while (human.getLive() && cabin.isOpen()) {
            theWall(human, cabin);
            compareYetiHuman(yeti, human);
            compareHumanCabin(human, cabin);
            compareBestChoise(human, yeti, cabin);
            positionJLYeti(yeti, human);
            Thread.sleep(10);
        }
        theEnd(human, cabin);
    }
}