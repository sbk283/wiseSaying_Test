package org.example.article.controller;

import org.example.Member;
import org.example.article.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    long id = 0;
    boolean loggedIn = false;
    List<Article> articleList = new ArrayList<>();
    public void create(Scanner sc, Member loggedIn) {
        if (loggedIn == null) {
            System.out.println("로그인시 이용 가능한 기능입니다.");
        } else {
            System.out.print("명언) ");
            String content = sc.nextLine().trim();
            System.out.print("작가) ");
            String author = sc.nextLine().trim();
            System.out.println((id + 1) + "번 게시글이 등록되었습니다.");
            id++;

            Article article = new Article(id, content, author);
            articleList.add(article);
        }
    }

    public void list(Scanner sc, Member loggedIn) {
        if (loggedIn == null) {
            System.out.println("로그인시 이용 가능한 기능입니다.");
        } else {
            if (articleList.isEmpty()) {
                System.out.println("게시글이 없습니다.");
            } else {
                System.out.println("번호 / 명언 / 작가");
                System.out.println("-".repeat(30));
                for (int i = articleList.size() - 1; i >= 0; i--) {
                    Article article = articleList.get(i);
                    System.out.printf("%d / %s / %s\n", article.getId(), article.getContent(), article.getAuthor());
                }
            }
        }
    }

    public void remove(Scanner sc, Member loggedIn) {
        if (loggedIn == null) {
            System.out.println("로그인시 이용 가능한 기능입니다.");
        } else {
            if (articleList.isEmpty()) {
                System.out.println("게시글이 없습니다.");
            } else {
                System.out.print("삭제할 번호를 입력해주세요) ");
                boolean deleted = false;
                long removeId = Long.parseLong(sc.nextLine().trim());
                for (int i = 0; i < articleList.size(); i++) {
                    if (articleList.get(i).getId() == removeId) {
                        articleList.remove(i);
                        deleted = true;
                        System.out.println(removeId + "번 명언이 삭제 되었습니다.");
                        break;
                    }
                }
                if (!deleted) {
                    System.out.println("선택하신 번호의 게시물이 존재하지 않습니다.");
                }
            }
        }
    }

    public void modify(Scanner sc, Member loggedIn) {
        if (loggedIn == null) {
            System.out.println("로그인시 이용 가능한 기능입니다.");
        } else {
            if (articleList.isEmpty()) {
                System.out.println("게시글이 없습니다.");
            } else {
                System.out.print("수정할 번호를 입력해주세요: ");
                long modifyId = Long.parseLong(sc.nextLine().trim());
                boolean modified = false;

                for (Article value : articleList) {
                    if (value.getId() == modifyId) {
                        Article article = value;
                        System.out.println("기존 명언: " + article.getContent());
                        System.out.print("새로운 명언: ");
                        String Content = sc.nextLine().trim();
                        System.out.println("기존 작가: " + article.getAuthor());
                        System.out.print("새로운 작가: ");
                        String Author = sc.nextLine().trim();

                        // 명언 및 작가 정보 수정
                        article.setContent(Content);
                        article.setAuthor(Author);

                        modified = true;
                        System.out.println(modifyId + "번 명언이 수정되었습니다.");
                        break;
                    }
                }

                if (!modified) {
                    System.out.println("선택하신 번호의 게시물이 존재하지 않습니다.");
                }
            }

        }
    }
}
