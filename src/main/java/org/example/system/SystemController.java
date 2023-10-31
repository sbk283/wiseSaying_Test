package org.example.system;

import org.example.Container;

public class SystemController {
    public void exit() {
        if (Container.getLoginedMember() != null) {
            System.out.println("로그아웃 처리가 완료되었습니다.");
            System.out.println("== 프로그램 종료 ==");
        } else {
            System.out.println("== 프로그램 종료 ==");
        }
    }

}
