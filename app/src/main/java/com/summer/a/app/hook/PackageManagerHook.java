package com.summer.a.app.hook;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PackageManagerHook {
    /**
     * 越早hook越好，推荐在Application.attachBaseContext中调用
     */
    public static void hook(final Context context) {
        try {
            //1、得到ActivityThread类
            Class<?> activityThreadClz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThreadClz.getMethod("currentActivityThread");
            //2、得到当前的ActivityThread对象
            Object activityThread = currentActivityThread.invoke(null);
            //3、得到PackageManager对象
            Method getPackageManager = activityThreadClz.getMethod("getPackageManager");
            final Object pkgManager = getPackageManager.invoke(activityThread);

            Class<?> packageManagerClz = Class.forName("android.content.pm.IPackageManager");
            //hook sPackageManager
            Field packageManagerField = activityThreadClz.getDeclaredField("sPackageManager");
            packageManagerField.setAccessible(true);
            //动态代理
            packageManagerField.set(activityThread, Proxy.newProxyInstance(context.getClassLoader(),
                    new Class[] { packageManagerClz }, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("getPackageInfo".equals(method.getName())){
                                return null;
                            }else{
                                Object result = method.invoke(pkgManager,args);
                                return result;
                            }
                        }
                    }));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
