package com.javarush.task.task19.task1905;

/**
 * Created by Gor_I on 15.03.2017.
 */
public class test {

    public static void main(String[] args) {

       Solution.Customer customer =new Solution.Customer() {
           @Override
           public String getCompanyName() {
               return "JavaRush Ltd.";
           }

           @Override
           public String getCountryName() {
               return "Ukraine";
           }
       };

       Solution.Contact contact = new Solution.Contact() {
           @Override
           public String getName() {
               return "Ivanov, Ivan";
           }

           @Override
           public String getPhoneNumber() {
               return "+38(050)123-45-67";
           }
       };


        Solution.RowItem rowItem = new Solution.DataAdapter(customer,contact);
        System.out.println(rowItem.getCompany());
        System.out.println(rowItem.getContactFirstName());
        System.out.println(rowItem.getContactLastName());
        System.out.println(rowItem.getDialString());
        System.out.println(rowItem.getCountryCode());
    }
}
