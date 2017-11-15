/*
 * Copyright (C) 2017 - present Instructure, Inc.
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
package com.instructure.canvasapi2.models

import android.os.Parcelable
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

sealed class Assignee : Parcelable {
    abstract val id: Long
    abstract val name: String
}

@PaperParcel
data class StudentAssignee(
        val student: User,
        override val id: Long = student.id,
        override val name: String = student.name ?: ""
) : Assignee(), PaperParcelable {
    companion object {
        @Suppress("unresolved_reference")
        @JvmField val CREATOR = PaperParcelStudentAssignee.CREATOR
    }
}

@JvmSuppressWildcards
@PaperParcel
data class GroupAssignee(
        val group: Group,
        val students: List<User>,
        override val id: Long = group.id,
        override val name: String = group.name ?: ""
) : Assignee(), PaperParcelable {
    companion object {
        @Suppress("unresolved_reference")
        @JvmField val CREATOR = PaperParcelGroupAssignee.CREATOR
    }
}