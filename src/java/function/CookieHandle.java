package function;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

/**
 *
 * @author Lenvo
 */
public class CookieHandle {
    
    public static Cookie find(Cookie[] cookies, String name) {
        if(cookies!=null)
            for(int i=1;i<cookies.length;++i)
                if(cookies[i].getName().equals(name)) {
                    try {
                        cookies[i].setValue(URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(CookieHandle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return cookies[i];
                }
            
        return null;
    }
    
    public static Cookie set(String name, String value, int age) {
        Cookie cookie = null;
        
        try {
            cookie = new Cookie(name,URLEncoder.encode(value,"UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(age);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CookieHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cookie;
    }
    
    
}
