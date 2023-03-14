package com.example.zlatik.repository;
import com.example.zlatik.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JSONRepo {
    private final static String fileName = "D:\\Выполненные работы\\2 курс\\4 семестр\\Ознакомительная практика 2 курс\\zlatik\\product.xml";
    public static Product getByID(Long myClassId) {
        List<Product> myClassList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Gson gson = new Gson();
            myClassList = gson.fromJson(bufferedReader, new TypeToken<List<Product>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Product objects have been read from " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int classId=-1;
        for (int i = 0; i < myClassList.size(); i++) {
            if (myClassList.get(i).getId().equals(myClassId)) {
                classId=i;
                break;
            }
        }
        return myClassList.get(classId);
    }

    public static void delete(Long myClassId) {
        List<Product> myClassList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Gson gson = new Gson();
            myClassList = gson.fromJson(bufferedReader, new TypeToken<List<Product>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Product objects have been read from " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<Product> idComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        myClassList.sort(idComparator);
        myClassList.remove(Integer.parseInt(myClassId.toString())-1);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            Gson gson = new Gson();
            gson.toJson(myClassList, fileWriter);
            fileWriter.close();
            System.out.println("Product object with id=" + myClassId + " has been removed from " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Product x, int plus){
        List<Product> myClassList= new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Gson gson = new Gson();
            myClassList = gson.fromJson(bufferedReader, new TypeToken<List<Product>>() {}.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<Product> idComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        myClassList.sort(idComparator);
        if(plus==1){
            x.setId(Long.valueOf(myClassList.size() + plus));
        }
        myClassList.add(x);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            Gson gson = new Gson();
            gson.toJson(myClassList, fileWriter);
            fileWriter.close();
            System.out.println("Product objects have been saved to " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> findAll(){
        List<Product> myClassList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Gson gson = new Gson();
            myClassList = gson.fromJson(bufferedReader, new TypeToken<List<Product>>() {}.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<Product> idComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        myClassList.sort(idComparator);
        return myClassList;
    }
}

