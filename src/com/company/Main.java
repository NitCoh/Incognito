package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> setOfQI = new ArrayList<>();
        setOfQI.add(3);
        setOfQI.add(7);
        setOfQI.add(14);
        Incognito inc = new Incognito("data.csv", setOfQI,90,3);
    }
}
