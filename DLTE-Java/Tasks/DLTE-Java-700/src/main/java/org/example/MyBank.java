package org.example;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface MyBank{
    void filter(String beforeDate,String afterDate) throws IOException, ClassNotFoundException, ParseException;
}