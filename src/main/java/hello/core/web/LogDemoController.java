package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // @Autowired로 생성자 생성할 필요 없이, DI가 필요한
                            // 빈들을 자동으로 생성해서 멤버에 넣어주는 생성자 생성.
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerObjectProvider.getObject();
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testID");
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        return "OK";
    }
}
