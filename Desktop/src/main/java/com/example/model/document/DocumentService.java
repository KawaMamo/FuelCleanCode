package com.example.model.document;

import com.example.model.Service;
import org.example.contract.request.create.CreateDocumentRequest;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.model.Document;

import java.util.List;


public class DocumentService implements Service<Document, CreateDocumentRequest, UpdateDocumentRequest> {

    private static final DocumentService INSTANCE = new DocumentService();

    public static DocumentService getInstance() {return INSTANCE;}


    @Override
    public List<Document> getItems(Integer page, Integer size) {
        return null;
    }

    @Override
    public Document addItem(CreateDocumentRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return null;
    }

    @Override
    public Document getItem(Long id) {
        return null;
    }

    @Override
    public Document delete(Long id) {
        return null;
    }

    @Override
    public Document editItem(UpdateDocumentRequest updateRequest) {
        return null;
    }
}
