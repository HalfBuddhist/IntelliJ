package com.lqw.common;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 路径获取功能类
 */
public class WebPath {

    /**
     * 获取项目根目录的绝对路径
     *
     * @return F:\TongJianpeng\J2EEUtil
     */
    public static String getAbsolutePathWithProject() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取项目所在盘符
     */
    public static String getDriverPathWithProject() {
        return new File("/").getAbsolutePath();
    }

    /**
     * 获取项目根目录的绝对路径
     * web
     *
     * @return 项目根目.例如<br/>
     * F:\tomcat\webapps\J2EEUtil\
     */
    public static String getAbsolutePathWithWebProject(
            HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获取项目根目录下的指定目录的绝对路径
     * web
     *
     * @param 项目根目下的指定目录 .例如:/login/
     * @return 项目根目下的指定目录.例如:<br/>
     * F:\tomcat\webapps\J2EEUtil\login\
     */
    public static String getAbsolutePathWithWebProject(HttpServletRequest request, String path) {
        return request.getSession().getServletContext().getRealPath(path);
    }

    /**
     * 获取项目根目录的绝对路径
     *
     * @return 项目根目.例如<br/>
     * F:\tomcat\webapps\J2EEUtil\
     */
    public static String getAbsolutePathWithWebProject(ServletContext context) {
        return context.getRealPath("/");
    }

    /**
     * 获取项目根目录下的指定目录的绝对路径
     *
     * @param 项目根目下的指定目录 .例如:/login/
     * @return 项目根目下的指定目录.例如:<br/>
     * F:\tomcat\webapps\J2EEUtil\login\
     */
    public static String getAbsolutePathWithWebProject(ServletContext context, String path) {
        return context.getRealPath(path);
    }

    /**
     * 获取项目classpath目录的绝对路径
     * 是src所以根目录，即路径中不包括有包名的，或者说是包所在的上层目录。
     *
     * @return classes目录的绝对路
     * file:/F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/
     * 使用URL.getPath()来获得字符串所表示的路径
     */
    public static URL getAbsolutePathWithClass() {
        return WebPath.class.getResource("/");
    }

    /**
     * 获取项目classPath目录下的指定目录的绝对路径
     * 即获得指定包名后的路径
     *
     * @param path classes目录下的指定目录.比如:/com/, 注意如果path是以/开始则从src目录下，
     *             否则从该WebPath类所在的目录开始找。
     * @return file:/F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/com/
     * 使用URL.getPath()来获得字符串所表示的路径
     */
    public static URL getAbsolutePathWithClass(String path) {
        return WebPath.class.getResource(path);
    }

    /**
     * 对应于上面的方法，只不过将文件的路径通过getResourceAsStream转化为输入流。
     * @param path 同上
     * @return is
     */
    public static InputStream getAbsolutePathISWithClass(String path){
        return WebPath.class.getResourceAsStream(path);
    }
    /**
     * 获取指定类文件的目录的绝对路径, 路径中包括有包名。
     *
     * @param clazz - 类
     * @return 类文件的绝对路径.例如:<br/>
     * 包com.Aries.Util.Web下的Main.java
     * 路径
     * file: F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/com/Aries/Util/Web/
     * 使用URL.getPath()来获得字符串所表示的路径
     */
    public static URL getAbsolutePathWithClass(Class clazz) {
        return clazz.getResource("");
    }


    /**
     * get server  web root path
     *
     * @param request
     * @return
     */
    public static String getServerWebRootPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    public static void main(String[] argv) {
        System.out.println(getAbsolutePathWithClass("/com/lqw/common/Utility.class").getPath());
        System.out.println(getAbsolutePathWithClass());
        System.out.println(getAbsolutePathWithClass(WebPath.class));
//		System.out.println(getDriverPathWithProject());
    }
}