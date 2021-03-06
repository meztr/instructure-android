/*
 * Copyright (C) 2017 - present Instructure, Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.instructure.teacher.adapters

import android.content.Context
import android.view.View
import com.instructure.canvasapi2.models.Assignment
import com.instructure.canvasapi2.models.GradeableStudentSubmission
import com.instructure.teacher.holders.GradeableStudentSubmissionViewHolder
import com.instructure.teacher.presenters.AssignmentSubmissionListPresenter
import com.instructure.teacher.utils.TeacherPrefs
import instructure.androidblueprint.SyncRecyclerAdapter

class GradeableStudentSubmissionAdapter(
        private val mAssignment: Assignment,
        private val mCourseId: Long,
        private val mContext: Context,
        presenter: AssignmentSubmissionListPresenter,
        val mCallback: (GradeableStudentSubmission) -> Unit) : SyncRecyclerAdapter<GradeableStudentSubmission, GradeableStudentSubmissionViewHolder>(mContext, presenter){


    override fun bindHolder(model: GradeableStudentSubmission, holderSubmission: GradeableStudentSubmissionViewHolder, position: Int) {
        holderSubmission.bind(mContext, model, mAssignment, mCourseId, TeacherPrefs.shouldGradeAnonymously(mCourseId, mAssignment.id), mCallback)
    }

    override fun createViewHolder(v: View, viewType: Int) = GradeableStudentSubmissionViewHolder(v)
    override fun itemLayoutResId(viewType: Int) = GradeableStudentSubmissionViewHolder.holderResId
}
