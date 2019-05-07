package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

public class MainModel implements Model {

    private ModelData modelData=new ModelData();
    private UserService userService= new UserServiceImpl();


    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUserById(long userId) {

        this.modelData.setActiveUser(userService.getUsersById(userId));

    }

    @Override
    public void loadUsers() {
        //list
        this.modelData.setDisplayDeletedUserList(false);

        this.modelData.setUsers(userService.getUsersBetweenLevels(1, 100));
    }

    @Override
    public void loadDeletedUsers() {
        this.modelData.setDisplayDeletedUserList(true);
        this.modelData.setUsers(userService.getAllDeletedUsers());
    }
}
