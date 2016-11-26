/*
 * Android_Common. lastModified by bobomee at 2016.5.16 10:46
 *
 * Copyright (C) 2016 bobomee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobomee.android.common.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 模拟数据工具类
 */
public class MockitoUtil {

    public static String readAssetsString(Context context, String name) {

        BufferedReader bufferedReader = null;
        InputStream inputStream = null;

        try {
            inputStream = context.getAssets().open(name);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line.trim());
            }
            return sb.toString();

        } catch (IOException e) {
            return null;
        } finally {
            IOUtil.closeQuietly(inputStream);
            IOUtil.closeQuietly(bufferedReader);
        }

    }

}
