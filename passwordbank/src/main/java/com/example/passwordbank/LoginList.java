package com.example.passwordbank.utilities;

import java.util.ArrayList;

import com.example.passwordbank.model.Login;

public final class LoginList extends ArrayList<Login>{

    private static final int value = 3;

    public LoginList() {}

    public LoginList(LoginList list){
        super(list);
    }

    public static LoginList getMostUsed(final LoginList loginList) {
        LoginList list = new LoginList();
        LoginList changeList = new LoginList(loginList);
        for (int i = 0; i < value; i++) {
            Login login = changeList.get(0);

            for (int j = 0; j < changeList.size(); j++) {
                if (changeList.get(j).getUseCount() > login.getUseCount() &&
                    !list.contains(changeList.get(j))) {
                    login = changeList.get(j);
                }
            }
            list.add(login);
            changeList.remove(login);
        }

        return list;
    }

    public static LoginList getOldestEdited(LoginList loginList) {
        LoginList list = new LoginList();
        LoginList changeList = new LoginList(loginList);
        for (int i = 0; i < value; i++) {
            Login login = changeList.get(0);

            for (int j = 0; j < changeList.size(); j++) {
                if (changeList.get(j).getLastEditDate().before(login.getLastEditDate()) &&
                    !list.contains(changeList.get(j))) {
                    login = changeList.get(j);
                }
            }
            list.add(login);
            changeList.remove(login);
        }
        return list;
    }
}
