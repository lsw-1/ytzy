package com.ludwigstralewiren.Model;

import java.util.List;

/**
 * Created by Ludwig on 01-Dec-15.
 */
public class ScoreCombos {


    public ScoreCombos() {

    }


    public int singleValuePoints(List<Integer> allvalues, int index) {
        int sum = 0;
        for (int i : allvalues
                ) {
            if (i == index + 1) {
                sum += i;
            }
        }
        return sum;
    }


    public int scorePair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        counts[d1 - 1]++;
        counts[d2 - 1]++;
        counts[d3 - 1]++;
        counts[d4 - 1]++;
        counts[d5 - 1]++;
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6 - at - 1] >= 2)
                return (6 - at) * 2;
        return 0;
    }

    public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        counts[d1 - 1]++;
        counts[d2 - 1]++;
        counts[d3 - 1]++;
        counts[d4 - 1]++;
        counts[d5 - 1]++;
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1)
            if (counts[6 - i - 1] >= 2) {
                n++;
                score += (6 - i);
            }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int fourOfAKind(int _1, int _2, int d3, int d4, int d5) {
        int[] scores;
        scores = new int[6];
        scores[_1 - 1]++;
        scores[_2 - 1]++;
        scores[d3 - 1]++;
        scores[d4 - 1]++;
        scores[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (scores[i] >= 4)
                return (i + 1) * 4;
        return 0;
    }

    public static int threeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        int[] s;
        s = new int[6];
        s[d1 - 1]++;
        s[d2 - 1]++;
        s[d3 - 1]++;
        s[d4 - 1]++;
        s[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (s[i] >= 3)
                return (i + 1) * 3;
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] scores;
        scores = new int[6];
        scores[d1 - 1] += 1;
        scores[d2 - 1] += 1;
        scores[d3 - 1] += 1;
        scores[d4 - 1] += 1;
        scores[d5 - 1] += 1;
        if (scores[0] == 1 &&
                scores[1] == 1 &&
                scores[2] == 1 &&
                scores[3] == 1 &&
                scores[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] scores;
        scores = new int[6];
        scores[d1 - 1] += 1;
        scores[d2 - 1] += 1;
        scores[d3 - 1] += 1;
        scores[d4 - 1] += 1;
        scores[d5 - 1] += 1;
        if (scores[1] == 1 &&
                scores[2] == 1 &&
                scores[3] == 1 &&
                scores[4] == 1
                && scores[5] == 1)
            return 20;
        return 0;
    }


    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] scores;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        scores = new int[6];
        scores[d1 - 1] += 1;
        scores[d2 - 1] += 1;
        scores[d3 - 1] += 1;
        scores[d4 - 1] += 1;
        scores[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (scores[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (scores[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    public static int yatzy(int... dice) {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die - 1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        int total = 0;
        total += d1;
        total += d2;
        total += d3;
        total += d4;
        total += d5;
        return total;
    }


}

