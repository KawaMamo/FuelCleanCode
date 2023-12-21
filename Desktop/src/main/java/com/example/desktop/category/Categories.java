package com.example.desktop.category;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.category.CategoryService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Category;

import java.util.List;
import java.util.Objects;

public class Categories implements TableController {

    private static ObservableList<Category> categories;
    public static Category selectedCategory;

    private final CategoryService categoryService = CategoryService.getInstance();

    @FXML
    private TextField page;

    @FXML
    private TableView<Category> tableTbl;
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Category, t1) -> selectedCategory = t1);
    }

    @FXML
    void add() {
        AddCategory.formType = FormType.CREATE;
        AddCategory.controller = this;
        Modal.start(this.getClass(), "addCategory.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = categoryService.getEndPoint();
        DeleteConfirmation.selected = selectedCategory.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddCategory.formType = FormType.UPDATE;
        AddCategory.controller = this;
        Modal.start(this.getClass(), "addCategory.fxml");
    }

    @FXML
    void pageDown() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())-1));
        loadData();
    }

    @FXML
    void pageUp() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())+1));
        loadData();
    }

    @FXML
    void search() {
        AddCategory.formType = FormType.GET;
        AddCategory.controller = this;
        Modal.start(this.getClass(), "addCategory.fxml");
    }



    private void setTable() {
        TableColumn<Category, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<Category, String> catCol = new TableColumn<>("الفئة");
        catCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPriceCategory().getName()));

        TableColumn<Category, String> materialCol = new TableColumn<>("المادة");
        materialCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMaterial().getName()));

        TableColumn<Category, String> priceCol = new TableColumn<>("السعر");
        priceCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice().getAmount()+" "+data.getValue().getPrice().getCurrency()));

        TableColumn<Category, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<Category, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idColumn, catCol, materialCol,priceCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(categories);
    }

    @Override
    public void removeData() {
        categories.remove(selectedCategory);
        loadData();
    }

    @Override
    public void addData(Object object) {
        categories.add((Category) object);
        loadData();
    }

    @Override
    public void loadData() {
        List<Category> items;
        if (Objects.isNull(query)) {
            items = categoryService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = categoryService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            categories = FXCollections.observableArrayList(items);
        else categories = null;
        tableTbl.setItems(categories);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
