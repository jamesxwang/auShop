package xyz.amazingxu.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
