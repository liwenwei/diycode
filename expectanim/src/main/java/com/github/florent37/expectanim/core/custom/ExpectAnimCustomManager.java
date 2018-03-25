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

package com.github.florent37.expectanim.core.custom;

import android.animation.Animator;
import android.view.View;

import com.github.florent37.expectanim.ViewCalculator;
import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnimCustomManager extends ExpectAnimManager {

    private final List<Animator> animations;

    public ExpectAnimCustomManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
        this.animations = new ArrayList<>();
    }

    @Override
    public void calculate() {

        for (AnimExpectation animExpectation : animExpectations) {
            if (animExpectation instanceof CustomAnimExpectation) {
                final CustomAnimExpectation expectation = (CustomAnimExpectation) animExpectation;

                expectation.setViewCalculator(viewCalculator);

                final Animator animator = expectation.getAnimator(viewToMove);
                if (animator != null) {
                    animations.add(animator);
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        return animations;
    }
}
