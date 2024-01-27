package com.paranid5.mvc_regex

import com.paranid5.mvc_regex.data.SubstringModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun revalidateMatches(matchedList: List<SubstringModel>, totalMatches: Int)

    @AddToEndSingle
    infix fun revalidateButtonEnabled(isButtonEnabled: Boolean)
}