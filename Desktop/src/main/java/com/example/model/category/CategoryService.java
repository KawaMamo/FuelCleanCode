package com.example.model.category;

import com.example.model.Service;
import com.example.model.category.response.CategoryResponseEntity;
import org.example.contract.request.create.CreateCategoryRequest;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.model.Category;
import org.example.model.Group;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CategoryService implements Service<Category, CreateCategoryRequest, UpdateCategoryRequest> {

    private final static CategoryService INSTANCE = new CategoryService();

    public static CategoryService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Category> getItems(Integer page, Integer size) {
        String getUrl;
        List<Category> categoryList = null;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final Category[] categories = gson.fromJson(stringHttpResponse.body(),
                    Category[].class);
            categoryList = List.of(categories);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final CategoryResponseEntity categoryResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    CategoryResponseEntity.class);
            if(Objects.nonNull(categoryResponseEntity._embedded))
                categoryList = categoryResponseEntity._embedded.categoryList;
        }
        return categoryList;
    }

    @Override
    public Category addItem(CreateCategoryRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Category.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/category";
    }

    @Override
    public Category getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), CategoryResponseEntity.class)._embedded.categoryList.get(0);
    }

    @Override
    public Category editItem(UpdateCategoryRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Category.class);
    }
}
