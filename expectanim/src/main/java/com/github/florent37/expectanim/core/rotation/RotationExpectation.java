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
 * Last modified 2017-03-08 01:01:18
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package com.github.florent37.expectanim.core.rotation;

import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

/**
 * Created by florentchampigny on 18/02/2017.
 */
public abstract class RotationExpectation extends AnimExpectation {

    public abstract Float getCalculatedRotation(View viewToMove);
    public abstract Float getCalculatedRotationX(View viewToMove);
    public abstract Float getCalculatedRotationY(View viewToMove);
}
