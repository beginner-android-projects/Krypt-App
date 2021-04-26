package com.pyra.krpytapplication.view.activity

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager
import com.pyra.krpytapplication.R
import com.pyra.krpytapplication.Utils.SharedHelper
import com.pyra.krpytapplication.model.ChatThemeModel
import com.pyra.krpytapplication.view.adapter.ChatMessageAdapter
import com.pyra.krpytapplication.view.adapter.ChatThemeListAdapter
import kotlinx.android.synthetic.main.activity_change_chat_theme.*
import kotlinx.android.synthetic.main.activity_change_chat_theme.backButton
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.ArrayList

class ChangeChatThemeActivity : BaseActivity() {

    var selectdbubleColor: String = ""
    var selectdbubleColorReciver: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_chat_theme)

        listener()
        setChatTheme()
        setChatThemeReceiver()
    }

    private fun listener() {
        backButton.setOnClickListener {
            onBackPressed()
        }

        if (!SharedHelper(this).chatBubbleColor.equals("", ignoreCase = true)) {
            senderText.setBackgroundColor(Color.parseColor(SharedHelper(this).chatBubbleColor))
        }

        if (!SharedHelper(this).chatBubbleColorReciver.equals("", ignoreCase = true)) {
            receiverparent.setBackgroundColor(Color.parseColor(SharedHelper(this).chatBubbleColorReciver))
        }




    }

    private fun setChatTheme() {

        var colorList: ArrayList<String> = arrayListOf(
            "#0B68DF",
            "#D63EA1",
            "#903ED6",
            "#3E78D6",
            "#5E53EB",
            "#07905B",
            "#62960E",
            "#008888",
            "#B17F21"
        )


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.stackFromEnd = false
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.isSmoothScrollbarEnabled = true
        var chatThemeListAdapter =
            ChatThemeListAdapter(this, colorList, SharedHelper(this).chatBubbleColor) {
                setThemeColor(it)
            }
        chatThemeList.layoutManager = linearLayoutManager
        chatThemeList?.adapter = chatThemeListAdapter


    }

    private fun setChatThemeReceiver() {

        var colorList: ArrayList<String> = arrayListOf(
            "#0B68DF",
            "#D63EA1",
            "#903ED6",
            "#3E78D6",
            "#5E53EB",
            "#07905B",
            "#62960E",
            "#008888",
            "#B17F21"
        )


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.stackFromEnd = false
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.isSmoothScrollbarEnabled = true

        var chatThemeListAdapter =
            ChatThemeListAdapter(this, colorList, SharedHelper(this).chatBubbleColorReciver) {
                setThemeColorReceiver(it)
            }
        chatThemeListReciver.layoutManager = linearLayoutManager
        chatThemeListReciver?.adapter = chatThemeListAdapter

    }

    fun onConfirmClicked(view: View) {
        if (!selectdbubleColor.equals("", ignoreCase = true)) {
            SharedHelper(this).chatBubbleColor = selectdbubleColor
        }

       if (!selectdbubleColorReciver.equals("", ignoreCase = true)) {
            SharedHelper(this).chatBubbleColorReciver = selectdbubleColorReciver
        }

        onBackPressed()
    }

    fun setThemeColor(value: String) {
        selectdbubleColor = value
        senderText.setBackgroundColor(Color.parseColor(value))

    }


    fun setThemeColorReceiver(value: String) {
        selectdbubleColorReciver = value
        receiverparent.setBackgroundColor(Color.parseColor(value))

    }
}