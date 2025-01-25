package example;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class testExcelExample {
    @Test
    public  void testExcel( ) throws IOException {

        // TODO Auto-generated method stub


        exceldata  d=new exceldata();
        ArrayList data=d.getData("Add Profile");

        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
        System.out.println(data.get(3));



    }

}
