package com.acmlab.file.controller;


import com.acmlab.file.config.shrio.jwt.JwtUtils;
import com.acmlab.file.config.shrio.utils.PasswordUtils;
import com.acmlab.file.entity.User;
import com.acmlab.file.service.UserService;
import com.acmlab.file.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * 用户管理接口
 *
 * @author lixin
 * @since 2021-08-01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 登录接口
     * @param user
     * @return com.acmlab.file.utils.Result
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        String newPassword = PasswordUtils.HashMD5(user.getUsername(), user.getPassword());
        //封装用户登录数据

        //登录
        try {
            String jwtToken = JwtUtils.sign(user.getUsername(), JwtUtils.SECRET);
            return Result.ok(jwtToken);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return Result.error("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return Result.error("密码错误!");
        }
    }


    /**
     * 注册接口
     * @param user
     * @return com.acmlab.file.utils.Result
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        String result;
        try {
            result = userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok(result);
    }

    /**
     * 修改密码接口—王京
     * @param: [username,password]
     * @description 修改密码
     * @return com.acmlab.file.utils.Result
     */

    @PostMapping("/rewrite")
    public Result<String> rewite(@RequestParam String username,@RequestParam String password ) {
        String result;
        try {
            result = userService.rewite(username,password);
            return Result.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }

    }
    /**
     * 登出接口—王京
     * @param: redirectAttributes
     * @description 登出
     * @return com.acmlab.file.utils.Result
     */
    @GetMapping("/logout")
    public Result<String>logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return Result.ok();
    }
}

