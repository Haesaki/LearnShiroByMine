//package com.sin.shirotutorial.doc.QuickStart;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.realm.text.IniRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Quickstart {
//
//    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
//
//
//    public static void main(String[] args) {
//        // 获取当前的的subject deprecated
////        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
////        SecurityManager securityManager = factory.getInstance();
//
//        // for this simple example quickstart, make the SecurityManager
//        // accessible as a JVM singleton.  Most applications wouldn't do this
//        // and instead rely on their container configuration or web.xml for
//        // webapps.  That is outside the scope of this simple quickstart, so
//        // we'll just do the bare minimum so you can continue to get a feel
//        // for things.
////        SecurityUtils.setSecurityManager(securityManager);
//
//        // Now that a simple Shiro environment is set up, let's see what you can do:
//
//        // The easiest way to create a Shiro SecurityManager with configured
//        // realms, users, roles and permissions is to use the simple INI config.
//        // We'll do that by using a factory that can ingest a .ini file and
//        // return a SecurityManager instance:
//
//        // Use the shiro.ini file at the root of the classpath
//        // (file: and url: prefixes load from files and urls respectively):
//        // 将shiro.ini绑定SecurityManager
//        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        defaultSecurityManager.setRealm(iniRealm);
//        // 绑定securityManager
//        SecurityUtils.setSecurityManager(defaultSecurityManager);
//
//        // get the currently executing user:
//        Subject currentUser = SecurityUtils.getSubject();
//
//        // Do some stuff with a Session (no need for a web or EJB container!!!)
//        Session session = currentUser.getSession();
//        session.setAttribute("someKey", "aValue");
//        String value = (String) session.getAttribute("someKey");
//        if (value.equals("aValue")) {
//            log.info("Retrieved the correct value! [" + value + "]");
//        }
//
//        // let's login the current user so we can check against roles and permissions:
//        if (!currentUser.isAuthenticated()) {
//            // 新建用户名 密码令牌实例
//            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
//            // 设置记住我
//            token.setRememberMe(true);
//            try {
//                // 执行登录操作
//                currentUser.login(token);
//            } catch (UnknownAccountException uae) {
//                // 未知账户操作
//                log.info("There is no user with username of " + token.getPrincipal());
//            } catch (IncorrectCredentialsException ice) {
//                // 不正确的认证
//                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
//            } catch (LockedAccountException lae) {
//                // 账户锁定错误
//                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
//                        "Please contact your administrator to unlock it.");
//            }
//            // ... catch more exceptions here (maybe custom ones specific to your application?
//            catch (AuthenticationException ae) {
//                //unexpected condition?  error?
//                ae.printStackTrace();
//            }
//        }
//
//        //say who they are:
//        //print their identifying principal (in this case, a username):
//        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
//
//        //test a role:
//        if (currentUser.hasRole("schwartz")) {
//            log.info("May the Schwartz be with you!");
//        } else {
//            log.info("Hello, mere mortal.");
//        }
//
//        // 相对粗粒度的操作
//        //test a typed permission (not instance-level)
//        if (currentUser.isPermitted("lightsaber:wield")) {
//            log.info("You may use a lightsaber ring.  Use it wisely.");
//        } else {
//            log.info("Sorry, lightsaber rings are for schwartz masters only.");
//        }
//
//        // 相对细粒度的操作
//        //a (very powerful) Instance Level permission:
//        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
//            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
//                    "Here are the keys - have fun!");
//        } else {
//            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
//        }
//
//        //all done - log out!
//        currentUser.logout();
//
//        System.exit(0);
//    }
//}