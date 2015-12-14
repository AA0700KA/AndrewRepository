package com.midgardabc.day6.Frame6.additions;

import java.awt.*;

/**
 * Created by user on 03.07.2015.
 */
public interface Drowable {

    Color[] tankColor = {new Color(88, 156, 0), new Color(0, 255, 200)};
    Color[] tigerColor = {new Color(55, 12, 170), new Color(222, 155, 200)};

    void draw(Graphics g);
}
