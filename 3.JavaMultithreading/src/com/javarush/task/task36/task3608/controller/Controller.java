package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Controller {

    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers(){
        this.model.loadUsers();
        this.usersView.refresh(this.model.getModelData());
    }

    public void setUsersView(UsersView usersView) {

        this.usersView = usersView;
    }

    public void onShowAllDeletedUsers() {

        this.model.loadDeletedUsers();
       // this.editUserView.refresh(this.model.getModelData());
        this.usersView.refresh(this.model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        this.model.loadUserById(userId);
        this.editUserView.refresh(this.model.getModelData());
    }
}
