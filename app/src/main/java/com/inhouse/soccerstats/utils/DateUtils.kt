package com.inhouse.soccerstats.utils

import java.text.SimpleDateFormat
import java.util.*

fun longDateFormat() = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())

fun dateOnlyFormat() = SimpleDateFormat("EEE dd MMM", Locale.getDefault())

fun timeOnlyFormat() = SimpleDateFormat("hh:mm a", Locale.getDefault())