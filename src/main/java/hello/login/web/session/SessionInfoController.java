package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session == null){
            return "세션이 없습니다.";
        }

        // 세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId());
        log.info("MaxInactiveInterval={}", session.getMaxInactiveInterval()); // 세션의 유효 시간 (초)
        log.info("CreationTime={}", new Date(session.getCreationTime())); // 세션이 생성일시
        log.info("LastAccessedTime={}", new Date(session.getLastAccessedTime())); // 세션에 최근에 접근한 시간
        log.info("isNew={}", session.isNew()); // 지금 만든 세션인지, 이미 존재하던 세션인지

        return "세션 출력";
    }
}
