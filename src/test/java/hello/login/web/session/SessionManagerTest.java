package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){

        // 세션 생성 (서버 -> 클라이언트)
        MockHttpServletResponse res = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, res);

        // 요청에 응답 쿠키 저장 (클라이언트 -> 서버)
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCookies(res.getCookies()); // mySessionId=sldfjwaeliflkdc...

        // 세션 조회
        Object result = sessionManager.getSession(req);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(req);
        Object expired = sessionManager.getSession(req);
        assertThat(expired).isNull();
    }
}
