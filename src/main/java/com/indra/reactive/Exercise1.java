package com.indra.reactive;

import java.util.List;
import java.util.stream.Collectors;

public class Exercise1 {

    public static void main(String[] args) {
        printNumbersFromIntStream();
        printNumbersLT5FromIntStream();
        printNumbersGT5FromIntStream();
        printNumbersGT5FromIntStreamAndMinusOneWhenNotFound();
        printFirstNameFromUserStream();
        printFirstNameFromUserStreamHavingIDMatch();
    }

    // Print all numbers the intNumbersStream stream
    public static void printNumbersFromIntStream() {
        List<Integer> list = StreamSources.intNumbersStream().collect(Collectors.toList());
        System.out.println("Print all numbers the intNumbersStream stream => " + list);
    }

    // Print numbers from intNumberStream that are less than 5
    public static void printNumbersLT5FromIntStream() {
        List<Integer> list = StreamSources.intNumbersStream().filter(i -> i < 5).collect(Collectors.toList());
        System.out.println("Print numbers from intNumberStream that are less than 5 => " + list);
    }

    // Print the second and third numbers in intNumberStream that's greater than 5
    public static void printNumbersGT5FromIntStream() {
        List<Integer> list = StreamSources.intNumbersStream().filter(i -> i > 5)
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Print the second and third numbers in intNumberStream that are greater than 5 => " + list);
    }

    // Print the first number in intNumberStream that is greater than 5
    // If nothing is found then print -1
    public static void printNumbersGT5FromIntStreamAndMinusOneWhenNotFound() {
        Integer number = StreamSources.intNumbersStream().filter(i -> i > 5)
                .findFirst()
                .orElse(-1);
        System.out.println("Print the first number in intNumberStream that is greater than 5, If nothing is found then print -1 => " + number);
    }

    // Print first names of all users in userStream
    public static void printFirstNameFromUserStream() {
        List<String> list = StreamSources.userStream().map(User::getFirstName).collect(Collectors.toList());
        System.out.println("Print first names of all users in userStream => " + list);
    }

    // Print first names in userStream for users that have IDs from intNumberStream
    public static void printFirstNameFromUserStreamHavingIDMatch() {
        List<String> list = StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getUserId() == id))
                .map(user -> user.getFirstName())
                .collect(Collectors.toList());
        System.out.println("1st way to Print first names in userStream for users that have IDs from intNumberStream => " + list);

        list =
                StreamSources.userStream()
                        .filter(user -> StreamSources.intNumbersStream().anyMatch(i -> i == user.getUserId()))
                        .map(u -> u.getFirstName())
                        .collect(Collectors.toList());
        System.out.println("2nd way to Print first names in userStream for users that have IDs from intNumberStream => " + list);
    }
}
