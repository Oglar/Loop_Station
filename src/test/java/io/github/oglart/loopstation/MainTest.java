package io.github.oglart.loopstation;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void itShouldStartWithoutErrors() {
        System.out.println("Test starting");

        String[] args = new String[]{};
        Main.main(args);

        System.out.println("Test stopping");
    }

}