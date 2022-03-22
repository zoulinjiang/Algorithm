package cn.itcast.handle;

import cn.itcast.expec.DeleteErrorException;
import cn.itcast.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @Autowired
//    private LogService logService;
//    //定义方法，处理发生的异常
//    /*
//        处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有ModelAndView，String,void,对象；类型的返回值
//
//        形参：Exception，表示Controller中抛出的异常对象。
//        通过形参可以获取发生的异常信息。
//
//        @ExceptionHandler(异常的class)：表示异常的类型，当翻身此类型异常的时候，
//        由当前方法处理
//     */
//    @ExceptionHandler(value = DeleteErrorException.class)
//    public ModelAndView doNameException(Exception ex, HttpSession session){
//        //处理DeleteErrorException异常
//        //TODO
//        /*
//            异常发生处理逻辑：
//            1.需要把异常记录下来，记录到数据库，日志文件。
//                记录异常发生的时间，那个方法发生的，异常错误内容。
//            2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员。
//            3.给用户友好的提示.
//         */
//        User user = (User) session.getAttribute("user");
//        logService.addLog(user.getId(),ex.toString());
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("msg","删除异常");
//        mv.addObject("ex",ex);
//        mv.setViewName("errorJsp/deleteError");
//        return mv;
//    }
//
//
//    //处理其他异常，自定义以外，不知名的异常
//    @ExceptionHandler
//    public ModelAndView doOtherException(Exception ex, HttpSession session){
//        User user = (User)session.getAttribute("user");
//        logService.addLog(user.getId(),ex.toString());
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("msg","其他异常!");
//        mv.addObject("ex",ex);
//        mv.setViewName("errorJsp/defaultError");
//        return mv;
//    }
//}
