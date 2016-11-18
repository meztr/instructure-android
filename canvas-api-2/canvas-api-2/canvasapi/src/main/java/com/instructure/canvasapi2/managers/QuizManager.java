/*
 * Copyright (C) 2016 - present Instructure, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.instructure.canvasapi2.managers;

import com.instructure.canvasapi2.AppManager;
import com.instructure.canvasapi2.StatusCallback;
import com.instructure.canvasapi2.apis.QuizAPI;
import com.instructure.canvasapi2.builders.RestBuilder;
import com.instructure.canvasapi2.builders.RestParams;
import com.instructure.canvasapi2.models.QuizQuestion;
import com.instructure.canvasapi2.models.QuizSubmissionQuestionResponse;
import com.instructure.canvasapi2.models.QuizSubmissionResponse;
import com.instructure.canvasapi2.tests.QuizManager_Test;

import java.util.List;


public class QuizManager extends BaseManager {

    private static boolean mTesting = false;

    public static void getQuizQuestions(long courseId, long quizId, StatusCallback<List<QuizQuestion>> callback) {

        if(isTesting() || mTesting) {
            QuizManager_Test.getQuizQuestions(courseId, quizId, callback);
        } else {
            RestBuilder adapter = new RestBuilder(AppManager.getConfig(), callback);
            RestParams params = new RestParams.Builder()
                    .withPerPageQueryParam(true)
                    .withShouldIgnoreToken(false)
                    .build();

            QuizAPI.getQuizQuestions(courseId, quizId, adapter, callback, params);
        }
    }

    public static void startQuizPreview(long courseId, long quizId, boolean forceNetwork, StatusCallback<QuizSubmissionResponse> callback) {

        if(isTesting() || mTesting) {
            QuizManager_Test.startQuizPreview(courseId, quizId, callback);
        } else {
            RestBuilder adapter = new RestBuilder(AppManager.getConfig(), callback);
            RestParams params = new RestParams.Builder()
                    .withPerPageQueryParam(true)
                    .withShouldIgnoreToken(false)
                    .withForceReadFromNetwork(forceNetwork)
                    .build();

            QuizAPI.startQuizPreview(courseId, quizId, adapter, callback, params);
        }
    }

    public static void getQuizSubmissionQuestions(long quizSubmissionId, StatusCallback<QuizSubmissionQuestionResponse> callback) {

        if(isTesting() || mTesting) {
            QuizManager_Test.getQuizSubmissionQuestions(quizSubmissionId, callback);
        } else {
            RestBuilder adapter = new RestBuilder(AppManager.getConfig(), callback);
            RestParams params = new RestParams.Builder()
                    .withPerPageQueryParam(true)
                    .withShouldIgnoreToken(false)
                    .build();

            QuizAPI.getQuizSubmissionQuestions(quizSubmissionId, adapter, callback, params);
        }
    }
}
