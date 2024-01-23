package com.example.jetpack.utils

import android.content.Context
import android.text.format.DateFormat
import android.widget.Toast
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random


const val ENTER_MOBILE_NUMBER = "Please enter Mobile No."
const val ENTER_VALIDE_MOBILE_NUMBER = "Please enter valid Mobile No."


const val NO_INTERNET_CONNECTION = "No network Connection !"
const val SOMETHING_WENT_WRONG = "Something went Wrong"


const val ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"


fun randomCharString(int: Int):String {
    val random = Random()
    val sb = StringBuilder(int)
    for (i in 0 until int)
        sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])

    var key = sb.toString()

    return key;
}

fun showToast(context: Context, text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, text, length).show()
}
fun getDate(timeStamp: Long): String? {
    val ts = Timestamp(timeStamp)
    val date: Date = ts
    var dateeee = DateFormat.format("yyyy-MM-dd HH:mm:ss.sss", date).toString();
    val strCurrentDate = dateeee
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
    val newDate = format.parse(strCurrentDate)
    format = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a")
    val date111 = format.format(newDate)
    return date111
}

fun <T> removeDuplicates(list: ArrayList<T>): ArrayList<T>? {

    val set: MutableSet<T> = LinkedHashSet()
    set.addAll(list)
    list.clear()
    list.addAll(set)
    return list
}
