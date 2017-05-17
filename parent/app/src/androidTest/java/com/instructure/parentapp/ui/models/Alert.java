/*
 * Copyright (C) 2017 - present Instructure, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/* This is an auto-generated file. */

package com.instructure.parentapp.ui.models;

public class Alert {
    public String id;
    public String parentId;
    public String studentId;
    public String alertType;
    public String title;
    public String actionDate;
    public String alertThresholdId;
    public String courseId;

    public Alert(String id, String parentId, String studentId, String alertType, String title, String actionDate, String alertThresholdId, String courseId) {
        this.id = id;
        this.parentId = parentId;
        this.studentId = studentId;
        this.alertType = alertType;
        this.title = title;
        this.actionDate = actionDate;
        this.alertThresholdId = alertThresholdId;
        this.courseId = courseId;
    }
}