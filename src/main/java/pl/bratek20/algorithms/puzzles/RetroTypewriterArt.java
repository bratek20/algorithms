package pl.bratek20.algorithms.puzzles;

import java.util.Scanner;

// https://www.codingame.com/ide/puzzle/retro-typewriter-art
class RetroTypewriterArt {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String recipe = in.nextLine();

        var chunks = recipe.split(" ");
        for (var chunk : chunks) {
            printChunk(chunk);
        }
    }

    static void printChunk(String chunk) {
        if (chunk.equals("nl")) {
            System.out.println();
            return;
        }
        var chunkChars = chunk.toCharArray();
        var l = chunkChars.length;
        if (l > 2) {
            if (chunkChars[l-2] == 's' && chunkChars[l-1] == 'p') {
                printChunk(chunk, l-2, ' ');
                return;
            }
            if (chunkChars[l-2] == 'b' && chunkChars[l-1] == 'S') {
                printChunk(chunk, l-2, '\\');
                return;
            }
            if (chunkChars[l-2] == 's' && chunkChars[l-1] == 'Q') {
                printChunk(chunk, l-2, '\'');
                return;
            }
        }
        printChunk(chunk, l - 1, chunkChars[l-1]);
    }

    static void printChunk(String chunk, int numLength, char c) {
        var num = Integer.parseInt(chunk.substring(0, numLength));
        for (int i = 0; i < num; i++) {
            System.out.print(c);
        }
    }
}
