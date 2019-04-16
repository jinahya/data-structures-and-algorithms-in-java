/*
 * Copyright 2014 Jin Kwon &lt;onacit at tlab.co.kr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Sortee<T extends Comparable<T>> {

    static <T extends Comparable<T>> Sortee<T> newInstance(T[] array) {

        array = Arrays.copyOfRange(array, 0, array.length);

        return null;
    }

    static <T extends Comparable<T>> Sortee<T> newInstance(List<T> list) {

        list = new ArrayList<T>(list);

        return null;
    }

    int size();

    T get(int index);

    int compare(int index1, int index2);
}

