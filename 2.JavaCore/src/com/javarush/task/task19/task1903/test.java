package com.javarush.task.task19.task1903;

/**
 * Created by Gor_I on 14.03.2017.
 */
public class test {

    public static void main(String[] args) {
        Solution.IncomeData incomeData = new Solution.IncomeData() {
            @Override
            public String getCountryCode() {
                return "RU";
            }

            @Override
            public String getCompany() {
                return "JavaRush Ltd.";
            }

            @Override
            public String getContactFirstName() {
                return "Ivan";
            }

            @Override
            public String getContactLastName() {
                return "Ivanov";
            }

            @Override
            public int getCountryPhoneCode() {
                return 38;
            }

            @Override
            public int getPhoneNumber() {
                return 67;
            }
        };



        Solution.Contact contact = new Solution.IncomeDataAdapter(incomeData);
        System.out.println(contact.getPhoneNumber());
        System.out.println(contact.getName());

        Solution.Customer customer = new Solution.IncomeDataAdapter(incomeData);
        System.out.println(customer.getCompanyName());
        System.out.println( customer.getCountryName());
    }

}
