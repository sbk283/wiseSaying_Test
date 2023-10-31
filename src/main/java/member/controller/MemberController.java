package member.controller;

import member.entity.Member;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    List<Member> memberList = new ArrayList<>();

    Scanner sc;
    Member loginedMember;

    public MemberController(Scanner sc, Member loginedMember) {
        this.sc= sc;
        this.loginedMember = loginedMember;
    }
    public void init() {
        Member member1 = new Member(1, "user1", "1234",  Util.nowDateTime());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234",  Util.nowDateTime());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234",  Util.nowDateTime());
        memberList.add(member3);
    }

    public void registor () {
        System.out.print("가입하실 아이디를 입력해주세요) ");
        String userId = sc.nextLine().trim();
        System.out.print("가입하실 비밀번호를 입력해주세요) ");
        String password = sc.nextLine().trim();
        System.out.print("비밀번호를 다시 한번 입력해주세요.) ");
        String passwordConfirm = sc.nextLine().trim();

        for (Member member : memberList) {
            if (member.getUserId().equals(userId)) {
                break;
            }
        }

        if (member != null) {
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
    }

    public void login () {
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
    }

    public void logout() {
        if (!loggedIn) {
            System.out.println("로그인 상태가 아닙니다.");
        } else {
            loggedIn = false;
            System.out.println("정상적으로 로그아웃 처리가 완료되었습니다.");
        }
    }
    private Member getMemberFindByUserId(String userId) {
        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }
}
