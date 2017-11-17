/*
 * Copyright 2017 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2017-03-08 23:56:43
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package com.example.wenwei.diycode_sdk.api.news.event;


import android.support.annotation.Nullable;

public class CreateNewsEvent extends BaseEvent<New> {
    /**
     * @param uuid 唯一识别码
     */
    public CreateNewsEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid 唯一识别码
     * @param code 网络返回码
     * @param aNew 实体数据
     */
    public CreateNewsEvent(@Nullable String uuid, @NonNull Integer code, @Nullable New aNew) {
        super(uuid, code, aNew);
    }
}
