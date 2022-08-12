package own.login.sessionlogin.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private static final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse resp) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        resp.addCookie(cookie);
    }

    public Object getSession(HttpServletRequest req) {
        Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public void expire(HttpServletRequest req) {
        Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest req, String cookieName) {
        if (req.getCookies() == null) {
            return null;
        }

        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
