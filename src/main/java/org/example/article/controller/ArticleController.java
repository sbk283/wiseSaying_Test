package org.example.article.controller;

import org.example.Container;
import org.example.article.entity.Article;
import org.example.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    List<Article> articleList = new ArrayList<>();
    long lastId = 0;

    public void create() {
        if (Container.getLoginedMember() == null) {
            System.out.println("로그인시 이용 가능한 기능입니다.");
            return;
        }

        lastId++;
        System.out.print("명언) ");
        String content = Container.getSc().nextLine().trim();
        System.out.print("작가) ");
        String author = Container.getSc().nextLine().trim();
        System.out.println(lastId + "번 게시글이 등록되었습니다.");

        Article article = new Article(lastId, content, author);
        articleList.add(article);
        }

    public void list() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println("-".repeat(30));
        for (int i = articleList.size() - 1; i >= 0; i--) {
            Article article = articleList.get(i);
            System.out.printf("%d / %s / %s\n", article.getId(), article.getContent(), article.getAuthor());
        }

    }
        public void remove () {
            System.out.print("삭제할 번호를 입력해주세요) ");
            boolean deleted = false;
            long removeId = Long.parseLong(Container.getSc().nextLine().trim());
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

    public void modify() {
        if (articleList.isEmpty()) {
                System.out.println("게시글이 없습니다.");
            } else {
                System.out.print("수정할 번호를 입력해주세요: ");
                long modifyId = Long.parseLong(Container.getSc().nextLine().trim());
                boolean modified = false;

                for (Article value : articleList) {
                    if (value.getId() == modifyId) {
                        Article article = value;
                        System.out.println("기존 명언: " + article.getContent());
                        System.out.print("새로운 명언: ");
                        String Content = Container.getSc().nextLine().trim();
                        System.out.println("기존 작가: " + article.getAuthor());
                        System.out.print("새로운 작가: ");
                        String Author = Container.getSc().nextLine().trim();

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
