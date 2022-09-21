package com.indra.reactive;

import java.io.IOException;

public class Exercise4 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumbersMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(number -> System.out.println(number));

        // Get the value from the Mono into a integer variable
        Integer number = ReactiveSources.intNumberMono().block();

        System.out.println("Press a key to end");
        System.in.read();

    }
}
