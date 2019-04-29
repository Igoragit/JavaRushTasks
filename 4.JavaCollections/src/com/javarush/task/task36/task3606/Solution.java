package com.javarush.task.task36.task3606;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();

        for (File in: files){

            if(in.getName().contains("class")){
                byte[] b = new byte[0];
                try {
                    b = Files.readAllBytes(in.toPath());
                    hiddenClasses.add(new MyClassLoader().load(b));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyClassLoader extends ClassLoader{
        public Class<?> load(byte[] bytes) throws IOException {
            return defineClass(null, bytes, 0, bytes.length);
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for (Class clazz : hiddenClasses) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterCount() == 0){
                            constructor.setAccessible(true);
                            return ((HiddenClass) constructor.newInstance());
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}

