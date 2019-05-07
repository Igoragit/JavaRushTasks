package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class EditUserView implements View {

    private Controller controller;



    @Override
    public void refresh(ModelData modelData) {

        System.out.println("User to be edited:");
        StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("\t");
            stringBuilder.append("User{name='").append(modelData.getActiveUser().getName()).append("', ").append("id=").append(modelData.getActiveUser().getId()).append(", level=").append(modelData.getActiveUser().getLevel()).append("}");
            System.out.println(stringBuilder);
            stringBuilder.setLength(0);

        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }
}
