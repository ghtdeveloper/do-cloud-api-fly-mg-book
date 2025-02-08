package com.vortechgroup.queen.skies.utils;

@FunctionalInterface
public interface TransformFrom<F,T>{
    T from(F f);
}

