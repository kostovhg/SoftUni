package com.softuni.exodia.services;

import com.softuni.exodia.domain.entities.Document;
import com.softuni.exodia.domain.models.service.DocumentServiceModel;
import com.softuni.exodia.repository.DocumentRepository;
import com.softuni.exodia.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl extends BaseService<DocumentServiceModel> implements DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    protected DocumentServiceImpl(MapperUtil mapperUtil, DocumentRepository documentRepository) {
        super(mapperUtil);
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentServiceModel create(DocumentServiceModel documentServiceModel) {
        try {
            Document document = this.mapper.map(documentServiceModel, Document.class);
            this.documentRepository.saveAndFlush(document);
            return this.mapper.map(document, DocumentServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DocumentServiceModel findBy(String parameter, String value) {
        Document document = null;
        switch (parameter) {
            case "id":
                document = this.documentRepository.findById(value).orElse(null);
                break;
            case "title":
                document = this.documentRepository.findByTitle(value).orElse(null);
                break;
        }

        return this.mapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        return this.mapper.map(this.documentRepository.findAll(), DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel print(String id) {

        try {
            DocumentServiceModel document = this.mapper
                    .map(this.documentRepository.findById(id).orElse(null),
                            DocumentServiceModel.class);

            this.documentRepository.deleteById(id);
            return document;
        } catch (Exception e) {
            throw new IllegalArgumentException("There is no entity with that id");
        }
    }
}
