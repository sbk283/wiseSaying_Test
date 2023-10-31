package org.example;

import org.example.article.controller.ArticleController;
import org.example.member.controller.MemberController;
import org.example.member.entity.Member;
import org.example.system.SystemController;

public class App {
    Member loginedMember = null;
    public void run() {
        ArticleController articleController = new ArticleController();
        MemberController memberController = new MemberController(loginedMember);
        SystemController systemController = new SystemController();

        memberController.init();

        System.out.println("== 명언앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "종료":
                    systemController.exit();
                    return;
                case "회원가입":
                    memberController.registor();
                    break;
                case "로그인":
                    memberController.login();
                    break;
                case "로그아웃":
                    memberController.logout();
                    break;
                case "등록":
                    articleController.create();
                    break;
                case "목록":
                    articleController.list();
                    break;
                case "삭제":
                    articleController.remove();
                    break;
                case "수정":
                    articleController.modify();
                    break;
            }
        }
    }
}
