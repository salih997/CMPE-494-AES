package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static ArrayList<int[]> key_words = new ArrayList<int[]>();

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        File key_file = new File("/Users/cisel/Desktop/CMPE494/src/com/company/key_file.txt");

        Scanner scan = new Scanner(key_file);
        String key = scan.nextLine();

        int[] w0 = new int[4];
        int[] w1 = new int[4];
        int[] w2 = new int[4];
        int[] w3 = new int[4];

        key_words.add(w0);
        key_words.add(w1);
        key_words.add(w2);
        key_words.add(w3);

        for (int i = 0; i < key.length(); i = i + 2) {  //dividing key to the words, words to the bytes
            String hex = key.substring(i, i + 2);
            int hx = Integer.parseInt(hex, 16);
            key_words.get(i / 8)[(i / 2) % 4] = hx;
        }
        generate_key(4, 1);
    }

    /*public static int generate_key(int round, String key, int len) throws FileNotFoundException {
        int[] r_con= {1,2,4,8,16,32,64, 128, 27, 54, 108};
        if(key.length() != len){
            System.out.println("Invalid Key Length");
        }

        int[] w0 = new int[4];
        int[] w1 = new int[4];
        int[] w2 = new int[4];
        int[] w3 = new int[4];

        ArrayList<int[]> vectors = new ArrayList<>(){
            {
                add(w0);
                add(w1);
                add(w2);
                add(w3);
            }
        };

        for(int i=0; i < key.length(); i=i+2){  //dividing key to the words, words to the bytes
            String hex = key.substring(i, i+2);
            int hx = Integer.parseInt(hex, 16);
            vectors.get(i / 8)[(i/2)%4] = hx;
        }

        int[] w4= new int[4];

        //circular byte left shift
        for(int i=0; i<4; i++){
            int a = (i+3)%4;
            System.out.println(a);
            w4[i] = vectors.get(3)[(i+3)%4];
        }

        w4 = substitude(w4); //substitution

        w4[0] = w4[0] ^ r_con[round-1]; //adding round constant

        vectors.add(w4); // w4 = g(w3)

        for(int i=0; i < 4; i++){
            int[] new_w = new int[4];
            for(int j=0; j<4; j++){
                new_w[j] = vectors.get(i)[j] ^ vectors.get(i+4)[j];
            }
            vectors.add(new_w);
        }

        for(int i=0; i < vectors.size(); i++){
            System.out.println(Arrays.toString(vectors.get(i)));
        }

        for(int i =5; i < vectors.size(); i++){
            for(int j=0; j<vectors.get(i).length; j++){
                System.out.print(Integer.toHexString(vectors.get(i)[j]));
            }
        }
        return len;
    }*/

    public static int[] substitude (int[] x) {
        int[] a = {183, 90, 157, 133};
        return a;
    }

    public static void generate_key(int words, int round) { //words = 4 for 128-bit key, 6 for 192 bit key, 8 for 256 bit key

        int[] old_key_part;
        int[] old_key_part_to_modify;
        int[] r_con= {1,2,4,8,16,32,64, 128, 27, 54, 108};

        for(int i = round*4; i< (round+1)*4; i++ ){
            int[] new_key_part = new int[4];
            if(i < words){
               new_key_part = key_words.get(i);
            }
            else if(i >= words && i%words == 0){
                int[] temp_arr = new int[4];
                old_key_part_to_modify = key_words.get(i-1);
                for(int k=0; k<4; k++){
                    temp_arr[(k+3)%4] = old_key_part_to_modify[k];
                }
                temp_arr = substitude(temp_arr);
                temp_arr[0] = temp_arr[0] ^ r_con[round-1];
                old_key_part = key_words.get(i-words);
                for(int k=0; k < 4; k++){
                    new_key_part[k] = temp_arr[k] ^ old_key_part[k];
                }
            }
            else if(i >= words && words>6 && i%words == 4){
                old_key_part = key_words.get(i-words);
                old_key_part_to_modify = substitude(key_words.get(i-1));
                for(int k=0; k < 4; k++){
                    new_key_part[k] = old_key_part_to_modify[k] ^ old_key_part[k];
                }
            }
            else{
                old_key_part = key_words.get(i-words);
                old_key_part_to_modify = key_words.get(i-1);
                for(int k=0; k < 4; k++){
                    new_key_part[k] = old_key_part_to_modify[k] ^ old_key_part[k];
                }
            }
            key_words.add(new_key_part);
        }

        for(int k= round*4; k < (round+1)*4; k++){
            for(int j=0; j<4; j++){
                System.out.print(Integer.toHexString(key_words.get(k)[j]));
            }
        }
    }
}
