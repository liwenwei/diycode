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

package com.github.florent37.expectanim.core.scale;

import android.view.View;

/**
 * Created by florentchampigny on 20/02/2017.
 */
public class ScaleAnimExpectationSameWidthAs extends ScaleAnimExpectationViewDependant {

    public ScaleAnimExpectationSameWidthAs(View otherView, Integer gravityHorizontal, Integer gravityVertical) {
        super(otherView, gravityHorizontal, gravityVertical);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        final int viewToMoveWidth = viewToMove.getWidth();

        final float otherViewWidth = viewCalculator.finalWidthOfView(otherView);

        if (otherViewWidth == 0 || viewToMoveWidth == 0f) {
            return 0f;
        }
        return otherViewWidth / viewToMoveWidth;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        if(keepRatio){
            return getCalculatedValueScaleX(viewToMove);
        }
        return null;
    }
}
