package com.javafx.pos;

import org.hibernate.Session;

public class Main {
    public static void  main(String[] args)
    {
        System.out.println("Main");
        loadDB();
    }

    private static void loadDB()
    {
        Session s = SingletonDatabase.getSession();
    }
}
