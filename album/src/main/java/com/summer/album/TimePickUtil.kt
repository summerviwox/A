package com.summer.album

import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.summer.lib.activity.BaseActivity
import java.util.Calendar

object TimePickUtil {

    fun showTimePicker(activity: BaseActivity) {
        val constraintsBuilder = CalendarConstraints.Builder()

        val minDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2015)
        }
        val maxDate = Calendar.getInstance()
        constraintsBuilder.setStart(minDate.timeInMillis)
        constraintsBuilder.setEnd(maxDate.timeInMillis)

        // 使用 DateValidator 确保用户不能选择范围外的日期
        constraintsBuilder.setValidator(
            CompositeDateValidator.allOf(
                listOf(
                    DateValidatorPointForward.from(minDate.timeInMillis),
                    DateValidatorPointBackward.before(maxDate.timeInMillis)
                )
            )
        )

        // 2. 显示日期选择器
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        datePicker.show(activity.supportFragmentManager, "DATE_PICKER_TAG")
        // 3. 监听选择结果
        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
        }
    }
}