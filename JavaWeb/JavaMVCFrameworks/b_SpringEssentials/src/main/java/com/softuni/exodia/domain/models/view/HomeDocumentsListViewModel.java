package com.softuni.exodia.domain.models.view;

import java.util.ArrayList;
import java.util.List;

public class HomeDocumentsListViewModel {

    private List<List<DocumentViewModel>> documents;
    private int rowsCount;
    private int totalDocumentsCount;

    public HomeDocumentsListViewModel() {
    }

    public HomeDocumentsListViewModel(List<DocumentViewModel> documents) {
        this.documents = new ArrayList<>();
        this.rowsCount = ((documents.size() - 1) / 5) + 1;
        this.totalDocumentsCount = documents.size();
        for (int rowIndex = 0; rowIndex < this.rowsCount; rowIndex++) {
            List<DocumentViewModel> row = new ArrayList<>();
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                if (colIndex + (rowIndex * 5) < this.totalDocumentsCount) {
                    row.add(documents.get(colIndex + (rowIndex * 5)));
                } else {
                    row.add(null);
                }
            }
            this.documents.add(row);
        }
    }

    public List<List<DocumentViewModel>> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<List<DocumentViewModel>> documents) {
        this.documents = documents;
    }

    public int getRowsCount() {
        return this.rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getTotalDocumentsCount() {
        return this.totalDocumentsCount;
    }

    public void setTotalDocumentsCount(int totalDocumentsCount) {
        this.totalDocumentsCount = totalDocumentsCount;
    }
}
