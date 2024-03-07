package org.example;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface MyBank{
    void filter(Date beforeDate, Date afterDate) ;
}