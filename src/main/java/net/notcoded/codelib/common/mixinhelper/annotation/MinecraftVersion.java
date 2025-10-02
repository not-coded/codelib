package net.notcoded.codelib.common.mixinhelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinecraftVersion {
    String[] minecraft() default "";
    boolean enforceAll() default true;
}
