package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.member.entity.Member;

import java.util.Scanner;

public class Container {
    @Getter
    @Setter
    private static Scanner sc;
    @Getter
    @Setter
    private static Member loginedMember;

    public static void init() {
        sc = new Scanner(System.in);
    }

    public static void close() {
        sc.close();
    }

}
