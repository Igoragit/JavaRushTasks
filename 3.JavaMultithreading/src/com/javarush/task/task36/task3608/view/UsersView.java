package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {

    private Controller controller = new Controller();



    @Override
    public void refresh(ModelData modelData) {

       if(modelData.isDisplayDeletedUserList()) System.out.println("All deleted users:");
       if(!modelData.isDisplayDeletedUserList()) System.out.println("All users:");
        StringBuilder stringBuilder = new StringBuilder();
        for (User in: modelData.getUsers()){

            stringBuilder.append("\t");
            stringBuilder.append("User{name='").append(in.getName()).append("', ").append("id=").append(in.getId()).append(", level=").append(in.getLevel()).append("}");
            System.out.println(stringBuilder);
            stringBuilder.setLength(0);
        }
        System.out.println("===================================================");

    }

    @Override
    public void setController(Controller controller) {
            this.controller=controller;
    }

    public void fireEventShowAllUsers(){
            this.controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        this.controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        this.controller.onOpenUserEditForm(id);
    }
}
