package com.indra.reactive;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise3 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a list and print the list and its size
        List<Integer> numbers =  ReactiveSources.intNumbersFlux().toStream().collect(Collectors.toList());
        System.out.println("List is " + numbers);
        System.out.println("Size is " + numbers.size());

        System.out.println("Press a key to end");
        System.in.read();
    }
}
