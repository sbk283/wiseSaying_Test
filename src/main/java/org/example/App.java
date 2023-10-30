package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        long id = 0;
        boolean loggedIn = false;
        boolean idExists = false;

        List<Article> articleList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();

        Member member1 = new Member("user1","1234","1234");
        memberList.add(member1);

        System.out.println("== 명언앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                break;
            } else if (command.equals("회원가입")) {
                System.out.print("가입하실 아이디를 입력해주세요) ");
                String userId = sc.nextLine().trim();
                System.out.print("가입하실 비밀번호를 입력해주세요) ");
                String password = sc.nextLine().trim();
                System.out.print("비밀번호를 다시 한번 입력해주세요.) ");
                String passwordConfirm = sc.nextLine().trim();

                for (Member member : memberList) {
                    if (member.getUserId().equals(userId)) {
                        idExists = true;
                        break;
                    }
                }

                if (idExists) {
                    System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요.");
                } else if (!password.equals(passwordConfirm)) {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                } else {
                    System.out.println(userId + "님 회원가입을 환영합니다!");
                    Member member = new Member(userId, password, passwordConfirm);
                    memberList.add(member);
                }

                Member member = new Member(userId, password, passwordConfirm);
                memberList.add(member);
            } else if (command.equals("로그인")) {
                if (loggedIn) {
                    System.out.println("이미 로그인 상태입니다.");
                } else {
                    System.out.print("아이디를 입력해주세요) ");
                    String userId = sc.nextLine().trim();
                    System.out.print("비밀번호를 입력해주세요) ");
                    String password = sc.nextLine().trim();

                    // 사용자 정보를 저장하는 리스트 또는 데이터베이스에서 사용자 정보를 가져와서 확인
                    for (Member member : memberList) {
                        if (member.getUserId().equals(userId) && member.getPassword().equals(password)) {
                            loggedIn = true;
                            break;
                        }
                    }
                    if (loggedIn) {
                        System.out.println("로그인 성공! " + userId + "님 환영합니다!");
                        // 로그인 성공 후 실행할 코드를 추가하세요.
                    } else {
                        System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인해주세요.");
                    }
                }
            } else if (command.equals("로그아웃")) {
                if (!loggedIn) {
                    System.out.println("로그인 상태가 아닙니다.");
                } else {
                    loggedIn = false;
                    System.out.println("정상적으로 로그아웃 처리가 완료되었습니다.");
                }

            } else if (command.equals("등록")) {
                if (loggedIn) {
                    System.out.print("명언) ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가) ");
                    String author = sc.nextLine().trim();
                    System.out.println((id + 1) + "번 게시글이 등록되었습니다.");
                    id++;

                    Article article = new Article(id, content, author);
                    articleList.add(article);
                } else {
                    System.out.println("로그인시 이용 가능한 기능입니다.");
                }
            } else if (command.equals("목록")) {
                if (loggedIn) {
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
                } else {
                    System.out.println("로그인시 이용 가능한 기능입니다.");
                }
            } else if (command.equals("삭제")) {
                if (loggedIn) {
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
                } else {
                    System.out.println("로그인시 이용 가능한 기능입니다.");
                }
            } else if (command.equals("수정")) {
                if (loggedIn) {
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
                } else {
                    System.out.println("로그인시 이용 가능한 기능입니다.");
                }
            }

        }
        sc.close();
        if (loggedIn) {
            loggedIn = false;
            System.out.println("정상적으로 로그아웃 처리가 완료되었습니다.");
        }
        System.out.println("프로그램 종료");
    }
}