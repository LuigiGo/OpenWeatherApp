package com.exam.openweatherapp.core.utils.helpers

import android.app.Activity
import android.graphics.Color
import com.exam.openweatherapp.R
import com.labters.lottiealertdialoglibrary.ClickListener
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog

class DialogHelper {
    private var dialog: LottieAlertDialog? = null

    fun showDialog(
        activity: Activity,
        title: String? = "Oopss!",
        message: String? = "Something went wrong",
        okButtonTitle: String? = "Okay",
        dialogTypes: Int? = DialogTypes.TYPE_SUCCESS,
        okButtonAction: (() -> Unit)? = null,
    ) {
        dialog?.apply {
            if (isShowing) {
                dialog?.dismiss()
            }
        }

        dialog =
            LottieAlertDialog.Builder(activity, dialogTypes).setTitle(title).setDescription(message)
                .setPositiveText(okButtonTitle).setPositiveTextColor(Color.WHITE)
                .setPositiveListener(object : ClickListener {
                    override fun onClick(dialog: LottieAlertDialog) {
                        dialog.dismiss()
                        okButtonAction?.invoke()
                    }
                }).build()
        dialog?.setCancelable(false)
        dialog?.show()
    }

}