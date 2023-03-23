package com.example.zlatik.repository;

import com.example.zlatik.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {
    private final static String fileName = "D:\\Выполненные работы\\2 курс\\4 семестр\\Ознакомительная практика 2 курс\\zlatik\\src\\main\\resources\\products.json";
    private Gson gson;

    private Comparator<Product> idComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public ProductRepository(Gson gson) {
        this.gson = gson;
    }
    @Async
    private List<Product> loadData() {
        var list = new ArrayList<Product>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Product>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Product objects have been read from " + fileName + " file.");
            list.sort(idComparator);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    private void writeData(List<Product> products) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(products, fileWriter);
            fileWriter.close();
            System.out.println("Product objects have been saved to " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public Product getByID(Long id) {
        List<Product> products = loadData();
        var buff = products.stream().filter(x->x.getId()==Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<Product> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(Product x) {
        List<Product> myClassList = loadData();
        if (myClassList.isEmpty()) {
            x.setId(Long.valueOf(1));
        } else {
            x.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(x);
        writeData(myClassList);
    }
    @Async
    public List<Product> findAll() {
        List<Product> myClassList = loadData();
        return myClassList;
    }
    @Async
    public Product update(Product product) {
        List<Product> products = loadData();
        if (!products.isEmpty() && product != null) {
            var id = 0;
            for (var item : products) {
                if (item.getId() == product.getId()) {
                    break;
                }
                id = id + 1;
            }
            products.set(
                    id,
                    product);
        }
        writeData(products);
        products = loadData();
        return products.stream().filter(x -> (x.getId()) == product.getId()).toList().get(0);
    }
}