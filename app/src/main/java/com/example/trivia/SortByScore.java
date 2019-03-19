package com.example.trivia;

import java.util.Comparator;

//compares two scores with each other
public class SortByScore implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        return  Integer.parseInt(o2.getScore()) - Integer.parseInt(o1.getScore());
    }
}
