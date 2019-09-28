package com.aranteknoloji.binder;

import android.app.Activity;

import com.aranteknoloji.annotations.internal.BindingSuffix;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Binding {

    private Binding() {
    }

    public static <T extends Activity> void bind(T activity) {
        instantiateBinder(activity);
    }

    private static <T extends Activity> void instantiateBinder(T target) {
        Class<?> targetClass = target.getClass();
        String className = targetClass.getName();

        try {
            Class<?> bindingClass = Objects.requireNonNull(targetClass
                    .getClassLoader())
                    .loadClass(className + BindingSuffix.GENERATED_CLASS_SUFFIX);
            Constructor<?> classConstructor = bindingClass.getConstructor(targetClass);
            try {
                classConstructor.newInstance(target);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException("Unable to invoke " + classConstructor, e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                if (cause instanceof Error) {
                    throw (Error) cause;
                }
                throw new RuntimeException("Unable to create instance.", cause);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find Class for " + className + BindingSuffix.GENERATED_CLASS_SUFFIX, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find constructor for " + className + BindingSuffix.GENERATED_CLASS_SUFFIX, e);
        }
    }
}
