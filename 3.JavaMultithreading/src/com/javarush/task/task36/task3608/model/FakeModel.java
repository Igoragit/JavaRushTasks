package com.javarush.task.task36.task3608.model;



import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData=new ModelData();

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelData getModelData() {

        return this.modelData;
    }

    @Override
    public void loadUsers() {

        List<User>  userList= new ArrayList<>();

        userList.add(new User("A",1,1));
        userList.add(new User("B",2,1));

        modelData.setUsers(userList);
    }
}
