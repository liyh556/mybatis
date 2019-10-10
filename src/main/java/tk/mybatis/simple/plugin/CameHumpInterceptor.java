package tk.mybatis.simple.plugin;


import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * Mybatis Map类型下划线key转小写驼峰形式
 */
@Intercepts(
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class}
        )
)
@SuppressWarnings({"unchecked","rawtypes"})
public class CameHumpInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object method = invocation.getMethod();
        Object[] args = invocation.getArgs();
      //  Object result = invocation.proceed();
        System.out.println("target:"+target);
        System.out.println("method:"+method);
        System.out.println("args:"+args);
//        JSON json = JSONArray.fromObject(result );
//        System.out.println(json.toString());
//       return result;


        List<Object> list = (List<Object>) invocation.proceed();

        for(Object object:list){
            if(object instanceof Map){
                processMap((Map<String, Object>) object);
            }else{
                break;
            }
        }
        return list;
    }

    /**
     * 处理Map类型
     * @param map
     */
    private void processMap(Map<String,Object> map){
        Set<String> keySet = new HashSet<String>(map.keySet());
        for(String key: keySet){

            if((key.charAt(0)>='A'
                    && key.charAt(0)<='Z')
                   ||key.indexOf("_")>=0){
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelhump(key),value);
            }
        }
    }

    /**
     * 将下划线风格替换为驼峰风格
     * @param inputString
     * @return
     */
    private String underlineToCamelhump(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for(int i = 0 ; i<inputString.length();i++){
            char c = inputString.charAt(i);
            if(c=='_'){
                if(sb.length()>0){
                    nextUpperCase = true;
                }
            }else{
                if(nextUpperCase){
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                }else{
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
