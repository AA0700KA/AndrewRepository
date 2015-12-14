package com.midgardabc.day7.tanks;

import com.midgardabc.day7.tanks.bf.tanks.Action;
import com.midgardabc.day7.tanks.bf.tanks.Tank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by user on 13.09.2015.
 */
public class Record {

    private ActionField af;

    public Record(ActionField af) {
        this.af = af;
    }

    public void readFromFile(String fileName) throws Exception
    {

        try(FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(reader)){

            String str;
            while((str = buffer.readLine()) != null) {
                System.out.println(str);
                String action = str.substring(0, str.indexOf("_"));
                String tank = str.substring(str.indexOf("_") + 1);
                Action a = convertsToAction(action);

                if (tank.equals("Tiger")) {
                    if (!af.getTiger().isDestroyed())
                    af.processAction(a, af.getTiger());
                } else if (tank.equals("BT7")) {
                    if (!af.getAggressor().isDestroyed())
                    af.processAction(a, af.getAggressor());
                } else {
                    if (!af.getDefender().isDestroyed())
                    af.processAction(a, af.getDefender());
                }
            }


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private Action convertsToAction(String action)
    {
        if (action.equals("MOVE")) return Action.MOVE;
        else if (action.equals("FIRE")) return Action.FIRE;
        else if (action.equals("UP")) return Action.TURN_UP;
        else if (action.equals("DOWN")) return Action.TURN_DOWN;
        else if (action.equals("RIGHT")) return Action.TURN_RIGHT;
        else if (action.equals("LEFT")) return Action.TURN_LEFT;
        return Action.NONE;
    }
}
