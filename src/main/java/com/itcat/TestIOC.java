package com.itcat;

/**
 * 12-如何实现一个IOC容器 -- 反射
 * 反射：调用Class类中的方法，当我们获得了想要的操作的类的Class对象后，可以通过该Class类中的方法获取并查看该类中的方法和属性
 *
 * 1.谈谈SpringIOC的理解
 * 2.谈谈SpringIOC的应用场景
 * 3.谈谈如何实现IOC容器？
 *      IOC实现方式有如下两种：
 *      -1.配置文件的方式
 *          <bean>
 *              <agrs></agrs>
 *          <bean/>
 *          public class UserController{
 *              private IUserService userService;
 *              public void setUserService(IUserService userService){
 *                  this.userService = userService;
 *              }
 *          }
 *      配置文件的方式具体实现过程，涉及两件事情：
 *          --1. 解析XML文件 --> Dom4j(框架解析)
 *          --2. 调用setUserService方法，实现注入(反射，反射可以在程序运行期间获取到该类的任何东西)
 *
 *      -2.注解的方式
 *      @Controller
 *      class UserController{
 *          @Autowire
 *          private IUserService userService;
 *      }
 *      @Service
 *      class UserService implements IUserService{
 *
 *      }
 *      注解方式具体实现过程：
 *          --1. 解析类
 *               这个类是归Spring管理的(通过@Controller注解，Spring识别出属于自己管理的类)
 *               获取到类的注解信息和属性的注解信息(底层也是通过反射实现)
 *          --2. 赋值
 *               自定义注解
 *                  @Controller
 *                  @Service
 *               开发注解的解析类
 */
public class TestIOC {
}
