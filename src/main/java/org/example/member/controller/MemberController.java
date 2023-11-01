package org.example.member.controller;

import org.example.Container;
import org.example.member.entity.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;


public class MemberController {
    List<Member> memberList = new ArrayList<>();


    Member loginedMember;
    Member member;

    public MemberController(Member loginedMember) {
        this.loginedMember = loginedMember;
    }
    public void init() {
        Member admin = new Member(1, "admin", "",  Util.nowDateTime());
        memberList.add(admin);
        Member test1 = new Member(2, "test1", "1234",  Util.nowDateTime());
        memberList.add(test1);
        Member user1 = new Member(3, "user1", "1234",  Util.nowDateTime());
        memberList.add(user1);
    }

    public void registor () {
        System.out.print("가입하실 아이디를 입력해주세요) ");
        String userId = Container.getSc().nextLine().trim();
        System.out.print("가입하실 비밀번호를 입력해주세요) ");
        String password = Container.getSc().nextLine().trim();
        System.out.print("비밀번호를 다시 한번 입력해주세요.) ");
        String passwordConfirm = Container.getSc().nextLine().trim();

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
        if (Container.getLoginedMember() != null) {
            System.out.println("이미 로그인 상태입니다.");
        } else {
            System.out.print("아이디를 입력해주세요) ");
            String userId = Container.getSc().nextLine().trim();
            System.out.print("비밀번호를 입력해주세요) ");
            String password = Container.getSc().nextLine().trim();

            Member member = this.getMemberFindByUserId(userId);
            // 사용자 정보를 저장하는 리스트 또는 데이터베이스에서 사용자 정보를 가져와서 확인
            if (member == null) {
                System.out.println("해당 회원이 존재하지 않습니다.");
                return;
            }
            if (!member.getPassword().equals(password)) {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
            Container.setLoginedMember(member);

            System.out.println("로그인 성공! " + Container.getLoginedMember().getUserId() + "님 환영합니다!");
        }
    }

    public void logout() {
        if (Container.getLoginedMember() == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }
        Container.setLoginedMember(null);
        System.out.println("정상적으로 로그아웃 처리가 완료되었습니다.");
    }
    private Member getMemberFindByUserId(String userId) {
        for (Member member : memberList) {
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }
}
