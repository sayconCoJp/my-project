package controller;

import model.dao.LoginUserDao;
import model.entity.LoginUser;

public class A {
    private final LoginUserDao loginUserDao = new LoginUserDao();

    public static void main(String[] args) {
        A controller = new A();
        controller.testLogin("imai", "p");
    }

    public void testLogin(String loginId, String password) {
        LoginUser user = loginUserDao.findByLoginId(loginId);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("ログイン成功");
        } else {
            System.out.println("ログイン失敗");
        }
    }
}
