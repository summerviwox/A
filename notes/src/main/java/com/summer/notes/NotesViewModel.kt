package com.summer.notes

import androidx.lifecycle.ViewModel

class NotesViewModel:ViewModel() {

    val 笔记列表数据 = mutableListOf<String>("start")

}