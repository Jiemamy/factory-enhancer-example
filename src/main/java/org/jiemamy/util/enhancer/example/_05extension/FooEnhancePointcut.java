/*
 * Copyright 2009 Jiemamy Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.util.enhancer.example._05extension;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

import org.jiemamy.utils.enhancer.InvocationPointcut;

/**
 * {@link Factory#newFoo()}の拡張位置を指定するポイントカット。
 * {@link FooEnhancer}は{@code new Foo()}によるインスタンス生成をフックするため、
 * {@link Foo#Foo()}を対象とする。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class FooEnhancePointcut implements InvocationPointcut {
    
    public boolean isTarget(CtClass self, CtBehavior behavior) {
        if (self.getName().equals(Foo.class.getName()) == false) {
            return false;
        }
        if ((behavior instanceof CtConstructor) == false) {
            return false;
        }
        try {
            return behavior.getParameterTypes().length == 0;
        }
        catch (NotFoundException e) {
            return false;
        }
    }
}
