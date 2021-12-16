package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static ArrayList<int[]> key_words = new ArrayList<int[]>();

    public static int[][] lookup_table = {
            {0x63,  0x7c,   0x77,   0x7b,   0xf2,   0x6b,   0x6f,   0xc5,   0x30,   0x01,   0x67,   0x2b,   0xfe,   0xd7,   0xab,   0x76},
            {0xca,  0x82,   0xc9,   0x7d,   0xfa,   0x59,   0x47,   0xf0,   0xad,   0xd4,   0xa2,   0xaf,   0x9c,   0xa4,   0x72,   0xc0},
            {0xb7,	0xfd,	0x93,	0x26,	0x36,	0x3f,	0xf7,	0xcc,	0x34,	0xa5,	0xe5,	0xf1,	0x71,	0xd8,	0x31,	0x15},
            {0x04,	0xc7,	0x23,	0xc3,	0x18,	0x96,	0x05,	0x9a,	0x07,   0x12,	0x80,	0xe2,	0xeb,	0x27,	0xb2,	0x75},
            {0x09,	0x83,	0x2c,	0x1a,	0x1b,	0x6e,	0x5a,	0xa0,	0x52,	0x3b,	0xd6,	0xb3,	0x29,	0xe3,	0x2f,	0x84},
            {0x53,	0xd1,	0x00,	0xed,	0x20,	0xfc,	0xb1,	0x5b,	0x6a,	0xcb,	0xbe,	0x39,	0x4a,	0x4c,	0x58,	0xcf},
            {0xd0,	0xef,	0xaa,	0xfb,	0x43,	0x4d,	0x33,	0x85,	0x45,	0xf9,	0x02,	0x7f,	0x50,	0x3c,	0x9f,	0xa8},
            {0x51,	0xa3,	0x40,	0x8f,	0x92,	0x9d,	0x38,	0xf5,	0xbc,	0xb6,	0xda,	0x21,	0x10,	0xff,	0xf3,	0xd2},
            {0xcd,	0x0c,	0x13,	0xec,	0x5f,	0x97,	0x44,	0x17,	0xc4,	0xa7,	0x7e,	0x3d,	0x64,	0x5d,	0x19,	0x73},
            {0x60,	0x81,	0x4f,	0xdc,	0x22,	0x2a,	0x90,	0x88,	0x46,	0xee,	0xb8,	0x14,	0xde,	0x5e,	0x0b,	0xdb},
            {0xe0,	0x32,	0x3a,	0x0a,	0x49,	0x06,	0x24,	0x5c,	0xc2,	0xd3,	0xac,	0x62,	0x91,	0x95,	0xe4,	0x79},
            {0xe7,	0xc8,	0x37,	0x6d,	0x8d,	0xd5,	0x4e,	0xa9,	0x6c,	0x56,	0xf4,	0xea,	0x65,	0x7a,	0xae,	0x08},
            {0xba,	0x78,	0x25,	0x2e,	0x1c,	0xa6,	0xb4,	0xc6,	0xe8,	0xdd,	0x74,	0x1f,	0x4b,	0xbd,	0x8b,	0x8a},
            {0x70,	0x3e,	0xb5,	0x66,	0x48,	0x03,	0xf6,	0x0e,	0x61,	0x35,	0x57,	0xb9,	0x86,	0xc1,	0x1d,	0x9e},
            {0xe1,	0xf8,	0x98,	0x11,	0x69,	0xd9,	0x8e,	0x94,	0x9b,	0x1e,	0x87,	0xe9,	0xce,	0x55,	0x28,	0xdf},
            {0x8c,	0xa1,	0x89,	0x0d,	0xbf,	0xe6,	0x42,	0x68,	0x41,	0x99,	0x2d,	0x0f,	0xb0,	0x54,	0xbb,	0x16},
    };
    public static int[][] reverse_lookup_table = {
            {0x52,	0x09,	0x6a,	0xd5,	0x30,	0x36,	0xa5,	0x38,	0xbf,	0x40,	0xa3,	0x9e,	0x81,	0xf3,	0xd7,	0xfb},
            {0x7c,	0xe3,	0x39,	0x82,	0x9b,	0x2f,	0xff,	0x87,	0x34,	0x8e,	0x43,	0x44,	0xc4,	0xde,	0xe9,	0xcb},
            {0x54,	0x7b,	0x94,	0x32,	0xa6,	0xc2,	0x23,	0x3d,	0xee,	0x4c,	0x95,	0x0b,	0x42,	0xfa,	0xc3,	0x4e},
            {0x08,	0x2e,	0xa1,	0x66,	0x28,	0xd9,	0x24,	0xb2,	0x76,	0x5b,	0xa2,	0x49,	0x6d,	0x8b,	0xd1,	0x25},
            {0x72,	0xf8,	0xf6,	0x64,	0x86,	0x68,	0x98,	0x16,	0xd4,	0xa4,	0x5c,	0xcc,	0x5d,	0x65,	0xb6,	0x92},
            {0x6c,	0x70,	0x48,	0x50,	0xfd,	0xed,	0xb9,	0xda,	0x5e,	0x15,	0x46,	0x57,	0xa7,	0x8d,	0x9d,	0x84},
            {0x90,	0xd8,	0xab,	0x00,	0x8c,	0xbc,	0xd3,	0x0a,	0xf7,	0xe4,	0x58,	0x05,	0xb8,	0xb3,	0x45,	0x06},
            {0xd0,	0x2c,	0x1e,	0x8f,	0xca,	0x3f,	0x0f,	0x02,	0xc1,	0xaf,	0xbd,	0x03,	0x01,	0x13,	0x8a,	0x6b},
            {0x3a,	0x91,	0x11,	0x41,	0x4f,	0x67,	0xdc,	0xea,	0x97,	0xf2,	0xcf,	0xce,	0xf0,	0xb4,	0xe6,	0x73},
            {0x96,	0xac,	0x74,	0x22,	0xe7,	0xad,	0x35,	0x85,	0xe2,	0xf9,	0x37,	0xe8,	0x1c,	0x75,	0xdf,	0x6e},
            {0x47,	0xf1,	0x1a,	0x71,	0x1d,	0x29,	0xc5,	0x89,	0x6f,	0xb7,	0x62,	0x0e,	0xaa,	0x18,	0xbe,	0x1b},
            {0xfc,	0x56,	0x3e,	0x4b,	0xc6,	0xd2,	0x79,	0x20,	0x9a,	0xdb,	0xc0,	0xfe,	0x78,	0xcd,	0x5a,	0xf4},
            {0x1f,	0xdd,	0xa8,	0x33,	0x88,	0x07,	0xc7,	0x31,	0xb1,	0x12,	0x10,	0x59,	0x27,	0x80,	0xec,	0x5f},
            {0x60,	0x51,	0x7f,	0xa9,	0x19,	0xb5,	0x4a,	0x0d,	0x2d,	0xe5,	0x7a,	0x9f,	0x93,	0xc9,	0x9c,	0xef},
            {0xa0,	0xe0,	0x3b,	0x4d,	0xae,	0x2a,	0xf5,	0xb0,	0xc8,	0xeb,	0xbb,	0x3c,	0x83,	0x53,	0x99,	0x61},
            {0x17,	0x2b,	0x04,	0x7e,	0xba,	0x77,	0xd6,	0x26,	0xe1,	0x69,	0x14,	0x63,	0x55,	0x21,	0x0c,	0x7d},
    };
    public static int[][] mix_array =new int[][]{{2,3,1,1},{1,2,3,1},{1,1,2,3},{3,1,1,2}};
    public static int[][] inverse_mix_array =new int[][]{{0xe,0xb,0xd,0x9},{0x9,0xe,0xb,0xd},{0xd,0x9,0xe,0xb},{0xb,0xd,0x9,0xe}};

    public static void printarr(int[][] array){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(String.format("%x", array[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        File key_file = new File("key_file.txt");
        Scanner scan = new Scanner(key_file);

        //key_length = args[0];

        int key_length = 128;

        //encrypt(scan, key_length);


        decrypt(scan, key_length);

        


    }

    public static void encrypt(Scanner scan, int key_length){    
        String key = scan.nextLine();


        int word = key_length/32;
        int number_of_hex = key_length/4;
        int round = 0;
        if(key_length==128){
            round = 10;
        }
        else if(key_length==192){
            round = 12;
        }
        else if(key_length==256){
            round = 14;
        }

        int[][] init_vectors = new int[word][4];

        for(int i = 0; i< word; i++){
            key_words.add(init_vectors[i]);
        }

        for (int i = 0; i < number_of_hex; i = i + 2) {  //dividing key to the words, words to the bytes
            String hex = key.substring(i, i + 2);
            int hx = Integer.parseInt(hex, 16);
            key_words.get(i / 8)[(i / 2) % 4] = hx;
        }

        int sum[][] = new int[4][4];
        int text[][] = messageBlockToArray("Two One Nine Two");
        int[][] key_array = new int[][]{init_vectors[0], init_vectors[1], init_vectors[2], init_vectors[3]};
        key_array = transpose(key_array);
        sum = addKey(text, key_array);

        for(int i=1;i<=round-1;i++){
            sum = byteSubstitution(sum);
            sum = shiftRows(sum);
            sum = mixColumns(sum);
            sum = addKey(sum,generate_key(word, i));
        }
        sum = byteSubstitution(sum);
        sum = shiftRows(sum);
        sum = addKey(sum,generate_key(word, round));
        printarr(sum);

    }

    public static void decrypt(Scanner scan, int key_length){
        String key = scan.nextLine();

        int word = key_length/32;
        int number_of_hex = key_length/4;
        int round = 0;
        if(key_length==128){
            round = 10;
        }
        else if(key_length==192){
            round = 12;
        }
        else if(key_length==256){
            round = 14;
        }

        int[][] init_vectors = new int[word][4];

        for(int i = 0; i< word; i++){
            key_words.add(init_vectors[i]);
        }

        for (int i = 0; i < number_of_hex; i = i + 2) {  //dividing key to the words, words to the bytes
            String hex = key.substring(i, i + 2);
            int hx = Integer.parseInt(hex, 16);
            key_words.get(i / 8)[(i / 2) % 4] = hx;
        }

        int sum[][] = new int[4][4];
        //int text[][] = messageBlockToArray("Two One Nine Two");
        int text[][] = {
            {0x29, 0xc3, 0x50, 0x5f},
            {0x57, 0x14, 0x20, 0xf6},
            {0x40, 0x22, 0x99, 0xb3},
            {0x1a, 0x02, 0xd7, 0x3a}
        };
        text = transpose(text);
        int[][] key_array = new int[][]{init_vectors[0], init_vectors[1], init_vectors[2], init_vectors[3]};
        key_array = transpose(key_array);

        for(int i=1; i<=round; i++){
            generate_key(word, i);
        }

        int idx = 4*round;
        int[][] round_key = new int[][]{key_words.get(idx), key_words.get(idx+1), key_words.get(idx+2), key_words.get(idx+3)};
        sum = addKey(text, transpose(round_key));

        printarr(sum);

        for(int i=round-1; i>=1; i--){

            sum = shiftRowsDec(sum);
            System.out.println("SHROW");
            printarr(sum);
            sum = byteSubstitutionDec(sum);
            System.out.println("BYTESUB");
            printarr(sum);
            idx = 4*i;
            round_key = new int[][]{key_words.get(idx), key_words.get(idx+1), key_words.get(idx+2), key_words.get(idx+3)};
            sum = addKey(sum, transpose(round_key));
            //System.out.println("ROUNDKEY");
            //printarr(transpose(round_key));
            //System.out.println("ADDKEY");
            printarr(sum);
            sum = inverseMixColumns(sum);
            System.out.println("MIXCOL");
            printarr(sum);

        }
        sum = shiftRowsDec(sum);
        sum = byteSubstitutionDec(sum);
        sum = addKey(sum, key_array);
        
        printarr(sum);

    }

    public static int [][] byteSubstitution(int [][] input){
        int [][] result = new int[4][4];

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int temp_X = input[i][j] / 16; // row axis in table
                int temp_Y = input[i][j] % 16; // column axis in table
                //System.out.println(String.format("0x%08X", input[i][j]));

                result[i][j] = lookup_table[temp_X][temp_Y];
            }
        }
        return result;
    }
    public static int [][] byteSubstitutionDec(int [][] input){
        int [][] result = new int[4][4];

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int temp_X = input[i][j] / 16; // row axis in table
                int temp_Y = input[i][j] % 16; // column axis in table

                result[i][j] = reverse_lookup_table[temp_X][temp_Y];
            }
        }
        return result;
    }
    public static int[][] addKey(int[][] text, int[][]key){
        int[][] result = new int[4][4];
        for(int i=0; i<4;i++){
            for(int j=0;j<4;j++){
                result[i][j]=text[i][j]^key[i][j];
            }
        }
        return result;
    }
    public static int [][] shiftRows(int [][] input){
        int [][] result = new int[4][4];

        // the first row is the same
        result[0][0] = input[0][0];
        result[0][1] = input[0][1];
        result[0][2] = input[0][2];
        result[0][3] = input[0][3];

        // one shift
        result[1][0] = input[1][1];
        result[1][1] = input[1][2];
        result[1][2] = input[1][3];
        result[1][3] = input[1][0];

        // two shift
        result[2][0] = input[2][2];
        result[2][1] = input[2][3];
        result[2][2] = input[2][0];
        result[2][3] = input[2][1];

        //three shift
        result[3][0] = input[3][3];
        result[3][1] = input[3][0];
        result[3][2] = input[3][1];
        result[3][3] = input[3][2];

        return result;
    }
    public static int [][] shiftRowsDec(int [][] input){
        int [][] result = new int[4][4];

        // the first row is the same
        result[0][0] = input[0][0];
        result[0][1] = input[0][1];
        result[0][2] = input[0][2];
        result[0][3] = input[0][3];

        // one shift
        result[1][0] = input[1][3];
        result[1][1] = input[1][0];
        result[1][2] = input[1][1];
        result[1][3] = input[1][2];

        // two shift
        result[2][0] = input[2][2];
        result[2][1] = input[2][3];
        result[2][2] = input[2][0];
        result[2][3] = input[2][1];

        //three shift
        result[3][0] = input[3][1];
        result[3][1] = input[3][2];
        result[3][2] = input[3][3];
        result[3][3] = input[3][0];

        return result;
    }

    // It takes 16 character long string and convert it to 4x4 array
    public static int [][] messageBlockToArray(String message){
        int [][] result = new int[4][4];

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                result[j][i] = message.charAt(i*4 + j);
            }
        }

        return result;
    }
    public static int bitwise_operation(int number,int shift){
        int sum = number;
        if(shift==0){
            return number;
        }
        if(shift==1){
            if(number<<1 >= 256){
                return ((number<<1)^(0b11011))%256;
            }
            return ((number<<1));
        }
        if(shift==2){
            sum = number<<2;
            if(sum >=512){
                sum=((sum)^(0b110110))%512;
            }
            if(sum>=256){
                return ((sum)^(0b11011))%256;
            }
            return sum;
        }
        if(shift==3){
            sum = number<<3;
            if(sum >=1024){
                sum=((sum)^(0b1101100))%1024;
            }
            if(sum >=512){
                sum=((sum)^(0b110110))%512;
            }
            if(sum>=256){
                return ((sum)^(0b11011))%256;
            }
            return sum;
        }
        return 0;
    }
    public static int times(int first,int second){
        if(first == 1){
            return second;
        }
        else if(first == 2){
            return bitwise_operation(second, 1);
        }
        else if(first==3){
            return bitwise_operation(second,1)^second;
        }
        else if(first == 9){
            return bitwise_operation(second,3)^second;
        }
        else if(first == 11){
            return bitwise_operation(second,3)^bitwise_operation(second,1)^second;
        }
        else if(first==13){
            return bitwise_operation(second,3)^bitwise_operation(second,2)^second;
        }
        else{
            return bitwise_operation(second,3)^bitwise_operation(second,2)^bitwise_operation(second,1);
        }
    }
    public static int [][] mixColumns(int[][] param){
        int temp[][] = new int[4][4];
        for(int i = 0; i<4;i++){
            for(int j=0;j<4;j++){
                temp[i][j]= times(mix_array[i][0],param[0][j])^ times(mix_array[i][1],param[1][j])^times(mix_array[i][2],param[2][j])^times(mix_array[i][3],param[3][j]);
            }
        }
        return temp;
    }

    public static int [][] inverseMixColumns(int[][] param){
        int temp[][] = new int[4][4];
        for(int i = 0; i<4;i++){
            for(int j=0;j<4;j++){
                temp[i][j]= times(inverse_mix_array[i][0],param[0][j])^ times(inverse_mix_array[i][1],param[1][j])^times(inverse_mix_array[i][2],param[2][j])^times(inverse_mix_array[i][3],param[3][j]);
            }
        }
        return temp;
    }
    
    public static int[][] transpose(int[][] matrix){
        int[][] transposed = new int[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4;j++){
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
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
        int [] result = new int[4];

        for(int i=0; i<4; i++){
            int temp_X = x[i] / 16; // row axis in table
            int temp_Y = x[i] % 16; // column axis in table

            result[i] = lookup_table[temp_X][temp_Y];
        }

        return result;
    }
    
    public static int[][] generate_key(int words, int round) { //words = 4 for 128-bit key, 6 for 192 bit key, 8 for 256 bit key

        int[] old_key_part;
        int[] old_key_part_to_modify;
        int[] r_con= {0,1,2,4,8,16,32,64, 128, 27, 54, 108, 216, 171, 77};

        for(int i = round*4; i< (round+1)*4; i++ ){
            int[] new_key_part = new int[4];
            if(i < words){
                new_key_part = key_words.get(i);
                continue;
            }
            else if(i >= words && i%words == 0){
                int[] temp_arr = new int[4];
                old_key_part_to_modify = key_words.get(i-1);
                for(int k=0; k<4; k++){
                    temp_arr[(k+3)%4] = old_key_part_to_modify[k];
                }
                temp_arr = substitude(temp_arr);
                temp_arr[0] = temp_arr[0] ^ r_con[i/words];
                old_key_part = key_words.get(i-words);
                for(int k=0; k < 4; k++){
                    new_key_part[k] = temp_arr[k] ^ old_key_part[k];
                }
            }
            else if(i >= words && words > 6 && i%words == 4){
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

        int idx = 4*round;
        int[][] round_key = new int[][]{key_words.get(idx), key_words.get(idx+1), key_words.get(idx+2), key_words.get(idx+3)};
        return transpose(round_key);
    }
}
