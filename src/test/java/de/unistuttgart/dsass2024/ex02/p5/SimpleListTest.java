package de.unistuttgart.dsass2024.ex02.p5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.*;

public class SimpleListTest {


    @Test
    public void sortTest() {
        SimpleList<Integer> list = new SimpleList();
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            list.prepend(random.nextInt(1000));
    }
        System.out.println(list);
        list.sort();
        System.out.println(list);
    }

}