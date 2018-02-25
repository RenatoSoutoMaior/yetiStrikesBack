package com.rsm;

import com.rsm.atmosphere.Music;
import com.rsm.atmosphere.Scenario;
import com.rsm.characters.Cabin;
import com.rsm.characters.Human;
import com.rsm.characters.Yeti;

public class Start extends Scenario {

    public static void main(String[] args) throws InterruptedException {

        Music.playMusic();
        Scenario world = new Scenario();
        world.addItems();
        world.setVisible(true);

        while (true) {
            world.play(new Human(), new Yeti(), new Cabin());
        }
    }
}
