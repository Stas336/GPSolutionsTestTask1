package main;

import java.io.*;

public class Main
{
    public static void main(String args[])
    {
        long start = System.nanoTime();
        try
        {
            BufferedReader input = new BufferedReader(new FileReader("input.txt"));
            String[] buff = input.readLine().split(" ");
            int k1 = Integer.parseInt(buff[0]);
            int l1 = Integer.parseInt(buff[1]);
            int m1 = Integer.parseInt(buff[2]);
            buff = input.readLine().split(" ");
            int k2 = Integer.parseInt(buff[0]);
            int l2 = Integer.parseInt(buff[1]);
            int m2 = Integer.parseInt(buff[2]);
            input.close();
            System.out.printf("%d (amount of bolts), %d%% (lost percentage), %d (cost for 1 unit)\n", k1, l1, m1);
            System.out.printf("%d (amount of nuts), %d%% (lost percentage), %d (cost for 1 unit)\n", k2, l2, m2);
            if (check(k1,l1,m1) && check(k2,l2,m2))
            {
                int damage = count(k1, l1, m1, k2, l2, m2);
                System.out.printf("Total damage is %d\n", damage);
                BufferedWriter output = new BufferedWriter(new FileWriter("output.txt"));
                output.write(String.valueOf(damage));
                output.close();
            }
            else
            {
                System.out.println("Error. Check your input parameters");
                return;
            }
        }catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return;
        }
        float time = (float)(System.nanoTime()-start)/1000000000;
        System.out.printf("Time elapsed: %f seconds\n", time);
    }
    private static boolean check(int k, int l, int m)
    {
        if (k < 100 || k > 30000 || k % 100 != 0)
        {
            return false;
        }
        else if (l < 0 || l > 100)
        {
            return false;
        }
        else if (m < 1 || m > 100)
        {
            return false;
        }
        return true;
    }
    private static int count(int k1, int l1, int m1, int k2, int l2, int m2)
    {
        int damage = (k1 * l1/100 * m1) + (k2 * l2/100 * m2);
        k1 = k1 - (k1 * l1/100);
        k2 = k2 - (k2 * l2/100);
        if (k1 > k2)
        {
            damage += (k1 - k2) * m1;
        }
        else
        {
            damage += (k2 - k1) * m2;
        }
        return damage;
    }
}