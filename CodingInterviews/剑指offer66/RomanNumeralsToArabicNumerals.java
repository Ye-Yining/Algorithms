/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw_homework;

/**
 *
 * @author Yanni
 */
public class NumeralsPrinter {

    private static final char[][] zero = {{' ', '-', ' '}, {'|', ' ', '|'}, {' ', ' ', ' '}, {'|', ' ', '|'}, {' ', '_', ' '}};
    private static final char[][] one = {{' ', ' ', ' '}, {' ', ' ', '|'}, {' ', ' ', ' '}, {' ', ' ', '|'}, {' ', ' ', ' '}};
    private static final char[][] two = {{' ', '_', ' '}, {' ', ' ', '|'}, {' ', '_', ' '}, {'|', ' ', ' '}, {' ', '_',' '}};
    private static final char[][] three = {{' ', '_', ' '}, {' ', ' ', '|'}, {' ', '_', ' '},{' ', ' ', '|'}, {' ', '_',' '}};
    private static final char[][] four = {{' ', ' ', ' '}, {'|', ' ', '|'}, {' ', '_', ' '}, {' ', ' ', '|'}, {' ', ' ', ' '}};
    private static final char[][] five = {{' ', '_', ' '}, {'|', ' ', ' '}, {' ', '_', ' '},{' ', ' ', '|'},{' ', '_', ' '}};
    private static final char[][] six = {{' ', '_', ' '}, {'|', ' ', ' '}, {' ', '_', ' '},{'|', ' ', '|'},{' ', '_', ' '}};
    private static final char[][] seven = {' ', '_', ' '}, {' ', ' ', '|'}, {' ', ' ', ' '}, {' ', ' ', '|'},{' ', ' ', ' '}};
    private static final char[][] eight = {{' ', '_', ' '}, {'|', ' ', '|'}, {'', '_', ' '},{'|', ' ', '|'},{' ', '_', ' '}};
    private static final char[][] nine = {{' ', '_', ' '}, {'|', ' ', '|'}, {'', '_', ' '},{' ', ' ', '|'},{' ', '_', ' '}};
    private static final char[][] error = {{' ', '_', ' '}, {'|', ' ', ' '}, {'', '_', ' '},{'|', ' ', ' '},{' ', '_', ' '}};


    public char[][][] initChars(Integer value) {
        //convert integer to string
        String s = value.toString();//12
        int numOfChars = s.length();
        char[][][] chars = new int[numOfChars][3][3];
        //initialise chars array further
       for (int i = 0; i < numOfChars; i++){//no of digits
                    switch (s.charAt(i)) {
                        case '1':
                            chars[i] = one;
                            break;
                        case '2':
                            chars[i] = two;
                            break;
                        case '3':
                            chars[i] = three;
                            break;
                        case '4':
                            chars[i] = four;
                            break;
                        case '5':
                            chars[i] = five;
                            break;
                        case '6':
                            chars[i] = six;
                            break;
                        case '7':
                            chars[i] = seven;
                            break;
                        case '8':
                            chars[i] = eight;
                            break;
                        case '9':
                            chars[i] = nine;
                            break;
                        case '0':
                            chars[i] = zero;
                            break;
                }
        return chars;
    }

    public char[][][] constructNewChars(char[][][] chars, int numOfChars, int size) {
        //size > 1
        int row = 5 + (size - 1) * 2;
        int col = 3 + (size - 1); 
        char[][][] newChars = new char[numOfChars][row][col];
        //construct new chars based on old chars for displaying the digits better
        for (int i = 0; i < numOfChars; i++){
            for (int j = 0; j < 3; j++){//no. of row
                for (int k = 0; k < 3; k++){//no. of col
                    if (chars[i][j][k] == '_'){//repeat '_' twice ('_' only appears at the middle)
                        newChars[i][j][k] = '_'
                        newChars[i][j][k+1] = '_';
                    }else if (chars[i][j][k] == '|'){//repeat '|' at (current row + 1, current col) ('|' only appears at the left or right)
                        if (k == 0){
                            newChars[i][j][k] = '|';
                            // repeat the same action (size -1) times
                            for (int count = 1; count < size; count++)
                                newChars[i][j+count][k] = '|';
                        }else if (k == 2){
                            newChars[i][j][col-1] = '|';
                            // repeat the same action (size -1) times
                            for (int count = 1; count < size; count++)
                                newChars[i][j+count][col-1] = '|';
                        }
                    }
                }
            }
        }//end for loop for construction
        return newChars;
        }

        public void print(char[][][] output){
            for (int i = 0; i <output[0].length; i++){//no of rows
                for (int i = 0; j < output.length; j++){//no of digits
                    for (int k = 0; k < output[0][0].length){// no of cols
                        if (output[i][j][k] == '')
                            System.out.print(" ");
                        else
                            System.out.print(output[i][j][k]);
                    }
                }
                System.out.print("\n");
            }
        }


}
