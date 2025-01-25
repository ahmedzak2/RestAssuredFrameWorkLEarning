package example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class exceldata {
    public ArrayList<String> getData(String testcaseName) throws IOException
    {
      /*  // we use it to save the path of Excel file to work on it and it accept it as input file streaming argument
        FileInputStream fileInputStream = new FileInputStream("D:/TESTING/tech-interview-handbook/apps/portal/prisma/salaries.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        *//*
        to know how many sheet in file excel
        then make loop to search for sheet by his name then use it to access the sheet
         *//*

        int  sheetsNumber= workbook.getNumberOfSheets();

        for (int i =0 ; i<sheetsNumber ; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Salaries")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                //to move through rows  as form 1 row to second row
                Iterator<Row> rows =sheet.iterator();
                Row firstRow =rows.next();
                // to mover between cells
              Iterator <Cell> cellIterator=  firstRow.cellIterator();
              int k =0;
              int coloumn =0;
              while (cellIterator.hasNext()){
                  Cell value= cellIterator.next();
                 if( value.getStringCellValue().equalsIgnoreCase("Role")){
                  coloumn = k;

                 }
                  k++;
              }

                System.out.println(coloumn);

                while (rows.hasNext()){
                    Row r1= rows.next();
                 if ( r1.getCell(coloumn).getStringCellValue().equalsIgnoreCase("Software Engineer")) {
                     System.out.println("first step");
                     Iterator<Cell> cv=r1.cellIterator();
                     while (cv.hasNext()){
                         System.out.println( cv.next().getNumericCellValue());
                 }
                 }

                }

            }


        }

    }*/
       // public ArrayList<String> getData(String testcaseName) throws IOException

            //fileInputStream argument
            ArrayList<String> a=new ArrayList<String>();

            FileInputStream fis=new FileInputStream("D:/TESTING/tech-interview-handbook/apps/portal/prisma/salaries.xlsx");;
            XSSFWorkbook workbook=new XSSFWorkbook(fis);

            int sheets=workbook.getNumberOfSheets();
            for(int i=0;i<sheets;i++)
            {
                if(workbook.getSheetName(i).equalsIgnoreCase("Salaries"))
                {
                    XSSFSheet sheet=workbook.getSheetAt(i);
                    //Identify Testcases coloum by scanning the entire 1st row

                    Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
                    Row firstrow= rows.next();
                    Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
                    int k=0;
                    int coloumn = 0;
                    while(ce.hasNext())
                    {
                        Cell value=ce.next();

                        if(value.getStringCellValue().equalsIgnoreCase("Role"))
                        {
                            coloumn=k;

                        }

                        k++;
                    }
                    System.out.println(coloumn);

                    ////once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
                    while(rows.hasNext())
                    {

                        Row r=rows.next();

                        if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("Software Engineer"))
                        {

                            ////after you grab purchase testcase row = pull all the data of that row and feed into test

                            Iterator<Cell>  cv=r.cellIterator();
                            while(cv.hasNext())
                            {
                                Cell c=	cv.next();
                                if(c.getCellType()== CellType.STRING)
                                {


                                    a.add(c.getStringCellValue());

                                } else{
                                    a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

                                }
                            }
                        }


                    }

                }
            }
        System.out.println("it dropped here");

        return a;

        }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub


    }


    }
