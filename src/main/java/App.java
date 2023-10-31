import article.controller.ArticleController;
import article.entity.Article;
import article.entity.Member;
import member.controller.MemberController;
import system.SystemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Member loginedMember = null;
    Scanner sc = new Scanner(System.in);
    public void run() {
        ArticleController articleController = new ArticleController(sc, loginedMember);
        MemberController memberController = new MemberController(sc, loginedMember);
        SystemController systemController = new SystemController();

        memberController.init();

        System.out.println("== 명언앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine().trim();
            switch (command) {
                case "종료":
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
                    articleController.create(sc);
                    break;
                case "목록":
                    articleController.list(sc);
                    break;
                case "삭제":
                    articleController.remove(sc);
                    break;
                case "수정":
                    articleController.modify(sc);
                    break;
            }

        }
        sc.close();
        System.out.println("== 프로그램 종료 ==");
    }
}