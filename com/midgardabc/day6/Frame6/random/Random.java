package com.midgardabc.day6.Frame6.random;

/**
 * Created by user on 20.06.2015.
 */
public class Random {

    public int nextInt(int count)
    {
        long random = System.nanoTime();
        String randomMillis = String.valueOf(random);
        char millis = randomMillis.charAt(randomMillis.length() - 1);
        randomMillis = String.valueOf(millis);
        int result = Integer.parseInt(randomMillis);
        if (result > 0 && result < count) {
            return result;
        }
        return 0;
    }

    public String returnLocation()
    {
        long random = System.nanoTime();
        String randomMillis = String.valueOf(random);
        char millis = randomMillis.charAt(randomMillis.length() - 1);
        randomMillis = String.valueOf(millis);
        int result = Integer.parseInt(randomMillis);
        if (result > 0 && result <= 4)
        {
            return 64 + "_" + 64;
        }
        else if (result > 4 && result < 8)
        {
            return 512 + "_" + 128;
        }
        return 512 + "_" + 384;
    }

}
