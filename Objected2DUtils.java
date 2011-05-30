/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Objected2DUtils {

    //error message if thread is interrupted while sleeping
    public static String message = "Thread interrupted";

    /**
     * Sleep (seconds)
     * @param time
     */
    public static void sleep (double time) {
        Objected2DUtils.usleep((int) time*1000);
    }

    /**
     * Sleep (miliseconds)
     * @param time
     */
    public static void usleep (int time) {
        try{
            Thread.currentThread().sleep(time);
        } catch(java.lang.InterruptedException ie) {
            throw new RuntimeException(Objected2DUtils.message);
        }
    }

    public static void printImageAlphaRaster (BufferedImage image) {
        int width = image.getWidth();
        int[] imgData = new int[width];


        System.out.print("     ||");
        for (int x = 0; x < width; x++) {
            System.out.print(Objected2DUtils.extendString(x, "0", 3) + "|");
        }
        System.out.print("\n-------");
        for (int x = 0; x < width; x++) {
            System.out.print("----");
        }
        System.out.print("\n");

        for (int y = 0; y < image.getHeight(); y++) {
            // fetch a line of data from each image
            image.getRGB(0, y, width, 1, imgData, 0, 1);
            
            // apply the mask
            System.out.print(" " + Objected2DUtils.extendString(y, "0", 3) + " ||");

            for (int x = 0; x < width; x++) {
                String string = String.valueOf(((imgData[x] >> 24) & 0xFF));

                while (string.length() < 3) {
                    string = "0"+string;
                }

                System.out.print("" + string + "|");
            }

            System.out.print("\n");
        }
    }

    public static void printImageBlueRaster (BufferedImage image) {
        int width = image.getWidth();
        int[] imgData = new int[width];


        System.out.print("     ||");
        for (int x = 0; x < width; x++) {
            System.out.print(Objected2DUtils.extendString(x, "0", 3) + "|");
        }
        System.out.print("\n-------");
        for (int x = 0; x < width; x++) {
            System.out.print("----");
        }
        System.out.print("\n");

        for (int y = 0; y < image.getHeight(); y++) {
            // fetch a line of data from each image
            image.getRGB(0, y, width, 1, imgData, 0, 1);

            // apply the mask
            System.out.print(" " + Objected2DUtils.extendString(y, "0", 3) + " ||");

            for (int x = 0; x < width; x++) {
                String string = String.valueOf(((imgData[x] >> 8) & 0xFF));

                while (string.length() < 3) {
                    string = "0"+string;
                }

                System.out.print("" + string + "|");
            }

            System.out.print("\n");
        }
    }

    public static String extendString (Object stringO, String extender, int length) {
        String string = String.valueOf(stringO);

        while (string.length() < length) {
            string = extender + string;
        }

        return string;
    }

    public static void test () {
        boolean [][] test = new boolean[4][4];

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                test[x][y] = false;
            }
        }

        test[0][0]=true;
        test[3][0]=true;
        test[3][3]=true;

        Objected2DUtils.print2DArrayMatrix(test);
    }

    public static void print2DArrayMatrixCombined (
                boolean[][] array1,
            boolean[][] array2,
            int xOffset,
            int yOffset) {

        boolean[][] cArray = new boolean[100][100];

        for (int y = 0; y < 100; y++)
        {
            for (int x = 0; x < 100; x++)
            {
                try {
                    if (x-xOffset >= 0
                                && y-yOffset >= 0
                                && x-xOffset < array2.length
                                && y-yOffset < array2[0].length ) {

                        if (array1[x][y] || array2[x-xOffset][y-yOffset])
                            cArray[x][y]=true;
                        else
                            cArray[x][y]=false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    cArray[x][y]=false;
                }
                
            }
        }

        Objected2DUtils.print2DArrayMatrix(cArray);
    }
    
    public static void print2DArrayMatrix (boolean[][] array) {
        int width = array[0].length;

        Object[][] oArray = new Object[array.length][width];

        for (int x=0; x<array.length; x++) {
            for (int y=0; y<width; y++) {
                oArray[x][y] = (array[x][y]) ? 1 : 0;
            }
        }

        Objected2DUtils.print2DArrayMatrix(oArray);
    }

    public static void print2DArrayMatrix (Object[][] array) {
        int width = array[0].length;
        for (int y=0; y<width; y++) {
            for (int x=0; x<array.length; x++) {
                System.out.print("" + array[x][y] + "|");
            }

            System.out.print("\n");
        }
    }
}
