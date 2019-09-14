package com.soict.hoangviet.baseproject.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogUtil {
    fun showMessageDialog(
        context: Context,
        title: String,
        message: String,
        positiveTitle: String,
        listener: BaseDialogInterface
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveTitle) { dialog, which ->
                listener.onPositiveClick(dialog)
            }
        alertDialog.show()
    }

    fun showConfirmDialog(
        context: Context,
        title: String,
        message: String,
        positiveTitle: String,
        negativeString: String,
        listener: BaseDialogInterface
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveTitle) { dialog, which ->
                listener.onPositiveClick(dialog)
            }
            .setNegativeButton(negativeString) { dialog, which ->
                listener.onNegativeClick(dialog)
            }
        alertDialog.show()
    }

    fun showChooseItemDialog(
        context: Context,
        title: String,
        listItem: Array<String>,
        listener: BaseDialogInterface
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
            .setItems(listItem) { dialog, which ->
                listener.onItemClick(dialog, which)
            }
        alertDialog.show()
    }

    fun showMultiChoiceItemsDialog(
        context: Context,
        title: String,
        listItem: Array<String>
    ): ArrayList<Int> {
        val selectedItems = ArrayList<Int>()
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
            .setMultiChoiceItems(listItem, null) { dialog, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(which)
                } else if (selectedItems.contains(which)) {
                    selectedItems.remove(Integer.valueOf(which))
                }
            }
        alertDialog.show()
        return selectedItems
    }

    fun showSingleChoiceItemsDialog(
        context: Context,
        title: String,
        listItem: Array<String>,
        listener: BaseDialogInterface
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
            .setSingleChoiceItems(listItem, 0) { dialog, which ->
                listener.onItemClick(dialog, which)
            }
        alertDialog.show()
    }

    interface BaseDialogInterface {
        fun onPositiveClick(dialog: DialogInterface)
        fun onNegativeClick(dialog: DialogInterface)
        fun onItemClick(dialog: DialogInterface, position: Int)
    }
}