package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {

        byte[] networkAdress = new byte[4];

        for(int x=0; x < networkAdress.length; x++){

            networkAdress[x] = (byte)(ip[x] & mask[x]);

        }

        return networkAdress;
    }

    public static void print(byte[] bytes) {
        StringBuilder str;
        StringBuilder adress= new StringBuilder();

        for (byte in: bytes){

            int unsigned = Byte.toUnsignedInt(in);
            str = new StringBuilder(Integer.toBinaryString(unsigned));

            while (str.length()!=8){

                str.insert(0, "0");

            }

            adress.append(str).append(" ");
        }
       // System.out.format()
        System.out.println(adress);
    }
}
