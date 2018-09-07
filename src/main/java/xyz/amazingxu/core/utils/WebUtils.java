package xyz.amazingxu.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import xyz.amazingxu.core.security.model.SimpleAuthority;
import xyz.amazingxu.auShop.dto.UserContextDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/23 15:09
 */
@SuppressWarnings("unchecked")
public class WebUtils {
    /**
     * Value returned when correct
     * @param data
     * @return
     */
    public static WebResults success(Object data){
        return success(0,data);
    }

    /**
     * Value returned when correct
     * @param code
     * @param data
     * @return
     */
    public static WebResults success(int code, Object data){
        WebResults result = new WebResults();
        result.setCode(code);
        result.setMessage("OK");
        result.setData(data);
        return result;
    }

    /**
     * Value returned when error
     * @return
     */
    public static WebResults success(){
        return success(null);
    }

    /**
     * Value returned when error
     * @param code
     * @param msg
     * @return
     */
    public static WebResults error(int code, String msg){
        WebResults result = new WebResults();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static WebResults error(Exception ex){
        WebResults result = new WebResults();
        result.setCode(ex.hashCode());
        result.setMessage(ex.getMessage());
        return result;
    }

    /**
     * token解析成UserContextDTO
     * @param token
     * @return
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            try {
                Claims user = Jwts.parser()
                        .setSigningKey(SystemVars.JWT_SECRET)
                        .parseClaimsJws(token.replace(SystemVars.AUTHENTICATION_HEAD, ""))
                        .getBody();
                String userId = (String)user.get("sub");
                UserContextDTO userDto = new UserContextDTO();
                userDto.setName((String)user.get("name"));
                userDto.setUsername((String)user.get("userName"));
                userDto.setId((String)user.get("id"));

                String authStr = (String)user.get("_auth");
                List<String> auths = Arrays.asList(StringUtils.commaDelimitedListToStringArray(authStr));
                List<SimpleAuthority> listPower = new ArrayList<SimpleAuthority>();

                for(String auth : auths){
                    SimpleAuthority sa = new SimpleAuthority();
                    String[] authObj = auth.split(";");
                    sa.setType(authObj[0]);
                    sa.setUrl(authObj[1]);
                    listPower.add(sa);
                }
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(userDto, null, listPower);
                }
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    /**
     * Sequence into JsonString
     * @param webResults
     * @return
     * @throws JsonProcessingException
     */
    public static String getString(WebResults webResults) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(webResults);
    }
}
